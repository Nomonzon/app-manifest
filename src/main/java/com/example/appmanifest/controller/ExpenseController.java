package com.example.appmanifest.controller;

import com.example.appmanifest.entity.Expense;
import com.example.appmanifest.payload.ExpensesDto;
import com.example.appmanifest.payload.Message;
import com.example.appmanifest.repository.ExpenseRepo;
import com.example.appmanifest.service.ExpenseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("api/manifest/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseServiceImpl expenseService;

    @Autowired
    private ExpenseRepo expenseRepo;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Expense> allExpenses = expenseService.getAllExpenses();
        return new ResponseEntity<>(expenseService, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        Expense expenses = expenseService.getExpenses(id);
        return expenses != null ?
                new ResponseEntity<>(expenses, HttpStatus.OK) :
                new ResponseEntity<>("Is not found", HttpStatus.NOT_FOUND);
    }
//    http://localhost:8080/api/v1/mno/objectKey/{"date1":, "date2":"Saif"}
    @GetMapping("/select")
    public ResponseEntity<?> getBySelection(@RequestParam(value = "data1") Timestamp data1, @RequestParam(value = "data2") Timestamp data2){
//        data1.toLocalDateTime();
//        data2.toLocalDateTime();
        List<Expense> selectionDate = expenseRepo.findBySelectionDate(data1, data2);
        return new ResponseEntity<>(selectionDate, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody ExpensesDto expensesDto){
        Message message = expenseService.addExpenses(expensesDto);
        return message.isSuccess() ?
                new ResponseEntity<>(message.getMessage(), HttpStatus.CREATED) :
                new ResponseEntity<>(message.getMessage(), HttpStatus.CONFLICT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ExpensesDto expensesDto){
        Message message = expenseService.updateExpenses(expensesDto, id);
        return message.isSuccess() ?
                new ResponseEntity<>(message.getMessage(), HttpStatus.OK) :
                new ResponseEntity<>(message.getMessage(), HttpStatus.CONFLICT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Message message = expenseService.deleteExpense(id);
        return message.isSuccess() ?
                new ResponseEntity<>(message.getMessage(), HttpStatus.OK) :
                new ResponseEntity<>(message.getMessage(), HttpStatus.CONFLICT);
    }

}
