@messageAPI @updateMessage
Feature: To update a message

  Background: create an auth token
    Given user has access to endpoint "/auth/login"
    When user creates a auth token with credential "admin" & "password"
    Then user should get the response code 200

  @updateMessageDataTable
  Scenario Outline: To update a message using cucumber Data Table
    Given user has access to endpoint "/message/<id>/read"
    When user makes a request to view list of messages
    And user updates the details of a message
    Then user should get the response code 200

    Examples:
      |id |
      |5|
      |6|