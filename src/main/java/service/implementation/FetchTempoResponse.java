package service.implementation;

import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.time.LocalDate;


public class FetchTempoResponse {
    private final String tempoToken;

    public FetchTempoResponse(String tempoToken) {
        this.tempoToken = tempoToken;
    }

    public HttpUrl buildUrl(LocalDate from, LocalDate to, int offSet, int limit, String projectKey) {

        HttpUrl url = HttpUrl.parse("https://api.tempo.io/core/3/worklogs").newBuilder()
                .addQueryParameter("from", String.valueOf(from))
                .addQueryParameter("to", String.valueOf(to))
                .addQueryParameter("offset", String.valueOf(offSet))
                .addQueryParameter("limit", String.valueOf(limit))
                .addQueryParameter("project", String.valueOf(projectKey))
                .build();

        return url;
    }

    public String getResponseFromTempo(HttpUrl url) {


        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", tempoToken)
                .build();
        Response response;
        String responseBody;

        try {
            response = client.newCall(request).execute();
            responseBody = response.body().string();


        } catch (IllegalArgumentException | IOException e) {
            throw new InvalidUrlException("Invalid URL", e);
        }
        return responseBody;
    }
}
