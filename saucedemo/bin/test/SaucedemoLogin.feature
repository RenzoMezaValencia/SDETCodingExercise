Feature: Login Validation
    Navigate and validate login credentials

@Test
Scenario: Test case 1 - Successful Login
    Given I navigate to www.saucedemo.com 
    When I enter "standard_user" and password
    Then I go to Product page

@Test_2
Scenario: Test case 2 - Failed Login
    Given I navigate to www.saucedemo.com 
    When I enter "locked_out_user" and password
    Then An Error message apears
