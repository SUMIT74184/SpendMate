package com.spendmate2.userservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;


@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true) //null userid will not be deserialized
public class UserInfoDto {

    private String userId;

    @JsonProperty("first_name")
    @NonNull
    private String firstName;

    @JsonProperty("last_name")
    @NonNull
    private String lastName;

    @JsonProperty("phone_number")
    @NonNull
    private Long phoneNumber;

    @JsonProperty("email")
    private String email;

    @JsonProperty("profile_Pic")
    private String profilePic;

    public UserInfo transformToUserInfo(){
        return UserInfo.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .phoneNumber(phoneNumber)
                .profilePic(profilePic)
                .build();
    }
}
