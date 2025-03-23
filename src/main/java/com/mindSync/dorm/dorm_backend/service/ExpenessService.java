package com.mindSync.dorm.dorm_backend.service;

import com.mindSync.dorm.dorm_backend.dto.ExpenseRequest;
import com.mindSync.dorm.dorm_backend.model.Expenses;
import com.mindSync.dorm.dorm_backend.model.Room;
import com.mindSync.dorm.dorm_backend.model.User;
import com.mindSync.dorm.dorm_backend.repository.ExpensesRepository;
import com.mindSync.dorm.dorm_backend.repository.RoomRepository;
import com.mindSync.dorm.dorm_backend.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenessService {

    final ExpensesRepository expensesRepository;
    final UserRepository userRepository;
    final RoomRepository roomRepository;

    ExpenessService(ExpensesRepository expensesRepository,UserRepository userRepository,RoomRepository roomRepository)
    {
        this.expensesRepository = expensesRepository;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
    }

    public String addExpenses(ExpenseRequest expenseRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName(); // Retrieves email of logged-in seller

        User user = userRepository.findByUsername(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Find all teammates in the same room, excluding the logged-in user
        List<User> teammates = userRepository.findByRoomNumberAndUsernameNot(user.getRoomNumber(), user.getUsername());

        // Check if at least one teammate exists
        String teammateName = teammates.isEmpty() ? "No teammate found" : teammates.get(0).getName();

        // Calculate half of the total amount
        Double halfAmount = expenseRequest.getTotalAmount() / 2;

        Expenses expenses = Expenses.builder()
                .purchasedBy(user.getName())
                .description(expenseRequest.getDescription())
                .teamMateName(teammateName)
                .myContribution(expenseRequest.getMyContribution())
                .myRemaining(halfAmount - expenseRequest.getMyContribution()    )
                .teammateContributionAmount(halfAmount)
                .totalAmount(expenseRequest.getTotalAmount())
                .user(user)
                .roomNumber(user.getRoomNumber())  // ✅ Only keeping roomNumber
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .updatedBy(user.getName())
                .build();

        expensesRepository.save(expenses);

        return "Expenses added successfully";
    }

}
