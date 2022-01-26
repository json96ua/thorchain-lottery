package com.thorchain.lottery.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Player {
    private String playerAddress;
    private int ticketsAmount;
    private List<Ticket> tickets;
    private List<Lottery> lotteries;

    public Player(String playerAddress) {
        this.playerAddress = playerAddress;
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
                ", ticketsAmount=" + ticketsAmount +
                ", tickets=" + tickets +
                ", lotteries=" + lotteries +
                '}';
    }
}
