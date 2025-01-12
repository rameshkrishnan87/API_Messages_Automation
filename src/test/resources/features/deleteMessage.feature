@messageAPI @deleteMessages
Feature: To Delete the Message by Id
  Background: create an auth token
    Given user has access to endpoint "/auth/login"
    When user creates a auth token with credential "admin" & "password"
    Then user should get the response code 200

  @deleteMessageById
  Scenario Outline: To delete a Message by ID
    Given user has access to endpoint "/message/<id>"
    When user makes a request to delete message
    Then user should get the response code 202
    Examples:
      |id |
      |5|
      |6|

  @e2eTest
  Scenario Outline: To perform a CURD operation on message service
    Given user has access to endpoint "/message/"
    When user creates a message
      | name   | email   | phone   | subject   | description   |
      | <name>| <email> | <phone> | <subject> | <description> |
    Then user should get the response code 201
    And user validates the response with JSON schema "createMessageSchema.json"
    And user makes a request to view message detail by id
    And user should get the response code 200
#    And user makes a request to delete message by id
#    And user should get the response code 202

    Examples:
      |name   | email          | phone       | subject         | description                        |
      | John  | john@test.com  | 32465806422 | Create Message 4| Create Message 4 -Description Demo |
      | Jack  | jack@test.com  | 32465280163 | Create Message 5| Create Message 5 -Description Demo |
