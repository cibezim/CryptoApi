package com.crypto.cyptoapi;

public class Ticker {
    private Double ask;
    private Double bid;

    public Ticker() {

    }

    public Ticker(Double ask, Double bid) {
        this.ask = ask;
        this.bid = bid;
    }

    public Double getAsk() {
        return ask;
    }

    public void setAsk(Double ask) {
        this.ask = ask;
    }

    public Double getBid() {
        return bid;
    }

    public void setBid(Double bid) {
        this.bid = bid;
    }
}


