package service.implementation;

import com.squareup.okhttp.HttpUrl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UrlBuildServiceImplTest {

    BuildTempoUrl urlBuildService;


    @BeforeEach
    void setUp() {
        urlBuildService = new BuildTempoUrl();
    }

    @Test
    void response() {

        HttpUrl expectedResult = HttpUrl.parse("https://api.tempo.io/core/3/worklogs?from=2020-10-28&to=2020-10-31&offset=0&limit=100&project=AP");
        LocalDate from = LocalDate.of(2020, 10, 28);
        LocalDate to = LocalDate.of(2020, 10, 31);
        int offset = 0;
        int limit = 100;

        HttpUrl url = urlBuildService.buildUrl(from, to, offset, limit, "AP");

        assertEquals(url, expectedResult);


    }

}
