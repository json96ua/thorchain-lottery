package com.thorchain.lottery.service;

import com.thorchain.lottery.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    private TicketService ticketService;

    public void setUpPlayer(Player player) {
        player.setTickets(ticketService.generateTicketsForPlayer(player));
    }

    @Autowired
    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }
}
