package service.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import contract.FetchWorklogs;
import contract.Worklog;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public class FetchWorklogService implements FetchWorklogs {

    final int offset = 0;
    final int limit = 1;

    private final ResponseServiceImpl responseService = new ResponseServiceImpl();
    private final UrlBuildServiceImpl urlBuildService = new UrlBuildServiceImpl();
    private final BuildWorklog buildWorklog = new BuildWorklog();
    ObjectMapper mapper = new ObjectMapper();
    private final FetchUserData fetchUserData = new FetchUserData();

    @Override
    public Collection<Worklog> fetchWorklogs(LocalDate from, LocalDate to, String... projectKeys) {
        Collection<Worklog> ourWorklogs = new ArrayList<>();

        for (String project : projectKeys) {

            String url = urlBuildService.buildUri(from, to, offset, limit, project);

            Collection<Worklog> projectWorklogs = fetch(url, new ArrayList<>());
            for (Worklog worklogs : projectWorklogs) {
                worklogs.setProjectKey(project);
                worklogs.setAccountId(fetchUserData.getUserEmailByAccountId(worklogs.getAccountId()));
            }
            ourWorklogs.addAll(projectWorklogs);
        }

        return ourWorklogs;
    }

    private Collection<Worklog> fetch(String url, Collection<Worklog> projectWorklogs) {

        String response = responseService.getResponseFromJira(url);

        projectWorklogs.addAll(buildWorklog.buildWorklog(response));

        String nextUrl = getNextLink(response);

        if (nextUrl != null) {
            return fetch(nextUrl, projectWorklogs);
        } else {
            return projectWorklogs;
        }
    }

    private String getNextLink(String response) {
        JsonNode actualObj;
        try {
            actualObj = mapper.readTree(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        JsonNode jsonNode = actualObj.get("metadata");
        if (jsonNode.get("next") != null) {
            return jsonNode.get("next").asText();
        } else {
            return null;
        }
    }


}

