Feature: Ringo Registration Test coverage
  # This below Background step is to Setup the Firefox Browser driver and open the Registration Page
  # Its a common step for all the Test Scenarios
  Background:
    Given Setup to start browser and the Registration Page

  # This Scenario verifies the Happy Flow of the User Registration
  # Note : Could not handle the Captcha
  Scenario: As a RingGo User, I like to Register for RingGo Account
    Given I open Registration Page and enter my details
    When I submit my registration details
    Then I am registered Successfully

  # This Scenario verifies the Error Notifications on Page Level
  Scenario: As a RingGo User, when I submit insufficient details then I am on Error Page
    Given I submit Registration page without entering mandatory details
    Then I am rendered with Page Warnings

  # This Scenario verifies the Specific Field Validation for Mobile Number
  Scenario Outline: Invalid Mobile Number Entries check
    When I enter invalid <mobileNumber> then I am triggered with specific <mobileErr> message
    Examples:
    |mobileNumber |mobileErr                                         |
    |0649123      |Telephone number must be longer than 9 characters |
    |064abc       |Mobile Number must be numeric                     |
    |06           |Phone number is invalid                           |

  # This Scenario verifies the Field Validation for Email and Password
  Scenario Outline: Invalid Email and Password Entries check
    When I enter invalid <emailId> and <password>
    Then I am triggered with specific Email Error as <emailErr> and Password Error message as The password does not meet the correct format.
    Examples:
    |emailId              |password  |emailErr                  |
    |#@%^%#$@#$@#.com     |zuppa     |Email address is invalid  |
    |email.@example.com   |Zuppaij   |Email address is invalid  |
    |Abc..123@example.com |Zupaij    |Email is invalid          |




