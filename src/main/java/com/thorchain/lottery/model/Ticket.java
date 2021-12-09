package com.thorchain.lottery.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class Ticket {
    private Long ticketId;
    private String codeOfTicket;
    private Player ticketOwner;

    public Ticket(String codeOfTicket, Player ticketOwner) {
        this.codeOfTicket = codeOfTicket;
        this.ticketOwner = ticketOwner;
    }

    public Ticket() {

    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getTicketId() {
        return ticketId;
    }

    public String getCodeOfTicket() {
        return codeOfTicket;
    }

    public void setCodeOfTicket(String codeOfTicket) {
        this.codeOfTicket = codeOfTicket;
    }

    @ManyToOne(fetch= FetchType.LAZY, cascade=CascadeType.ALL)
    @JsonBackReference
    public Player getTicketOwner() {
        return ticketOwner;
    }

    public void setTicketOwner(Player ticketOwner) {
        this.ticketOwner = ticketOwner;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", codeOfTicket='" + codeOfTicket + '\'' +
                ", ticketOwner=" + ticketOwner +
                '}';
    }
}
