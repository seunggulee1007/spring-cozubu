package com.tp.cozubu.model.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "t_user")
@DynamicUpdate
public class User {

    @Id
    private String username;

    @Column
    private String password;

    @Column(name = "api_con_key")
    private String apiConkey;

    @Column(name = "api_sec_key")
    private String apiSecKey;

    @Column(name = "msg_id")
    private String msgId;

    @Column
    private boolean authenticated;

    @Column(name = "prefer_strategy")
    private String preferStrategy;

    @Column(name = "api_key_verify", columnDefinition = "bpchar(1)")
    private String apiKeyVerify;

}
