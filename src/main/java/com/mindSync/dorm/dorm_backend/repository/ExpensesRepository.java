package com.mindSync.dorm.dorm_backend.repository;

import com.mindSync.dorm.dorm_backend.model.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpensesRepository extends JpaRepository<Expenses,Long> {
}
