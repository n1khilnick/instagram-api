package com.geekster.instagramapi.services;

import com.geekster.instagramapi.models.AuthenticationToken;
import com.geekster.instagramapi.models.User;
import com.geekster.instagramapi.repositories.ITokenDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    @Autowired
    ITokenDao tokenDao;


    public void createToken(AuthenticationToken token) {
        tokenDao.save(token);
    }

    public AuthenticationToken getToken(User user) {
        return tokenDao.findByUser(user);
    }

    public boolean authenticate(String token) {
     AuthenticationToken authToken = tokenDao.findFirstByToken(token);

     return authToken != null;
    }

    public boolean authenticateForPost(String userEmail, String token) {
        AuthenticationToken authToken = tokenDao.findFirstByToken(token);

        return authToken != null;
    }

}
