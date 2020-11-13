package service.implementation;

import java.time.LocalDate;
import java.util.*;

public class UrlBuildServiceImpl {

    final String firstSeparator = "&";
    final String url = "";
    Map<String, String> newList = new LinkedHashMap<>();

    public String buildUri(LocalDate from, LocalDate to, int offSet, int limit, String projectKey) {


        newList.put("from", String.valueOf(from));
        newList.put("to", String.valueOf(to));
        newList.put("offset", String.valueOf(offSet));
        newList.put("limit", String.valueOf(limit));
        newList.put("project", projectKey);

        return url + buildQueryParams();
    }

        public StringBuilder buildQueryParams(){
        StringBuilder quaryParams = new StringBuilder();

        for (Map.Entry<String, String> entry : newList.entrySet()) {

            quaryParams
                    .append(entry.getKey())
                    .append("=")
                    .append(entry.getValue())
                    .append(firstSeparator);
        }

        quaryParams.setLength(quaryParams.length() - 1);

        return quaryParams;
    }

}
