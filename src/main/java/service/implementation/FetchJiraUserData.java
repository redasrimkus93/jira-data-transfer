package service.implementation;

import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.Map;

public class FetchJiraUserData {

    private final ParseJiraUserData parseJiraUserData;
    private final String  jiraDomain;
    private final String jiraToken;

    public FetchJiraUserData(ParseJiraUserData parseJiraUserData, String jiraDomain, String jiraToken) {
        this.parseJiraUserData = parseJiraUserData;
        this.jiraDomain = jiraDomain;
        this.jiraToken = jiraToken;
    }

    public HttpUrl buildJiraUrl() {

        return new HttpUrl.Builder()
                .scheme("https")
                .host(jiraDomain + ".atlassian.net")
                .addPathSegment("rest")
                .addPathSegment("api")
                .addPathSegment("3")
                .addPathSegment("users")
                .addPathSegment("search")
                .build();
    }


    public String getResponse() {
        HttpUrl url = buildJiraUrl();
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", jiraToken)
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
        
        String response = getResponse();
        Map<String, String> map = parseJiraUserData.parseUserData(response);
        return map.get(accountID);
    }
}