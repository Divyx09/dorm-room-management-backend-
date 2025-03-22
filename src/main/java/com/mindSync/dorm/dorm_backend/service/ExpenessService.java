package com.mindSync.dorm.dorm_backend.service;

import com.mindSync.dorm.dorm_backend.dto.ExpenseRequest;
import com.mindSync.dorm.dorm_backend.model.Expenses;
import com.mindSync.dorm.dorm_backend.model.User;
import com.mindSync.dorm.dorm_backend.repository.ExpensesRepository;
import com.mindSync.dorm.dorm_backend.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ExpenessService {

    final ExpensesRepository expensesRepository;
    final UserRepository userRepository;

    ExpenessService(ExpensesRepository expensesRepository,UserRepository userRepository)
    {
        this.expensesRepository = expensesRepository;
        this.userRepository = userRepository;
    }

    public String addExpenses(ExpenseRequest expenseRequest)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName(); // Retrieves email of logged-in seller

        User user = userRepository.findByUsername(userEmail)
                .orElseThrow(() -> new RuntimeException("uaer not found"));
        Expenses expenses = Expenses.builder()
                .purchasedBy(expenseRequest.getPurchasedBy())
                .teamMateName(expenseRequest.getTeamMateName())
                .myContribution(expenseRequest.getMyContribution())
                .contributionAmount(expenseRequest.getContributionAmount())
                .totalAmount(expenseRequest.getTotalAmount())
                .user(user)
                .build();

        expensesRepository.save(expenses);

        return "expenses added";
    }
}
