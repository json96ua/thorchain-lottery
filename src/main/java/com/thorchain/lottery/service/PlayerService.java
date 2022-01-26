package com.thorchain.lottery.service;

import com.thorchain.lottery.model.Player;
import com.thorchain.lottery.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    private TicketService ticketService;
    private PlayerRepository repository;

    public Player createNewPlayer(String address) {
        Player player = new Player(address);
        player.setTickets(ticketService.generateTicketsForPlayer(player));
        return player;
    }

    public List<Player> getAllPlayers() {
        return (List<Player>) repository.findAll();
    }

    public Player getPlayerByAddress(String playerAddress) {
        return repository.getPlayerByPlayerAddress(playerAddress);
    }

    @Autowired
    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Autowired
    public void setRepository(PlayerRepository repository) {
        this.repository = repository;
    }
}
