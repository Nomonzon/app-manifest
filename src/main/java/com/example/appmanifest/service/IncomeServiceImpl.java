package com.example.appmanifest.service;

import com.example.appmanifest.entity.CategoryIncome;
import com.example.appmanifest.entity.Income;
import com.example.appmanifest.payload.IncomeDto;
import com.example.appmanifest.payload.Message;
import com.example.appmanifest.repository.CategoryIncomesRepo;
import com.example.appmanifest.repository.IncomeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncomeServiceImpl {

    @Autowired
    private IncomeRepo incomeRepo;

    @Autowired
    private CategoryIncomesRepo categoryIncomesRepo;

    /**
     * This method is for getting all incomes from the database;
     * @return
     */

    public List<Income> getAllIncomes(){
        return incomeRepo.findAll();
    }

    /**
     * This method is for getting income by id from the database;
     * @param id this id comes from path;
     * @return Expense by id
     */
    public Income getIncomes(Long id){
        Optional<Income> incomeOptional = incomeRepo.findById(id);
        return incomeOptional.orElse(null);
    }

    /**
     * This method is for adding new "income";
     * @param incomeDto
     * @return new Message(message, boolean)
     */
    public Message addIncome(IncomeDto incomeDto){
        Optional<CategoryIncome> categoryIncomeOptional = categoryIncomesRepo.findById(incomeDto.getCategoryId());
        if (categoryIncomeOptional.isPresent()) {
            Income income = new Income(incomeDto.getSum(), incomeDto.getComment(), categoryIncomeOptional.get());
            incomeRepo.save(income);
            return new Message("Success income has been successfully added", true);
        }
        return new Message("Error", false);
    }

    /**
     *
     * @param incomeDto
     * @param id
     * @return
     */
    public Message updateIncome(IncomeDto incomeDto, Long id){
        Optional<Income> incomeOptional = incomeRepo.findById(id);
        Optional<CategoryIncome> categoryIncomeOptional = categoryIncomesRepo.findById(id);
        if (categoryIncomeOptional.isEmpty()){
            return new Message("Category id has not founded", false);
        }
        if (incomeOptional.isPresent()){
            Income income = incomeOptional.get();
            income.setComment(incomeDto.getComment());
            income.setSum(incomeDto.getSum());
            income.setCategoryIncomes(categoryIncomeOptional.get());
            incomeRepo.save(income);
            return new Message("Success added", true);
        }
        return new Message("Error", false);
    }

    public Message deleteIncome(Long id){
        try {
            incomeRepo.deleteById(id);
            return new Message("Success Deleted", true);
        }catch (Exception e){
            return new Message("Error", false);
        }
    }

}
