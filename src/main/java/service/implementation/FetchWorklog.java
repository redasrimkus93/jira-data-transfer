package service.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.squareup.okhttp.HttpUrl;
import contract.FetchWorklogs;
import contract.Worklog;
import lombok.extern.log4j.Log4j;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@Log4j
public class FetchWorklog implements FetchWorklogs {

    private final FetchTempoResponse fetchTempoResponse;
    private final BuildTempoWorklog buildTempoWorklog = new BuildTempoWorklog();
    private final FetchJiraUserData fetchJiraUserData;

    public FetchWorklog(FetchTempoResponse fetchTempoResponse, FetchJiraUserData fetchJiraUserData) {
        this.fetchJiraUserData = fetchJiraUserData;
        this.fetchTempoResponse = fetchTempoResponse;
    }


    @Override
    public Collection<Worklog> fetchWorklogs(LocalDate from, LocalDate to, String... projectKeys) {
        Collection<Worklog> ourWorklogs = new ArrayList<>();

        for (String project : projectKeys) {

            int offset = 0;
            int limit = 1;
            HttpUrl url = fetchTempoResponse.buildUrl(from, to, offset, limit, project);

            Collection<Worklog> projectWorklogs = fetch(url, new ArrayList<>());
            for (Worklog worklogs : projectWorklogs) {
                worklogs.setProjectKey(project);
                worklogs.setAccountId(fetchJiraUserData.getUserEmailByAccountId(worklogs.getAccountId()));
            }
            ourWorklogs.addAll(projectWorklogs);
        }
        return ourWorklogs;
    }


    private Collection<Worklog> fetch(HttpUrl url, Collection<Worklog> projectWorklogs) {

        String response = fetchTempoResponse.getResponseFromTempo(url);

        projectWorklogs.addAll(buildTempoWorklog.buildWorklog(response));

        HttpUrl nextUrl = getNextLink(response);

        if (nextUrl != null) {
            return fetch(nextUrl, projectWorklogs);
        } else {
            return projectWorklogs;
        }
    }

    private HttpUrl getNextLink(String response) {
        JsonNode actualObj;
        try {
            actualObj = Utils.mapper.readTree(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        JsonNode jsonNode = actualObj.get("metadata");
        if (jsonNode.get("next") != null) {
            String url = jsonNode.get("next").asText();
            return HttpUrl.parse(url);
        } else {
            return null;
        }
    }
}

