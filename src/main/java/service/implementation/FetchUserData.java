package service.implementation;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FetchUserData {

    Map<String, String> map = new HashMap<>();
    final String url = "";
    final String token = "";
    private final ParseUserData parseUserData = new ParseUserData();

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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return myResponse;
    }

    public String getUserEmailByAccountId(String accountID) {
        map = parseUserData.parseUserData(getResponse());
        return map.get(accountID);
    }
}