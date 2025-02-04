package com.example.MaslyakBank_client.domain;

import jakarta.persistence.*;


@Entity
@Table(name = "bank_user_balance")
public class UserBalanceTable {

    public UserBalanceTable() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_id")
    private Integer user_id;
    @Column(name = "balance_usd")
    private String balance_usd;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBalance_usd() {
        return balance_usd;
    }

    public void setBalance_usd(String balance_usd) {
        this.balance_usd = balance_usd;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

}
