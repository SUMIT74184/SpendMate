package com.expense.Service.service;


import com.expense.Service.dto.ExpenseDto;
import com.expense.Service.entities.Expense;
import com.expense.Service.repository.ExpenseRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ExpenseService {

    private ExpenseRepository expenseRepository;

    private ObjectMapper objectMapper=new ObjectMapper();

    @Autowired
    ExpenseService(ExpenseRepository expenseRepository){
        this.expenseRepository=expenseRepository;
    }

    public boolean createExpense(ExpenseDto expenseDto){
      setCurrency(expenseDto);
      try{
          //saving and converting the expenseDto into expense entities
          expenseRepository.save(objectMapper.convertValue(expenseDto, Expense.class));
          return true;
        } catch (Exception e) {
       return false;
      }
    }

    public boolean updateExpense(ExpenseDto expenseDto){
        Optional<Expense> expenseFoundOpt=expenseRepository.findByUserIdAndExternalId(
                expenseDto.getUserId(), expenseDto.getExternalId());
        if(expenseFoundOpt.isEmpty()){
            return false;
        }
        Expense expense=expenseFoundOpt.get();
        expense.setCurrency(Strings.isNotBlank(expenseDto.getCurrency())
                ?expenseDto.getCurrency() //jo default currency
                :expense.getCurrency()); //or no update -->.whatever currency we have fetched

        expense.setMerchant(Strings.isNotBlank(expenseDto.getMerchant())
                 ?expenseDto.getMerchant()
                :expense.getCurrency());

        expense.setAmount(Strings.isNotBlank(expenseDto.getAmount())
                ?expenseDto.getAmount()
                :expense.getAmount());

        expenseRepository.save(expense);
        return true;
    }

    public List<ExpenseDto>getExpenses(String userId){
        List<Expense>expenseList=expenseRepository.findByUserId(userId);
        return objectMapper.convertValue(expenseList,new TypeReference<List<ExpenseDto>>(){
    //we are getting the list of expenses and list in runtime accepts the generic that's why we have used type-references
        });
    }

    private void setCurrency(ExpenseDto expenseDto){
        if(Objects.isNull(expenseDto.getCurrency())){
            expenseDto.setCurrency("inr");
        }
    }


}
