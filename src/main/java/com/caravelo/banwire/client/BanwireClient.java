package com.caravelo.banwire.client;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class BanwireClient
{
    private static final Logger log = LoggerFactory.getLogger(BanwireClient.class);

    private final String host;
    private final String user;

    public BanwireClient(String host, String user) throws BanwireClientException
    {
        log.debug("Initialising Banwire client: [host: {}, user: {}]", host, user);

        if (host == null || host.isEmpty()) {
            throw new BanwireClientException("The <host> argument is required");
        }
        if (user == null || user.isEmpty()) {
            throw new BanwireClientException("The <user> argument is required");
        }

        this.host = host;
        this.user = user;
    }

    public TokenisedCard tokenise(Map<String, Object> parameters) throws BanwireClientException
    {
        return tokenise(parameters, false);
    }

    public TokenisedCard tokenise(Map<String, Object> parameters, boolean cardExists) throws BanwireClientException
    {
        log.debug("Requesting tokenisation of card with details: {}", parameters);

        if (parameters == null || parameters.isEmpty()) {
            throw new BanwireClientException("You must specify a parameters map");
        }

        try {
            Map<String, Object> queryString = new HashMap<>();
            queryString.put("action", "card");
            if (cardExists) {
                queryString.put("exists", "1");
            }

            HttpResponse<JsonNode> response = Unirest
                    .post(host)
                    .queryString(queryString)
                    .field("method", "add")
                    .field("user", user)
                    .field("number", parameters.get("number"))
                    .field("exp_month", parameters.get("exp_month"))
                    .field("exp_year", parameters.get("exp_year"))
                    .field("cvv", parameters.get("cvv"))
                    .field("name", parameters.get("name"))
                    .field("address", parameters.get("address"))
                    .field("postal_code", parameters.get("postal_code"))
                    .field("email", parameters.get("email"))
                    .field("phone", parameters.get("phone"))
                    .asJson();

            JSONObject body = response.getBody().getObject();
            log.debug("Card tokenisation result: {}", body);

            TokenisedCard tc = new TokenisedCard();
            tc.setId(body.getString("id"));
            tc.setToken(body.getString("token"));
            tc.setTask(body.getString("task"));
            tc.setResult(body.getBoolean("result"));
            tc.setExists(body.getBoolean("exists"));
            JSONObject cardNode = body.getJSONObject("card");
            tc.setType(cardNode.getString("type"));
            tc.setCategory(cardNode.getString("category"));

            return tc;
        } catch (UnirestException e) {
            if (log.isDebugEnabled()) {
                log.error("Card tokenisation failed", e);
            }
            throw new BanwireClientException(e);
        }
    }

    public Purchase purchase(Map<String, Object> parameters) throws BanwireClientException
    {
        log.debug("Requesting purchase with parameters: {}", parameters);

        if (parameters == null || parameters.isEmpty()) {
            throw new BanwireClientException("You must specify a parameters map");
        }

        try {
            HttpResponse<JsonNode> response = Unirest
                    .post(host)
                    .queryString("action", "card")
                    .field("method", "payment")
                    .field("user", user)
                    .field("reference", parameters.get("reference"))
                    .field("token", parameters.get("token"))
                    .field("amount", parameters.get("amount"))
                    .asJson();

            JSONObject body = response.getBody().getObject();
            log.debug("Purchase result: {}", body);

            Purchase purchase = new Purchase();
            purchase.setId(body.getString("id"));
            purchase.setTransactionId(body.getString("id_transaction"));
            purchase.setAuthCode(body.getString("auth_code"));
            purchase.setReference(body.getString("reference"));
            purchase.setDescription(body.getString("description"));
            purchase.setAmount(body.getBigDecimal("amount"));

            return purchase;
        } catch (UnirestException e) {
            if (log.isDebugEnabled()) {
                log.error("Purchase failed", e);
            }
            throw new BanwireClientException(e);
        }
    }

}
