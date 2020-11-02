package com.moodyjun.expensetrackerapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "expense_table")
public class Expense {

    @Id
    @GeneratedValue
    private long eid ;
    private Instant expenseDate;
    private String description;
    private String location;
    @ManyToOne
    private User user ;
    @ManyToOne
    private Category category;

}
