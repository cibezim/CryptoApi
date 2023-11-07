package com.crypto.cyptoapi;


public class TickerInfo {

    private Ticker ticker;

    public TickerInfo() {

    }

    public TickerInfo(Ticker ticker) {
        this.ticker = ticker;
    }

    public Ticker getTicker() {
        return ticker;
    }

    public void setTicker(Ticker ticker) {
        this.ticker = ticker;
    }
}