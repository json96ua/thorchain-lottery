package com.thorchain.lottery.thorchain.service;

import com.thorchain.lottery.common.Constants;
import com.thorchain.lottery.error.exception.NoValidPoolsForPlayerException;
import com.thorchain.lottery.error.handler.PlayerErrorHandler;
import com.thorchain.lottery.error.handler.PoolErrorHandler;
import com.thorchain.lottery.model.Pool;
import com.thorchain.lottery.model.PoolItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.UnknownContentTypeException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ThorchainAPIService {
    private final static String API_URL = "https://testnet.midgard.thorchain.info/v2/";
    private final static String MEMBER_URL = "member/";
    private final static String POOL_URL = "pool/";

    private RestTemplate restTemplate;

    public boolean isPoolParticipant(String playerAddress) {
        restTemplate.setErrorHandler(new PlayerErrorHandler());
        ResponseEntity<PoolItem> response = restTemplate.getForEntity(API_URL + MEMBER_URL + playerAddress, PoolItem.class);
        return response.getBody().getPools().size() > 0;
    }

    public List<Pool> getPlayerPools(String playerAddress) {
        restTemplate.setErrorHandler(new PlayerErrorHandler());
        ResponseEntity<PoolItem> response = restTemplate.getForEntity(API_URL + MEMBER_URL + playerAddress, PoolItem.class);
        return response.getBody().getPools();
    }

    public List<Pool> getValidPlayerPools(String playerAddress) {
        List<Pool> validPools = new ArrayList<>();
        for (Pool pool : getPlayerPools(playerAddress)) {
            if (pool.getLiquidityUnits() / Constants.DISCHARGE > Constants.AVAILABLE_LPU_AMOUNT) {
                validPools.add(pool);
            }
        }
        if (validPools.isEmpty()) {
            throw new NoValidPoolsForPlayerException("There aren't any pools with liquidity more than 10 for this user.");
        }
        return validPools;
    }

    public Pool getPoolInfoByName(String poolName) throws UnknownContentTypeException {
        restTemplate.setErrorHandler(new PoolErrorHandler(poolName));
        ResponseEntity<Pool> response = restTemplate.getForEntity(API_URL + POOL_URL + poolName, Pool.class);
        return response.getBody();
    }


    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
