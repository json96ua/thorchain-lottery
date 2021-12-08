package com.thorchain.lottery.model;

import java.util.List;

public class Player {
    private String playerAddress;
    private String playerNickname;
    private int ticketsAmount;
    private List<Ticket> tickets;

    public Player(String playerAddress, String playerNickname) {
        this.playerAddress = playerAddress;
        this.playerNickname = playerNickname;
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

    public int getTicketsAmount() {
        return ticketsAmount;
    }

    public void setTicketsAmount(int ticketsAmount) {
        this.ticketsAmount = ticketsAmount;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerAddress='" + playerAddress + '\'' +
                ", playerNickname='" + playerNickname + '\'' +
                ", tickets=" + tickets +
                '}';
    }
}
