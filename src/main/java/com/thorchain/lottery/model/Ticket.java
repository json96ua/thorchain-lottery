package com.thorchain.lottery.model;

public class Ticket {
    private String codeOfTicket;
    private String ticketOwnerId;

    public Ticket(String codeOfTicket, String ticketOwnerId) {
        this.codeOfTicket = codeOfTicket;
        this.ticketOwnerId = ticketOwnerId;
    }

    public String getCodeOfTicket() {
        return codeOfTicket;
    }

    public void setCodeOfTicket(String codeOfTicket) {
        this.codeOfTicket = codeOfTicket;
    }

    public String getTicketOwnerId() {
        return ticketOwnerId;
    }

    public void setTicketOwnerId(String ticketOwnerId) {
        this.ticketOwnerId = ticketOwnerId;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "codeOfTicket='" + codeOfTicket + '\'' +
                ", ticketOwnerId='" + ticketOwnerId + '\'' +
                '}';
    }
}
