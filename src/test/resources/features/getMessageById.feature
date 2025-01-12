@messageAPI @viewMessages
Feature: To view the list of messages

  @viewMessageById
  Scenario Outline: To view all the Message Details
    Given user has access to endpoint "/message/<id>"
    When user makes a request to view list message by id
    Then user should get the response code 200
    And user should see message details by "<id>"
  Examples:
    |id |
    |2|
    |3|