package com.thorchain.lottery.error.handler;

import com.thorchain.lottery.error.exception.NoSuchPlayerInPoolException;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;

public class PlayerErrorHandler extends DefaultResponseErrorHandler {
    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        throw new NoSuchPlayerInPoolException("The user doesn't participate in any pool.");
    }
}
