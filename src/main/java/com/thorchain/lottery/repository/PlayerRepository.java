package com.thorchain.lottery.repository;

import com.thorchain.lottery.model.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {
    Player getPlayerByPlayerAddress(String playerAddress);
}
