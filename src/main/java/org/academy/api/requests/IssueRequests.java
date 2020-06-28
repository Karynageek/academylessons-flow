package org.academy.api.requests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.academy.api.Resources;

import java.util.List;

@Slf4j
public class IssueRequests extends AbstractRequests {
    private Response response;
    public void getIssuesRequest(String token, int responseCode) {
        response = getRequests.withToken(token, responseCode, Resources.getIssueRequests());
        log.info(response.asString());
    }
    public String getIssuesTitleRequest() {
        JsonPath jsonPath = response.jsonPath();
        List<String> title= jsonPath.getList("title");
        return title.get(0);
    }
}
