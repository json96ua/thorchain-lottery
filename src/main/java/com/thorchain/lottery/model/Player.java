package com.thorchain.lottery.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Player {
    private String playerAddress;
    private String playerNickname;
    private int ticketsAmount;
    private List<Ticket> tickets;
    private List<Lottery> lotteries;

    public Player(String playerAddress, String playerNickname) {
        this.playerAddress = playerAddress;
        this.playerNickname = playerNickname;
    }

    public Player() {

    }

    @Id
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

    @OneToMany(mappedBy = "ticketOwner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @ManyToMany
    public List<Lottery> getLotteries() {
        return lotteries;
    }

    public void setLotteries(List<Lottery> lotteries) {
        this.lotteries = lotteries;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerAddress='" + playerAddress + '\'' +
                ", playerNickname='" + playerNickname + '\'' +
                ", ticketsAmount=" + ticketsAmount +
                ", tickets=" + tickets +
                ", lotteries=" + lotteries +
                '}';
    }
}
