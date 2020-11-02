package com.moodyjun.expensetrackerapp.repository;

import com.moodyjun.expensetrackerapp.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense,Long> {


}
