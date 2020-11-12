package service.implementation;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class ResponseServiceImpl {

    final String token = "Bearer jHC2ewoaAQN3BbErxjrlKWcEk8U9P7";

    public String getResponseFromJira(String uri) {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(uri)
                .method("GET", null)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", token)
                .build();
        Response response;
        String responseBody;

        try {
            response = client.newCall(request).execute();
            responseBody = response.body().string();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return responseBody;
    }
}
