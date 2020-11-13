package service.implementation;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ResponseServiceImplTest {

    ResponseServiceImpl responseService;

    final String uri = "";

    @BeforeEach
    void setUp() {
        responseService = new ResponseServiceImpl();
    }

    @Test
    void response() {
        String response = responseService.getResponseFromJira(uri);


    }
}



