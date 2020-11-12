package service.implementation;

import java.time.LocalDate;
import java.util.*;

public class UrlBuildServiceImpl {

    public String buildUri(LocalDate from, LocalDate to, int offSet, int limit, String projectKey) {
        final String firstSeparator = "&";

        String url = "https://api.tempo.io/core/3/worklogs?";

        Map<String, String> newList = new LinkedHashMap<>();
        newList.put("from", String.valueOf(from));
        newList.put("to", String.valueOf(to));
        newList.put("offset", String.valueOf(offSet));
        newList.put("limit", String.valueOf(limit));
        newList.put("project", projectKey);

        StringBuilder quaryParams = new StringBuilder();

        for (Map.Entry<String, String> entry : newList.entrySet()) {

            quaryParams.append(entry.getKey()).append("=").append(entry.getValue()).append(firstSeparator);
        }
        if (quaryParams.length() > 0)
            quaryParams.setLength(quaryParams.length() - 1);

        return url + quaryParams;
    }

}
