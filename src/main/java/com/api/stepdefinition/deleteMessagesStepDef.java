package com.api.stepdefinition;

import com.api.model.CreateMessageModel;
import com.api.utlis.ResponseHandler;
import io.cucumber.java.en.When;

import com.api.utlis.TestContext;


import io.cucumber.java.en.When;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;

public class deleteMessagesStepDef {
    private TestContext context;

    public deleteMessagesStepDef(TestContext context) {
        this.context = context;
    }

    @When("user makes a request to delete message")
    public void userMakesARequestToDeleteMessage() {
        Cookies allDetailedCookies =context.response.detailedCookies();
        Cookie token = allDetailedCookies.get("token");
        context.response = context.requestSetup()
                .cookie(token)
                .when().delete(context.session.get("endpoint")+"");
    }

//    @When("user makes a request to delete message by id")
//    public void userMakesARequestToDeleteMessageByID() {
//
//        Cookies allDetailedCookies =context.response.detailedCookies();
//        Cookie token = allDetailedCookies.get("token");
//        CreateMessageModel createMessageModel = ResponseHandler.deserializedResponse(context.response, CreateMessageModel.class);
//
//        context.response = context.requestSetup()
//                .cookie(token)
//                .pathParam("messageID", context.session.get("messageID"))
//                .when().delete(context.session.get("endpoint")+"{messageID}");
//    }
}