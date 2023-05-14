package com.geekster.instagramapi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AuthenticationToken {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long tokenId;

        private String token;

        private LocalDateTime tokenCreationTime;

        @OneToOne
        @JoinColumn(name = "fk_user_user_id")
        private User user;

        public AuthenticationToken(User user) {
                this.token = UUID.randomUUID().toString();
                this.tokenCreationTime = LocalDateTime.now();
                this.user = user;
        }

}
