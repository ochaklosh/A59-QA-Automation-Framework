Feature: Login feature

  Scenario: Positive Login Scenario
    Given I open browser
    And I open Koel Login Page
    When I enter email "demo@testpro.io"
    And I enter password "te$tStudent"
    And I click submit
    Then I should be logged in