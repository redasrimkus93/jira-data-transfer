package service.implementation;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ResponseServiceImplTest {

    ResponseServiceImpl responseService;

    final String uri = "https://api.tempo.io/core/3/worklogs";

    @BeforeEach
    void setUp() {
        responseService = new ResponseServiceImpl();
    }

    @Test
    void response() {
        String response = responseService.getResponseFromJira(uri);


    }
}



