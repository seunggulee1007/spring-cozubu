package com.tp.cozubu.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "t_trade_wallet")
public class TradeWallet {

    @EmbeddedId
    private TradeWalletPk tradeWalletPk;

    @Column(name = "coin_cnt", columnDefinition = "NUMERIC(13,8)")
    private double coinCnt;

    @Column(columnDefinition = "NUMERIC(16,8)")
    private double balance;

    @Column(name = "current_price", columnDefinition = "NUMERIC(11,2)")
    private double currentPrice;

    @Column(name = "del_yn", columnDefinition = "bpchar(1), default='N'")
    private String delYn;

    @Column(columnDefinition = "NUMERIC(16,8)")
    private double price;

    @Column(name = "stop_yn", columnDefinition = "bpchar(1), default='Y'")
    private String stopYn;

    @Data
    @Embeddable
    public static class TradeWalletPk implements Serializable {
        @Column(length = 20)
        private String username;
        @Column(length = 5)
        private String coin;
        @Column(length = 10)
        private String model;
    }
}
