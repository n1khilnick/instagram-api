package com.geekster.instagramapi.services;

import com.geekster.instagramapi.dto.SignInInput;
import com.geekster.instagramapi.dto.SignInOutput;
import com.geekster.instagramapi.dto.SignUpInput;
import com.geekster.instagramapi.dto.SignUpOutput;
import com.geekster.instagramapi.models.AuthenticationToken;
import com.geekster.instagramapi.models.Post;
import com.geekster.instagramapi.models.User;
import com.geekster.instagramapi.repositories.IUserDao;
import jakarta.transaction.Transactional;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UserService {
    @Autowired
    IUserDao userDao;

    @Autowired
    TokenService tokenService;

    @Autowired
    PostService postService;


    public SignUpOutput signUp(SignUpInput userSignUp)  {
        //checks if user exists or not based on email
        User user = userDao.findFirstByUserEmail(userSignUp.getUserEmail());

        if(user != null){
            throw new IllegalStateException("Email has already been taken !! Try another email");
        }

//        //checks if user exists or not based on username
//         user = userDao.findFirstByUserName(userSignUp.getUserName());
//
//        if(user != null){
//            throw new IllegalStateException("username has already been taken !! Try another ");
//        }


        //password encryption

        String encryptedPassword = null;

        try{
            encryptedPassword = encryptedPassword(userSignUp.getUserPassword());
        }catch (NoSuchAlgorithmException ex){
                ex.printStackTrace();
        }

        //Save the user
        user = new User(userSignUp.getUserEmail(),userSignUp.getUserPhoneNumber(),userSignUp.getUserFirstName(),userSignUp.getUserLastName(),userSignUp.getUserName(),encryptedPassword,userSignUp.getUserAge());

        userDao.save(user);

        //token creation and saving : session

        AuthenticationToken token =new AuthenticationToken(user);

        tokenService.createToken(token);

        return new SignUpOutput("SignUp Successful","User created Successfully !!");

    }

    private String encryptedPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(password.getBytes());
        byte[] digested = md5.digest();

        return DatatypeConverter.printHexBinary(digested); //hash
    }

    public SignInOutput signIn(SignInInput userSignIn) {
        //check user based on email
        User user = userDao.findFirstByUserEmail(userSignIn.getUserEmail());

        if(user == null){
            throw new IllegalStateException("user doesn't exists !! check details or SignUp First");
        }

        //password encryption
        String encryptedPassword = null;

        try {
            encryptedPassword = encryptedPassword(userSignIn.getUserPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        //Match the encrypted password with password saved in database

        boolean isPasswordValid = encryptedPassword.equals(user.getUserPassword());

        if(!isPasswordValid){
            throw new IllegalStateException("Sorry, your details are incorrect. Please double-check your details.");
        }

        //figure out the token

        AuthenticationToken authToken = tokenService.getToken(user);


        //set up output response

        return new SignInOutput("User authentication successfully !!",authToken.getToken());

    }

    @Transactional
    public void updateUserPhoneNumberById(Long userId, Long userPhoneNumber) {
        userDao.updateUserPhoneNumberById(userId,userPhoneNumber);
    }

}
