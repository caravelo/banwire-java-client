package com.caravelo.banwire.client;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Map;

public class BanwireClient
{
    private static final String POST = "post";

    private static final String METHOD_PAYMENT = "payment";

    private final String host;
    private final String user;

    public BanwireClient(String host, String user) throws BanwireClientException
    {
        if (host == null || host.isEmpty()) {
            throw new BanwireClientException("The <host> argument is required");
        }
        if (user == null || user.isEmpty()) {
            throw new BanwireClientException("The <user> argument is required");
        }

        this.host = host;
        this.user = user;


    }

    public void purchase(Map<String, Object> parameters)
    {
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
            System.out.println("Body ==> " + body.toString());
            String id = body.getString("id");
            System.out.println("ID ==> " + id);
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

}
