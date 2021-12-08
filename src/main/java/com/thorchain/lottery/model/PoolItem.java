package com.thorchain.lottery.model;

import java.util.List;

public class PoolItem {
    private List<Pool> pools;

    public PoolItem() {
    }

    public List<Pool> getPools() {
        return pools;
    }

    public void setPools(List<Pool> pools) {
        this.pools = pools;
    }
}
