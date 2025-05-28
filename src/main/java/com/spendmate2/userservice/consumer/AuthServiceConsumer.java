package com.spendmate2.userservice.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spendmate2.userservice.entities.UserInfo;
import com.spendmate2.userservice.entities.UserInfoDto;
import com.spendmate2.userservice.repository.UserRepository;
import com.spendmate2.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceConsumer {

    @Autowired
    private UserService userService;

//    private UserRepository userRepository;

//    AuthServiceConsumer(ObjectMapper objectMapper,UserRepository userRepository){
//        this.userRepository=userRepository;
//
//    }
    @KafkaListener(topics = "${spring.kafka.topic.name}",groupId = "${spring.kafka.consumer.group-id}")
    public void listen(UserInfoDto eventData){
        try{
//        UserInfo userInfo=eventData.transformToUserInfo();
//        userRepository.save(userInfo);
            //Todo make it transactional ..to handle idempotency and validate email,phoneNumber
            userService.createorUpdateUser(eventData);

        } catch (Exception e) {

            e.printStackTrace();
            System.out.println("AuthServiceConsumer:Exception is thrown while consuming kafka event");
        }
    }

}
