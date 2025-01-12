@messageAPI @viewMessages
Feature: To view the Message by Id

  @viewMessageById
  Scenario Outline: To view a Message Detail by ID
    Given user has access to endpoint "/message/<id>"
    When user makes a request to view number of messages
    Then user should get the response code 200
    And user should see message details by "<id>"
  Examples:
    |id |
    |2|
    |3|