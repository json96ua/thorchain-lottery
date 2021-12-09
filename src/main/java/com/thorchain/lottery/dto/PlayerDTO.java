package com.thorchain.lottery.dto;

public class PlayerDTO {
    private String playerAddress;
    private String playerNickname;

    public PlayerDTO() {
    }

    public String getPlayerAddress() {
        return playerAddress;
    }

    public void setPlayerAddress(String playerAddress) {
        this.playerAddress = playerAddress;
    }

    public String getPlayerNickname() {
        return playerNickname;
    }

    public void setPlayerNickname(String playerNickname) {
        this.playerNickname = playerNickname;
    }
}
