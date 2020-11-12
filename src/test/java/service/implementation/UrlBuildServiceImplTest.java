package service.implementation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UrlBuildServiceImplTest {

    UrlBuildServiceImpl urlBuildService;
    String givenUrl = "https://api.tempo.io/core/3/worklogs?from=2020-10-28&to=2020-10-31&offset=0&limit=100&project=AP";

    @BeforeEach
    void setUp() {
        urlBuildService = new UrlBuildServiceImpl();
    }

    @Test
    void response() {

        LocalDate from = LocalDate.of(2020, 10, 28);
        LocalDate to = LocalDate.of(2020, 10, 31);
        int offset = 0;
        int limit = 100;

        String url = urlBuildService.buildUri(from, to, offset, limit, "AP");
        System.out.println(url);

        assertEquals(url, givenUrl);


    }

}
