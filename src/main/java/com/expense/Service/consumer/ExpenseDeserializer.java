package com.expense.Service.consumer;

import com.expense.Service.dto.ExpenseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
//import org.springframework.core.serializer.Deserializer;

import java.util.Map;

public class ExpenseDeserializer implements Deserializer<ExpenseDto> {

    @Override
    public void close(){
    }

    @Override
    public void configure(Map<String,?> configs, boolean isKey){
    }

    @Override
    public ExpenseDto deserialize(String arg0,byte[] arg1){
        ObjectMapper objectMapper = new ObjectMapper();
        ExpenseDto expenseDto=null;
        try{
            expenseDto=objectMapper.readValue(arg1,ExpenseDto.class); //amount,merchant,currency
        } catch (Exception e) {
//            throw new RuntimeException(e);
            e.printStackTrace();
        }
        return expenseDto;
    }
}
