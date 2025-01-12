package com.api.stepdefinition;

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

    @When("user makes a request to delete message with basic auth {string} & {string}")
    public void userMakesARequestToDeleteBookingWithBasicAuth(String username, String password) {
        Cookies allDetailedCookies =context.response.detailedCookies();
        Cookie token = allDetailedCookies.get("token");
        context.response = context.requestSetup()
//                .auth().preemptive().basic(username, password)
//                .pathParam("bookingID", context.session.get("bookingID"))
                .cookie(token)
                .when().delete(context.session.get("endpoint")+"");
    }
}