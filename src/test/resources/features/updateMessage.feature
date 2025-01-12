@messageAPI @updateMessage
Feature: To update a message

  Background: create an auth token
    Given user has access to endpoint "/auth/login"
    When user creates a auth token with credential "admin" & "password"
    Then user should get the response code 200

  @updateMessageDataTable
  Scenario Outline: To update a message using cucumber Data Table
    Given user has access to endpoint "/message/<id>"
    When user makes a request to view booking IDs
    And user updates the details of a booking
      | firstname   | lastname   | totalprice   | depositpaid   | checkin   | checkout   | additionalneeds   |
      | <firstname> | <lastname> | <totalprice> | <depositpaid> | <checkin> | <checkout> | <additionalneeds> |
    Then user should get the response code 200
    And user validates the response with JSON schema "bookingDetailsSchema.json"

    Examples:
      | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds |
      | John      | Rambo    |      10000 | true        | 2021-05-15 | 2021-06-11 | Breakfast       |
      | Rocky     | Balboa   |       2006 | false       | 2021-06-01 | 2021-07-10 | Dinner          |