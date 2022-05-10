package com.example.appmanifest.service;

import com.example.appmanifest.entity.Category;
import com.example.appmanifest.entity.Expense;
import com.example.appmanifest.payload.ExpensesDto;
import com.example.appmanifest.payload.Message;
import com.example.appmanifest.repository.CategoryRepo;
import com.example.appmanifest.repository.ExpenseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseServiceImpl {

    @Autowired
    private ExpenseRepo expenseRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    /**
     * This method is for getting all expenses from the database;
     * @return List<Expense>
     */
    public List<Expense> getAllExpenses(){
        return expenseRepo.findAll();
    }

    /**
     * This method is for getting expense by id from the database;
     * @param id this id comes from path;
     * @return Expense by id
     */
    public Expense getExpenses(Long id){
        Optional<Expense> expenseOptional = expenseRepo.findById(id);
        return expenseOptional.orElse(null);
    }

    /**
     * This method is for adding new expense;
     * @param expensesDto
     * @return new Message(message, boolean)
     */
    public Message addExpenses(ExpensesDto expensesDto){
        Optional<Category> categoryOptional = categoryRepo.findById(expensesDto.getCategoryId());

        if (categoryOptional.isPresent()) {
            Expense expense = new Expense(expensesDto.getSum(), expensesDto.getComment(), categoryOptional.get());
            expenseRepo.save(expense);
            return new Message("Success expense has been successfully added", true);
        }
        return new Message("Error", false);
    }

    /**
     *
     * @param expensesDto
     * @param id
     * @return
     */
    public Message updateExpenses(ExpensesDto expensesDto, Long id){
        Optional<Expense> expenseOptional = expenseRepo.findById(id);
        Optional<Category> categoryOptional = categoryRepo.findById(id);
        if (categoryOptional.isEmpty()){
            return new Message("Category id has not founded", false);
        }
        if (expenseOptional.isPresent()){
            Expense expense = expenseOptional.get();
            expense.setComment(expensesDto.getComment());
            expense.setSum(expensesDto.getSum());
            expense.setCategory(categoryOptional.get());
            expenseRepo.save(expense);
            return new Message("Success added", true);
        }
        return new Message("Error", false);
    }

    public Message deleteExpense(Long id){
        try {
            expenseRepo.deleteById(id);
            return new Message("Success Deleted", true);
        }catch (Exception e){
            return new Message("Error", false);

        }
    }

}
