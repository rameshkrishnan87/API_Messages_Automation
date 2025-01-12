package com.api.stepdefinition;

import com.api.model.CreateMessageModel;
import com.api.utlis.ResponseHandler;
import io.cucumber.gherkin.internal.com.eclipsesource.json.JsonObject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
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
        JsonPath js = new JsonPath(context.response.getBody().asString());
        context.session.put("messageID", js.getInt("messages[0].id"));
    }

    @Then("user should get the response code {int}")
    public void userShouldGetTheResponseCode(Integer statusCode) {
        assertEquals(Long.valueOf(statusCode), Long.valueOf(context.response.getStatusCode()));
    }

    @When("user makes a request to view message details by id")
    public void userMakeRequestToViewMessageById() {
        context.response = context.requestSetup()
                .when().get(context.session.get("endpoint").toString());
//           List<JsonObject> messages = (context.response.getBody().jsonPath().getList("messages"));
//           JsonPath js = new JsonPath(context.response.getBody().asString());
//           int message_count = js.getInt("messages.size()");
//        for(int i=0;i<message_count;i++){
//            int id = js.getInt("messages["+i+"].id");
//            System.out.println("Message ID:"+id);
//            assertNotNull("Message ID not found!", id);
//        }

    }

    @When("user makes a request to view number of messages")
    public void userMakeRequestToViewNumberOfMessages(){
        context.response = context.requestSetup()
                .when().get(context.session.get("endpoint").toString());
    }

    @When("user makes a request to view message detail by id")
    public void userMakeRequestToViewMessageDetailById(){
        LOG.info("Session messageID: "+context.session.get("messageID"));
        CreateMessageModel createMessageModel = ResponseHandler.deserializedResponse(context.response, CreateMessageModel.class);
        context.response = context.requestSetup()
                .pathParam("messageID", context.session.get("messageID"))
                .when().get(context.session.get("endpoint") + "{messageID}");
        assertNotNull("Message Details Not Found ", createMessageModel);
        Cookies allDetailedCookies =context.response.detailedCookies();
        Cookie token = allDetailedCookies.get("token");
        System.out.print("%###Token in get message by id"+token);
        context.session.put("name", createMessageModel.getName());
        context.session.put("email", createMessageModel.getEmail());
        context.session.put("phone", createMessageModel.getPhone());
        context.session.put("subject", createMessageModel.getSubject());
        context.session.put("description", createMessageModel.getDescription());
    }

    @Then("user should see message details by {string}")
    public void userShouldGetTheMessageDetailById(String id) {
        JsonPath js = new JsonPath(context.response.getBody().asString());
        String actualMessageId = js.getString("messageid");
        System.out.println(actualMessageId);
        assertEquals(actualMessageId, id);
    }

    @Then("user should see message count")
    public void userShouldSeeTheMessageCount(){
        JsonPath js = new JsonPath(context.response.getBody().asString());
        String actualMessageCount= js.getString("count");
        System.out.println(actualMessageCount);
        assertNotNull("Message Count not found!", actualMessageCount);
    }
}
