package com.api.stepdefinition;

import static org.junit.Assert.*;

import java.util.Map;

import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.path.json.JsonPath;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONObject;


import com.api.utlis.JsonReader;
import com.api.utlis.ResponseHandler;
import com.api.utlis.TestContext;

import io.cucumber.java.en.When;
import io.cucumber.datatable.DataTable;

public class updateMessagesStedDef {
    private TestContext context;
    private static final Logger LOG = LogManager.getLogger(updateMessagesStedDef.class);
    public Cookie authToken;
    public updateMessagesStedDef(TestContext context) {
        this.context = context;
    }

    @When("user creates a auth token with credential {string} & {string}")
    public void userCreatesAAuthTokenWithCredential(String username, String password) {
        JSONObject credentials = new JSONObject();
        credentials.put("username", username);
        credentials.put("password", password);
        context.response = context.requestSetup().body(credentials.toString())
                .when().post(context.session.get("endpoint").toString());
        Cookies allDetailedCookies =context.response.detailedCookies();
        Cookie token = allDetailedCookies.get("token");
//        JsonPath js = new JsonPath(context.response.getBody().asString());
//        String token = js.getString("token");
        LOG.info("Auth Token: " + token);
        System.out.println(token);
        context.session.put("token", "token=" + token);
    }

    @When("user updates the details of a message")
    public void userMakesARequestToDeleteBookingWithBasicAuth() {
        Cookies allDetailedCookies =context.response.detailedCookies();
        Cookie token = allDetailedCookies.get("token");
        authToken = allDetailedCookies.get("token");
        context.response = context.requestSetup()
//                .auth().preemptive().basic(username, password)
//                .pathParam("bookingID", context.session.get("bookingID"))
                .cookie(token)
                .when().put(context.session.get("endpoint")+"");
    }
}
