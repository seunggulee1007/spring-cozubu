package com.tp.cozubu.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "t_trade_history")
public class TradeHistory {
    @Id
    private int seq;

    @Column(length = 20)
    private String username;

    @Column(length = 5)
    private String coin;

    @Column(length = 10)
    private String model;

    @Column(length = 30)
    private String datetime;

    @Column(columnDefinition = "NUMERIC(16,8)")
    private double price;

    @Column(name = "trade_type", columnDefinition = "bpchar(1)")
    private String tradeType;

    @Column(name = "current_price", columnDefinition = "NUMERIC(11,2)")
    private double currentPrice;

    @Column(name = "coin_cnt", columnDefinition = "NUMERIC(13,8)")
    private double coinCnt;


}
