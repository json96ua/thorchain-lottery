package com.thorchain.lottery.service;

import com.thorchain.lottery.common.Constants;
import com.thorchain.lottery.model.Player;
import com.thorchain.lottery.model.Pool;
import com.thorchain.lottery.model.Ticket;
import com.thorchain.lottery.thorchain.service.ThorchainAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.UnknownContentTypeException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TicketService {
    private ThorchainAPIService apiService;

    public List<Ticket> generateTicketsForPlayer(Player player) {
        List<Ticket> tickets = new ArrayList<>();
        for (int i = 0; i < calculateAmountOfTickets(player); i++) {
            tickets.add(
                    new Ticket(
                    player.getPlayerAddress() + UUID.randomUUID(),
                            player.getPlayerAddress())
            );
        }
        return tickets;
    }

    private int calculateAmountOfTickets(Player player) {
        List<Pool> pools = apiService.getValidPlayerPools(player.getPlayerAddress());
        int amountOfTickets = 0;
        for (Pool pool : pools) {
            int poolShare = 0;
            try {
                poolShare = calculatePoolShare(pool);
            } catch (UnknownContentTypeException ex) {
                continue;
            }
            amountOfTickets += pool.getLiquidityUnits() / Constants.DISCHARGE * poolShare;
        }
        return roundTickets(amountOfTickets);
    }

    private int roundTickets(int amountOfTickets) {
        if (amountOfTickets > 100) {
            return 100;
        } else if (amountOfTickets < 1) {
            return 1;
        }
        return amountOfTickets;
    }

    private int calculatePoolShare(Pool pool) {
        Pool poolInfoByName = apiService.getPoolInfoByName(pool.getPool());
        return Math.toIntExact(pool.getLiquidityUnits() / poolInfoByName.getLiquidityUnits() * 100);
    }

    @Autowired
    public void setApiService(ThorchainAPIService apiService) {
        this.apiService = apiService;
    }
}
