package com.thorchain.lottery.controller;

import com.thorchain.lottery.dto.PlayerDTO;
import com.thorchain.lottery.dto.PlayerToLotteryDTO;
import com.thorchain.lottery.error.exception.NoSuchPlayerInPoolException;
import com.thorchain.lottery.error.exception.NoSuchPoolException;
import com.thorchain.lottery.error.exception.NoValidPoolsForPlayerException;
import com.thorchain.lottery.model.Player;
import com.thorchain.lottery.service.LotteryService;
import com.thorchain.lottery.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class MainController {
    private PlayerService playerService;
    private LotteryService lotteryService;

    @Autowired
    public void setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
    }

    @Autowired
    public void setLotteryService(LotteryService lotteryService) {
        this.lotteryService = lotteryService;
    }

    @PostMapping("/create/player")
    public Player addPlayer(@RequestBody PlayerDTO playerDTO) {
        return playerService.savePlayer(playerService.createNewPlayer(playerDTO));
    }

    @GetMapping("/getAllPlayers")
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @PostMapping("/create/lottery")
    public UUID addLottery() {
        return lotteryService.createNewLottery();
    }

    @PostMapping("/player/add/lottery")
    public void addPlayerToLottery(@RequestBody PlayerToLotteryDTO dto) {
        playerService.addPlayerToLottery(dto);
    }

    @ExceptionHandler(NoSuchPlayerInPoolException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNoSuchPlayerInPoolException(NoSuchPlayerInPoolException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @ExceptionHandler(NoValidPoolsForPlayerException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNoValidPoolsForPlayerException(NoValidPoolsForPlayerException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @ExceptionHandler(NoSuchPoolException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNoSuchPoolException(NoSuchPoolException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }
}
