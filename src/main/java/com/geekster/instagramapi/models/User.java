package com.geekster.instagramapi.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_table")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,scope = User.class,property = "userId")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String userEmail;

    private Long userPhoneNumber;

    private String userFirstName;

    private String userLastName;

    private String userName;

    private String userPassword;

    private Integer userAge;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    public User(String userEmail, Long userPhoneNumber, String userFirstName, String userLastName, String userName, String userPassword, Integer userAge) {
        this.userEmail = userEmail;
        this.userPhoneNumber = userPhoneNumber;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userAge = userAge;
    }
}
