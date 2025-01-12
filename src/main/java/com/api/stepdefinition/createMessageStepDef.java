package com.api.stepdefinition;

import static org.junit.Assert.*;

import java.util.Map;

import lombok.extern.java.Log;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONObject;


import com.api.utlis.JsonReader;
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

//        BookingDTO bookingDTO = ResponseHandler.deserializedResponse(context.response, BookingDTO.class);
//        assertNotNull("Booking not created", bookingDTO);
//        LOG.info("Newly created booking ID: "+bookingDTO.getBookingid());
//        context.session.put("bookingID", bookingDTO.getBookingid());
//        validateBookingData(new JSONObject(bookingData), bookingDTO);
    }
}
