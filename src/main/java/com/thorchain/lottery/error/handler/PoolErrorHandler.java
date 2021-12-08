package com.thorchain.lottery.error.handler;

import com.thorchain.lottery.error.exception.NoSuchPoolException;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;

public class PoolErrorHandler extends DefaultResponseErrorHandler {
    private String poolName;

    public PoolErrorHandler(String poolName) {
        this.poolName = poolName;
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
//        throw new NoSuchPoolException(String.format("The pool %s cannot be found.", poolName));
    }
}