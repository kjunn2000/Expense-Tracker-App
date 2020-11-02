package com.moodyjun.expensetrackerapp.repository;

import com.moodyjun.expensetrackerapp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    public Category findByName(String name);


}
