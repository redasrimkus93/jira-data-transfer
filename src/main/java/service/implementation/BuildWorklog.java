package service.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import contract.Worklog;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public class BuildWorklog {

    ObjectMapper mapper = new ObjectMapper();


    public Collection<Worklog> buildWorklog(String response) {
        Collection<Worklog> ourWorklogs = new ArrayList<>();

        try {
            JsonNode actualObj = mapper.readTree(response);
            JsonNode jsonNode = actualObj.get("results");

            for (JsonNode node : jsonNode) {
                Worklog worklog = new Worklog();

                worklog.setTimeSpentSeconds(node.get("timeSpentSeconds").asInt());
                worklog.setDescription(node.get("description").toString());
                worklog.setStartDate(LocalDate.parse(node.get("startDate").asText()));

                JsonNode issueNode = node.get("issue");
                worklog.setIssueID(issueNode.get("key").toString());

                JsonNode accountIdNode = node.get("author");
                worklog.setAccountId(accountIdNode.get("accountId").toString());

                ourWorklogs.add(worklog);
            }

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);

        }
        return ourWorklogs;

    }
}
