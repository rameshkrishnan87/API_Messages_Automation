@messageAPI @viewMessages
Feature: To view the list of messages

  @viewAllMessages
  Scenario: To view all the Message Details
    Given user has access to endpoint "/message"
    When user makes a request to view list of messages
    Then user should get the response code 200
#    And user should see all the booking IDs