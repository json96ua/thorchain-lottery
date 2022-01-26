package com.thorchain.lottery.explorer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.NullNode;
import com.thorchain.lottery.model.BlockInfo;
import com.thorchain.lottery.model.Sender;
import com.thorchain.lottery.model.Transaction;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class TransactionExplorer {

    private final static String API_URL = "https://thornode.thorchain.info/txs?tx.height=";
    private final static String LIMIT_PARAM = "&limit=1000";
    private final static String SEND_EVENT_NAME = "send";

    private ObjectMapper objectMapper;

    public List<Transaction> doExplore(int from, int to) {
        List<Transaction> transactions = new ArrayList<>();
        for (int blockHeight = from; blockHeight <= to; blockHeight++) {
            transactions.addAll(getBlockInfo(blockHeight).getTransactions());
        }
        return transactions;
    }

    private BlockInfo getBlockInfo(int blockHeight) {
        String url = API_URL + blockHeight + LIMIT_PARAM;
        String response = doRequest(url);
        BlockInfo blockInfo = new BlockInfo();

        try {
            JsonNode responseTree = objectMapper.readTree(response);
            ArrayNode txs = (ArrayNode) responseTree.findValue("txs");

            int totalCount = responseTree.findValue("total_count").asInt(0);
            List<Transaction> transactions = new ArrayList<>(totalCount);
            transactions.addAll(parseTransactionFromResponse(txs));

            blockInfo.setTotalTransactions(totalCount);
            blockInfo.setTransactions(transactions);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return blockInfo;
    }

    private Sender getSenderInfo(JsonNode tx) {
        Sender sender = new Sender();
        JsonNode value = tx.findValue("tx").findValue("value").findValue("msg").findValue("value");
        String fromAddress = value.findValue("from_address").asText();
        String amount = value.findValue("amount").findValue("amount").asText();

        sender.setSenderAddress(fromAddress);
        sender.setValue(new BigInteger(amount));
        return sender;
    }

    private List<Transaction> parseTransactionFromResponse(ArrayNode txs) {
        if (txs == null || txs.isEmpty()) {
            return Collections.emptyList();
        }
        List<Transaction> transactions = new ArrayList<>(txs.size());
        for (JsonNode tx : txs) {
            if (!(tx.findValue("logs") instanceof NullNode)) {
                JsonNode message = ((ArrayNode) tx.findValue("logs").findValue("events")).get(0);
                JsonNode action = ((ArrayNode) message.findValue("attributes")).get(0);
                String eventName = action.findValue("value").asText();

                if (SEND_EVENT_NAME.equals(eventName)) {
                    Transaction transaction = new Transaction();
                    transaction.setTransactionHash(tx.findValue("txhash").asText());
                    transaction.setEventName(eventName);
                    transaction.setSender(getSenderInfo(tx));
                    transactions.add(transaction);
                }
            }
        }
        return transactions;
    }

    private String doRequest(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            return new OkHttpClient().newCall(request).execute().body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Strings.EMPTY;
    }

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
}
