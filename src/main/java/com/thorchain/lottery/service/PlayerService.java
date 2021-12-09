package com.thorchain.lottery.service;

import com.thorchain.lottery.dto.PlayerDTO;
import com.thorchain.lottery.dto.PlayerToLotteryDTO;
import com.thorchain.lottery.model.Lottery;
import com.thorchain.lottery.model.Player;
import com.thorchain.lottery.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PlayerService {

    private TicketService ticketService;
    private LotteryService lotteryService;
    private PlayerRepository repository;

    public Player createNewPlayer(PlayerDTO dto) {
        Player player = new Player(dto.getPlayerAddress(), dto.getPlayerNickname());
        player.setTickets(ticketService.generateTicketsForPlayer(player));
        return player;
    }

    public Player savePlayer(Player player) {
        return repository.save(player);
    }

    public List<Player> getAllPlayers() {
        return (List<Player>) repository.findAll();
    }

    public Player getPlayerByAddress(String playerAddress) {
        return repository.getPlayerByPlayerAddress(playerAddress);
    }

    public void addPlayerToLottery(PlayerToLotteryDTO dto) {
        Lottery lottery = lotteryService.getLotteryById(UUID.fromString(dto.getLotteryId()));
        Player player = getPlayerByAddress(dto.getPlayerAddress());
        List<Lottery> lotteries = player.getLotteries();
        lotteries.add(lottery);
        player.setLotteries(lotteries);
        repository.save(player);
    }

    @Autowired
    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Autowired
    public void setLotteryService(LotteryService lotteryService) {
        this.lotteryService = lotteryService;
    }

    @Autowired
    public void setRepository(PlayerRepository repository) {
        this.repository = repository;
    }
}
