package com.mindSync.dorm.dorm_backend.controller;

import com.mindSync.dorm.dorm_backend.dto.ExpenseRequest;
import com.mindSync.dorm.dorm_backend.service.ExpenessService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/expenses")
public class ExpensesController {

    final ExpenessService expenessService;

    ExpensesController(ExpenessService expenessService)
    {
        this.expenessService = expenessService;
    }

    @PostMapping("/addexpenses")
    public String addExpenses(@Valid @RequestBody ExpenseRequest expenseRequest)
    {
        return expenessService.addExpenses(expenseRequest);
    }
}
