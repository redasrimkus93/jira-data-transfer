package service.implementation;

import contract.Worklog;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.BasicConfigurator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collection;
@Log4j
public class FetchWorklogTest {

    FetchWorklog fetchWorklog;

    @BeforeEach
    void setUp() {
        final String jiraDomain = "redasrimkus";
        final String jiraToken = "Basic cmVkYXMucmlta3VzQHRlbGVzb2Z0YXMuY29tOk9sUURuUjRSNVFBMDd0c1hGN0k0QTUwQg==";
        final String tempoToken = "Bearer jHC2ewoaAQN3BbErxjrlKWcEk8U9P7";
        FetchTempoResponse fetchTempoResponse = new FetchTempoResponse(tempoToken);
        FetchJiraUserData fetchJiraUserData = new FetchJiraUserData(new ParseJiraUserData(), jiraDomain, jiraToken);
        fetchWorklog = new FetchWorklog(fetchTempoResponse, fetchJiraUserData);
    }

    @Test
    void response() {
        BasicConfigurator.configure();
        LocalDate from = LocalDate.of(2020, 10, 20);
        LocalDate to = LocalDate.of(2020, 10, 31);

        Collection<Worklog> worklogs;
        worklogs = fetchWorklog.fetchWorklogs(from, to, "TP", "AP");

        log.info(worklogs);

    }
}



