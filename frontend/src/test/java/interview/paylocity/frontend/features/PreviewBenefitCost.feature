Feature: As an Employer, I want to input my employee data so that I can get a preview of benefit costs

  Scenario: Add Employee
    Given As an Employer I am on the Benefits Dashboard page
    When I select Add Employee
    Then I should be able to enter employee details
    And the employee should save
    And I should see the employee in the table
    And the benefit cost calculations are correct

  Scenario: Edit Employee
    Given As an Employer I am on the Benefits Dashboard page
    When I select the Action Edit
    Then I can edit employee details
    And the data should change in the table

  Scenario: Delete Employee
    Given As an Employer I am on the Benefits Dashboard page
    When I click the Action X
    Then the employee should be deleted