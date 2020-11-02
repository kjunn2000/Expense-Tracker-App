package com.moodyjun.expensetrackerapp.service;


import com.moodyjun.expensetrackerapp.model.Expense;
import com.moodyjun.expensetrackerapp.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public List<Expense> findAllExpenses(){
        return expenseRepository.findAll();
    }

    public Optional<Expense> findExpenseById(long id){
        return expenseRepository.findById(id);
    }

    public Expense createExpense(Expense expense){
        return expenseRepository.save(expense);
    }

    public List<Expense> createExpenses(List<Expense> expenses){
        return expenseRepository.saveAll(expenses);
    }

    public Expense updateExpense ( Expense expense){
        Expense exitingExpense = findExpenseById(expense.getEid()).orElse(null);
        if (exitingExpense!=null){
            exitingExpense.setExpenseDate(expense.getExpenseDate());
            exitingExpense.setDescription(expense.getDescription());
            exitingExpense.setCategory(expense.getCategory());
            exitingExpense.setLocation(expense.getLocation());
            exitingExpense.setUser(expense.getUser());
        }
        return expenseRepository.save(exitingExpense);
    }

    public String deleteExpenseById (long id){
        expenseRepository.deleteById(id);
        return "Successfully delete expense with id - "+id;
    }



}
