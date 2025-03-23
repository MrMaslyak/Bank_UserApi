package com.example.MaslyakBank_client.domain;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "bank_user_auth_token")
public class UserAuthTokenTable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UserDataTable user_id;

    @Column(name = "token" , length = 50, nullable = false)
    private String token;

    @Column(name = "created_at")
    private LocalDateTime created_at;

    }
