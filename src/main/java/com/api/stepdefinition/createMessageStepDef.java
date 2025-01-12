package com.api.stepdefinition;

import static org.junit.Assert.*;

import java.util.Map;

import com.api.model.CreateMessageModel;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.module.jsv.JsonSchemaValidator;
import lombok.extern.java.Log;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.api.utlis.ResponseHandler;
import com.api.utlis.TestContext;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.assertNotNull;

public class createMessageStepDef {
    private TestContext context;
    private static final Logger LOG = LogManager.getLogger(createMessageStepDef.class);

    public createMessageStepDef(TestContext context) {
        this.context = context;
    }

    @When("user creates a message")
    public void userCreatesABooking(DataTable dataTable) {
        Map<String,String> messageData = dataTable.asMaps().get(0);
        JSONObject messageBody = new JSONObject();
        messageBody.put("name", messageData.get("name"));
        messageBody.put("email", messageData.get("email"));
        messageBody.put("phone",messageData.get("phone"));
        messageBody.put("subject", messageData.get("subject"));
        messageBody.put("description", messageData.get("description"));

        context.response = context.requestSetup().body(messageBody.toString())
                .when().post(context.session.get("endpoint").toString());

         CreateMessageModel createMessageModel = ResponseHandler.deserializedResponse(context.response, CreateMessageModel.class);
        assertNotNull("Message not created", createMessageModel);
        LOG.info("Newly created Message ID: "+createMessageModel.getMessageid());
        System.out.println("Newly created Message ID: "+createMessageModel.getMessageid());
         context.session.put("messageID", createMessageModel.getMessageid());
         validateMessageData(new JSONObject(messageData), createMessageModel);
    }

    private void validateMessageData(JSONObject messageData, CreateMessageModel createMessageModel) {
        LOG.info(messageData);

        assertEquals("First Name did not match", messageData.get("name"), createMessageModel.getName());
        assertEquals("Last Name did not match", messageData.get("email"), createMessageModel.getEmail());
        assertEquals("Total Price did not match", messageData.get("phone"), createMessageModel.getPhone());
        assertEquals("Deposit Paid did not match", messageData.get("subject"), createMessageModel.getSubject());
        assertEquals("Additional Needs did not match", messageData.get("description"), createMessageModel.getDescription());

    }

    @Then("user validates the response with JSON schema {string}")
    public void userValidatesResponseWithJSONSchema(String schemaFileName) {
        context.response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/"+schemaFileName));
        LOG.info("Successfully Validated schema from "+schemaFileName);
    }

}
