Feature: Login feature

  Scenario: Positive Login Scenario
    Given I open Koel Login Page
    When I enter email "demo@testpro.io"
    And I enter password "te$t$tudent"
    And I click submit
    Then I should be logged in

  Scenario Outline: Login Scenario
    Given I open Koel Login Page
    When I enter email "<Email>"
    And I enter password "<Password>"
    And I click submit
    Then I should be logged in
    Examples:
      | Email           |Password     |
      | demo@ttpro.io   | te$tdent    |
      |                 | te$t$tudent |
      | do@testpro.io   |             |
      |                 |             |

