Feature: User creation, update and deletion
  Using Employee API create user, update it and delete it afterward

  Background:
    Given User have following data
      | id         | firstName | lastName | dependants |
      | userData01 | random    | random   | 2          |
      | userData02 | Michal    | Todorov  | not_used   |

  Scenario: Create user, update it, delete it and verify it's no longer available
    Given Employee want to create "userData01" user
    When Employee want to update "provided" user with "userData02" data
    And Verify that "provided" user using "userData02" data are correct
    Then Employee want to delete "provided" user
    And Verify that "provided" user does not exist