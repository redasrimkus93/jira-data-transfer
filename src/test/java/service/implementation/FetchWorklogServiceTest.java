package service.implementation;

import contract.FetchWorklogs;
import contract.Worklog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collection;

public class FetchWorklogServiceTest {

    FetchWorklogs fetchWorklogs;

    @BeforeEach
    void setUp() {
        fetchWorklogs = new FetchWorklogService();
    }

    @Test
    void response() {
        LocalDate from = LocalDate.of(2020, 10, 25);
        LocalDate to = LocalDate.of(2020, 10, 31);

        Collection<Worklog> worklog;
        worklog = fetchWorklogs.fetchWorklogs(from, to, "TP");
        System.out.println(worklog.size());


    }

}
