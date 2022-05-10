package com.example.appmanifest.controller;

import com.example.appmanifest.entity.Expense;
import com.example.appmanifest.entity.Income;
import com.example.appmanifest.payload.ExpensesDto;
import com.example.appmanifest.payload.IncomeDto;
import com.example.appmanifest.payload.Message;
import com.example.appmanifest.repository.ExpenseRepo;
import com.example.appmanifest.repository.IncomeRepo;
import com.example.appmanifest.service.ExpenseServiceImpl;
import com.example.appmanifest.service.IncomeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("api/manifest/incomes")
public class IncomeController {
    @Autowired
    private IncomeServiceImpl incomeService;

    @Autowired
    private IncomeRepo incomeRepo;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Income> allIncomes = incomeService.getAllIncomes();
        return new ResponseEntity<>(allIncomes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        Income incomes = incomeService.getIncomes(id);
        return incomes != null ?
                new ResponseEntity<>(incomes, HttpStatus.OK) :
                new ResponseEntity<>("Is not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/select")
    public ResponseEntity<?> getBySelection(@RequestParam(value = "data1") Timestamp data1, @RequestParam(value = "data2") Timestamp data2){
        List<Income> selectionDate = incomeRepo.findBySelectionDate(data1, data2);
        return new ResponseEntity<>(selectionDate, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody IncomeDto incomeDto){
        Message message = incomeService.addIncome(incomeDto);
        return message.isSuccess() ?
                new ResponseEntity<>(message.getMessage(), HttpStatus.CREATED) :
                new ResponseEntity<>(message.getMessage(), HttpStatus.CONFLICT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody IncomeDto incomeDto){
        Message message = incomeService.updateIncome(incomeDto, id);
        return message.isSuccess() ?
                new ResponseEntity<>(message.getMessage(), HttpStatus.OK) :
                new ResponseEntity<>(message.getMessage(), HttpStatus.CONFLICT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Message message = incomeService.deleteIncome(id);
        return message.isSuccess() ?
                new ResponseEntity<>(message.getMessage(), HttpStatus.OK) :
                new ResponseEntity<>(message.getMessage(), HttpStatus.CONFLICT);
    }
}
