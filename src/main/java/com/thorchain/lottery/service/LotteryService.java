package com.thorchain.lottery.service;

import com.thorchain.lottery.model.Lottery;
import com.thorchain.lottery.model.Player;
import com.thorchain.lottery.repository.LotteryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LotteryService {
    private LotteryRepository lotteryRepository;

    public Lottery createNewLottery() {
        return lotteryRepository.save(new Lottery());
    }

    public void addPlayerToLottery(Player player, Lottery lottery) {
        lottery.getPlayers().add(player);
        lotteryRepository.save(lottery);
    }

    public void addPlayersToLottery(List<Player> players, Lottery lottery) {
        lottery.getPlayers().addAll(players);
        lotteryRepository.save(lottery);
    }

    public Lottery getLotteryById(UUID lotteryId) {
        return lotteryRepository.getByLotteryId(lotteryId);
    }

    @Autowired
    public void setLotteryRepository(LotteryRepository lotteryRepository) {
        this.lotteryRepository = lotteryRepository;
    }
}
