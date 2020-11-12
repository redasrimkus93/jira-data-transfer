package service.implementation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
