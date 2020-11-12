package service.implementation;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FetchUserData {

    Map<String, String> map = new HashMap<>();
    final String url = "https://redasrimkus.atlassian.net/rest/api/3/users/search";
    final String token = "Basic cmVkYXMucmlta3VzQHRlbGVzb2Z0YXMuY29tOk9sUURuUjRSNVFBMDd0c1hGN0k0QTUwQg==";

    public String getResponse() {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .method("GET", null)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", token)
                .build();
        Response response;
        String myResponse;

        try {
            response = client.newCall(request).execute();
            myResponse = response.body().string();
            parseUserData(myResponse);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return myResponse;
    }

    public void parseUserData(String response) {

        ObjectMapper mapper = new ObjectMapper();

        JsonNode actualObj;

        try {
            actualObj = mapper.readTree(response);
            for (JsonNode node : actualObj) {
                JsonNode accountIdNode = node.get("accountId");
                JsonNode emailAddressNode = node.get("emailAddress");

                if (accountIdNode != null && emailAddressNode != null) {
                    String accountId = accountIdNode.toString();
                    String emailAddress = emailAddressNode.toString();
                    map.put(accountId, emailAddress);
                }
            }
            System.out.println(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public String getUserEmailByAccountId(String accountID) {
        return map.get(accountID);
    }
}