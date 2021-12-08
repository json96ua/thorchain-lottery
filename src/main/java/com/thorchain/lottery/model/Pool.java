package com.thorchain.lottery.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigInteger;

public class Pool {
    private String pool;
    @JsonFormat(shape= JsonFormat.Shape.NUMBER)
    private long liquidityUnits;

    public Pool() {
    }

    public String getPool() {
        return pool;
    }

    public void setPool(String pool) {
        this.pool = pool;
    }

    public long getLiquidityUnits() {
        return liquidityUnits;
    }

    public void setLiquidityUnits(long liquidityUnits) {
        this.liquidityUnits = liquidityUnits;
    }
}
