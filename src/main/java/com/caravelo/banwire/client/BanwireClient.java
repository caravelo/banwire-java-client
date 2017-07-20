package com.caravelo.banwire.client;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    public Purchase purchase(Map<String, Object> parameters) throws BanwireClientException
    {
        log.debug("Requesting purchase with parameters: {}", parameters);

        if (parameters == null || parameters.isEmpty()) {
            throw new RuntimeException("You must specify a parameters map");
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
                log.error(e.getMessage(), e);
            }
            throw new BanwireClientException(e);
        }
    }

}
