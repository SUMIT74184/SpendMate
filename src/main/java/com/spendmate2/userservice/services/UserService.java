package com.spendmate2.userservice.services;
import com.spendmate2.userservice.entities.UserInfo;
import com.spendmate2.userservice.entities.UserInfoDto;
import com.spendmate2.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserInfoDto createorUpdateUser(UserInfoDto userInfoDto){
        UnaryOperator<UserInfo> updatingUser= user->{
            //implement the todo update methd
                    user.setEmail(userInfoDto.getEmail());
                    user.setFirstName(userInfoDto.getFirstName());
                    user.setLastName(userInfoDto.getLastName());
                    user.setPhoneNumber(userInfoDto.getPhoneNumber());
          return userRepository.save(user);
        };

        Supplier<UserInfo> createUser=()->{
            return userRepository.save(userInfoDto.transformToUserInfo());
        };

        UserInfo userInfo=userRepository.findByUserId(userInfoDto.getUserId())
                .map(updatingUser)
                .orElseGet(createUser);
            return new UserInfoDto(
                    userInfo.getUserId(),
                    userInfo.getFirstName(),
                    userInfo.getLastName(),
                    userInfo.getPhoneNumber(),
                    userInfo.getEmail(),
                    userInfo.getProfilePic()
            );
    }

    public UserInfoDto getUser(UserInfoDto userInfoDto) throws Exception{
        Optional<UserInfo> userInfoOpt = userRepository.findByUserId(userInfoDto.getUserId());
        if(userInfoOpt.isEmpty()){
            throw new Exception("User not found");
        }
        UserInfo userInfo=userInfoOpt.get();
        return new UserInfoDto(
                userInfo.getUserId(),
                userInfo.getFirstName(),
                userInfo.getLastName(),
                userInfo.getPhoneNumber(),
                userInfo.getEmail(),
                userInfo.getProfilePic()
        );
    }
}
