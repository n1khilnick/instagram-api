package com.geekster.instagramapi.repositories;

import com.geekster.instagramapi.models.AuthenticationToken;
import com.geekster.instagramapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITokenDao extends JpaRepository<AuthenticationToken,Long> {
    AuthenticationToken findByUser(User user);

    AuthenticationToken findFirstByToken(String token);
}
