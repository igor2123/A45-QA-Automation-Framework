Feature: Profile Feature
  Scenario: Change Profile Name
    And I open Login Page
    When I enter email "demo@class.com
    And I enter password "te$t$tudent
    And I submit button
    Then I am logged in
    And I click avatar icon
    And I provide current email
    And I provide current password
    And I change profile name
    And I click save button
    Then I changed profile name
