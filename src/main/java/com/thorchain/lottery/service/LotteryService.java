package com.thorchain.lottery.service;

import com.thorchain.lottery.model.Lottery;
import com.thorchain.lottery.repository.LotteryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LotteryService {
    private LotteryRepository lotteryRepository;

    public UUID createNewLottery() {
        Lottery lottery = lotteryRepository.save(new Lottery());
        return lottery.getLotteryId();
    }

    public Lottery getLotteryById(UUID lotteryId) {
        return lotteryRepository.getByLotteryId(lotteryId);
    }

    @Autowired
    public void setLotteryRepository(LotteryRepository lotteryRepository) {
        this.lotteryRepository = lotteryRepository;
    }
}
