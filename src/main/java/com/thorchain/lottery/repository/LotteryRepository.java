package com.thorchain.lottery.repository;

import com.thorchain.lottery.model.Lottery;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LotteryRepository extends CrudRepository<Lottery, UUID> {
    Lottery getByLotteryId(UUID lotteryId);
}
