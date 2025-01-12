@messageAPI @createMessage
Feature: To create a new booking in restful-booker

  @createMessageDataTable
  Scenario Outline: To create new message using cucumber Data Table
    Given user has access to endpoint "/message/"
    When user creates a message
      | name   | email   | phone   | subject   | description   |
      | <name>| <email> | <phone> | <subject> | <description> |
    Then user should get the response code 201
#    And user validates the response with JSON schema "createBookingSchema.json"

    Examples:
      |name   | email          | phone       | subject         | description                        |
      | John  | john@test.com  | 32465806421 | Create Message 1| Create Message 1 -Description Demo |
      | Jack  | jack@test.com  | 32465280160 | Create Message 2| Create Message 1 -Description Demo |