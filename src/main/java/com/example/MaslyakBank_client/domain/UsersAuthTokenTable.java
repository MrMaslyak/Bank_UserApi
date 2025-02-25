package com.example.MaslyakBank_client.domain;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "bank_user_auth_token")
public class UsersAuthTokenTable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer user_id;

    @Column(name = "token")
    private String token;

    @Column(name = "created_at")
    private Date created_at;

    }
