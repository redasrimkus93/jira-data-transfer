package contract;

import java.time.LocalDate;
import java.util.Collection;

public interface FetchWorklogs {
    Collection<Worklog> fetchWorklogs(LocalDate from, LocalDate to, String... projectKeys);
}
