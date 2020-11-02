package com.moodyjun.expensetrackerapp.controller;

import com.moodyjun.expensetrackerapp.model.Expense;
import com.moodyjun.expensetrackerapp.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ExpenseController {

    @Autowired
    ExpenseService expenseService;

    @RequestMapping("/expenses")
    public List<Expense> getExpenses(){
        return expenseService.findAllExpenses();
    }

    @RequestMapping("/expense/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable long id){
        return expenseService.findExpenseById(id)
                .map(response-> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/expense")
    public ResponseEntity<Expense> createExpense(@RequestBody Expense expense) throws URISyntaxException {
        return ResponseEntity.created(new URI("/api/expense/"+expense.getEid())).body(expenseService.createExpense(expense));
    }

    @PutMapping("/expense")
    public ResponseEntity<Expense> updateExpense(@RequestBody Expense expense){
        return ResponseEntity.ok().body(expenseService.updateExpense(expense));
    }

    @DeleteMapping("/expense/{id}")
    @ResponseBody
    public String deleteExpense(@PathVariable long id ){
        return expenseService.deleteExpenseById(id);
    }
}
