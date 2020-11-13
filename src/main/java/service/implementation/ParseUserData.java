package service.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class ParseUserData {

    ObjectMapper mapper = new ObjectMapper();
    private final Map<String, String> map = new HashMap<>();
    public Map<String,String> parseUserData(String response) {

        JsonNode actualObj;

        try {
            actualObj = mapper.readTree(response);
            for (JsonNode node : actualObj) {
                JsonNode accountIdNode = node.get("accountId");
                JsonNode emailAddressNode = node.get("emailAddress");

                if (accountIdNode != null && emailAddressNode != null) {
                    String accountId = accountIdNode.toString();
                    String emailAddress = emailAddressNode.toString();
                    map.put(accountId, emailAddress);
                }
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return map;
    }
}
