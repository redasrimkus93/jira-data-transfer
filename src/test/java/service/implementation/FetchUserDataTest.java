package service.implementation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class FetchUserDataTest {
    FetchUserData fetchUserData;

    @BeforeEach
    void setUp() {
        fetchUserData = new FetchUserData();
    }

    @Test
    void response() {
        String response = fetchUserData.getResponse();

        
    }
}
