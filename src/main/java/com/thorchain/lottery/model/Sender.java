package com.thorchain.lottery.model;

import java.math.BigInteger;

public class Sender {
    private String senderAddress;
    private BigInteger value;

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public BigInteger getValue() {
        return value;
    }

    public void setValue(BigInteger value) {
        this.value = value;
    }
}
