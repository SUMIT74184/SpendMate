package com.spendmate2.userservice.deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spendmate2.userservice.entities.UserInfoDto;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class UserInfoDeserializer implements Deserializer<UserInfoDto> {
    @Override
    public void configure(Map<String,?>configs,boolean isKey){
    }

    @Override
    public UserInfoDto deserialize(String arg0,byte[] arg1){
        ObjectMapper objectMapper=new ObjectMapper();
        UserInfoDto user=null;
        try{
        user = objectMapper.readValue(arg1,UserInfoDto.class);
        } catch (Exception e) {
//            throw new RuntimeException(e);
            e.printStackTrace();
            System.out.println("Can not deserialize");
        }
        return user;
    }
    @Override
    public void close(){

    }
}
// we need to move ahead from userinfodto ->userinfo
