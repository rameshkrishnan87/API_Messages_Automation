package com.api.stepdefinition;

import io.cucumber.gherkin.internal.com.eclipsesource.json.JsonObject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import com.api.utlis.TestContext;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class getListOfMessagesStepDef {
    private TestContext context;
    private static final Logger LOG = LogManager.getLogger(getListOfMessagesStepDef.class);

    public getListOfMessagesStepDef(TestContext context) {
        this.context = context;
    }

    @Given("user has access to endpoint {string}")
    public void userHasAccessToEndpoint(String endpoint) {
        context.session.put("endpoint", endpoint);
    }

    @When("user makes a request to view list of messages")
    public void userMakeRequestToViewMessages() {
        context.response = context.requestSetup().when().get(context.session.get("endpoint").toString());
        List<JsonObject> messages = (context.response.getBody().jsonPath().getList("messages"));
        JsonPath js = new JsonPath(context.response.getBody().asString());
        int message_count = js.getInt("messages.size()");
        for(int i=0;i<message_count;i++){
            int id = js.getInt("messages["+i+"].id");
            System.out.println("Message ID:"+id);
            assertNotNull("Message ID not found!", id);
        }

    }

    @Then("user should get the response code {int}")
    public void userShouldGetTheResponseCode(Integer statusCode) {
        assertEquals(Long.valueOf(statusCode), Long.valueOf(context.response.getStatusCode()));
    }
}
