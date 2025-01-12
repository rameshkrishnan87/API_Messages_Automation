@messageAPI @deleteMessages
Feature: To Delete the Message by Id
  Background: create an auth token
    Given user has access to endpoint "/auth/login"
    When user creates a auth token with credential "admin" & "password"
    Then user should get the response code 200

  @deleteMessageById
  Scenario Outline: To delete a Message by ID
    Given user has access to endpoint "/message/<id>"
    When user makes a request to delete message with basic auth "admin" & "password123"
    Then user should get the response code 202
    Examples:
      |id |
      |5|
      |6|