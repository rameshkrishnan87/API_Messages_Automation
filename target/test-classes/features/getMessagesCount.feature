@messageAPI @viewMessages
Feature: To view Messages Count

  @viewMessageCount
  Scenario: To view number of Messages created
    Given user has access to endpoint "/message/count"
    When user makes a request to view message details by id
    Then user should get the response code 200
    And user should see message count
