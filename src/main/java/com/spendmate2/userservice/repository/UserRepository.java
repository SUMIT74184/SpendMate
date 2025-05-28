package com.spendmate2.userservice.repository;

import com.spendmate2.userservice.entities.UserInfo;
import com.spendmate2.userservice.entities.UserInfoDto;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface UserRepository extends CrudRepository<UserInfo,String>
{
  Optional<UserInfo> findByUserId(String userId);

}
