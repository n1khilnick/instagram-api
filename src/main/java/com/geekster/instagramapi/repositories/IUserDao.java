package com.geekster.instagramapi.repositories;

import com.geekster.instagramapi.models.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDao extends CrudRepository<User,Long> {

    User findFirstByUserEmail(String userEmail);


    @Modifying
    @Query(value = "update user_table set user_phone_number = :userPhoneNumber where user_id = :userId",nativeQuery = true)
    void updateUserPhoneNumberById(Long userId, Long userPhoneNumber);

}
