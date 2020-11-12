package contract;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Worklog {
    private String accountId;
    private String description;
    private int timeSpentSeconds;
    private LocalDate startDate;
    private String issueID;
    private String projectKey;

}
