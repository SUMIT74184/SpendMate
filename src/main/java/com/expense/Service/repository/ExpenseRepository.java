package com.expense.Service.repository;

import com.expense.Service.entities.Expense;
import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends CrudRepository<Expense,Long> {
    List<Expense> findByUserId(String userId);

    List<Expense>findByUserIdAndCreatedAtBetween(String userId, Timestamp startTime, Timestamp endTime);

    //to delete any expense which is created by mistake---userid and expense_id
    Optional<Expense>findByUserIdAndExternalId(String userId,String externalId);

}
