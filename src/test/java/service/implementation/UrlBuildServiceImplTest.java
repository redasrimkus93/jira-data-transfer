package service.implementation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UrlBuildServiceImplTest {

    UrlBuildServiceImpl urlBuildService;


    @BeforeEach
    void setUp() {
        urlBuildService = new UrlBuildServiceImpl();
    }

    @Test
    void response() {

        String expectedResult = "";
        LocalDate from = LocalDate.of(2020, 10, 28);
        LocalDate to = LocalDate.of(2020, 10, 31);
        int offset = 0;
        int limit = 100;

        String url = urlBuildService.buildUri(from, to, offset, limit, "AP");

        assertEquals(url, expectedResult);


    }

}
