package com.thorchain.lottery.dto;

public class PlayerToLotteryDTO {
    private String playerAddress;
    private String lotteryId;

    public PlayerToLotteryDTO() {
    }

    public String getPlayerAddress() {
        return playerAddress;
    }

    public void setPlayerAddress(String playerAddress) {
        this.playerAddress = playerAddress;
    }

    public String getLotteryId() {
        return lotteryId;
    }

    public void setLotteryId(String lotteryId) {
        this.lotteryId = lotteryId;
    }
}
