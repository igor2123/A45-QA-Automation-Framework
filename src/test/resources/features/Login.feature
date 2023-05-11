Feature: Login Feature
  Scenario: LoginValidEmailValidPassword
    Given I open browser
    And I open Login Page
    When I enter email "demo@class.com
    And I enter password "te$t$tudent
    And I submit button
    Then I am logged in

