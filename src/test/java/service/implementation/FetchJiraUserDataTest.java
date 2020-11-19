package service.implementation;

import com.squareup.okhttp.HttpUrl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class FetchJiraUserDataTest {
    FetchJiraUserData fetchJiraUserData;

    @BeforeEach
    void setUp() {

    }

    @Test
    void response() {
        String jiraDomain = "redasrimkus";
        HttpUrl response = fetchJiraUserData.buildJiraUrl();
        System.out.println(response);
    }
}
