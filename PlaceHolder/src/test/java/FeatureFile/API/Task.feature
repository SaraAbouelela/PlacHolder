Feature: As tester i want to validate the emails mentioned in the posts posted by specific username

  @TestScenario
  Scenario Outline: Search for user with specific user name and find all his posts and validate the emails mentioned in these posts
    When User send request to get userId with specific "<UserName>"
    And Send request to get Posts that's posted by this UserId
    And Send request to get Comment that's mentioned into these posts
    Then Validate the emails formats
    Examples:
      | UserName |
      | Delphine |
