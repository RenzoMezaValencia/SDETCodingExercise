Feature: Workflow Validation
    Test Workflow scenarios

    Background:
        Given I navigate to www.saucedemo.com
        When I enter "standard_user" and password
@Test_3
    Scenario Outline: Test case 3 - Happy Path Workflow
        When I add product <product> to the cart
            And I checkout the product
            And Fill checkout information
        Then I see the checkout Overview
            And I can logout to Login page
Examples:
    | product |
    | "Sauce Labs Bike Light"  |

@Test_4
    Scenario: Test case 4 - Multiple scenario Workflow
        Given I change filter "Price (low to high)"
        When I add product "Sauce Labs Fleece Jacket" to the cart
        When I capture "Sauce Labs Fleece Jacket" price
        When I add product "Sauce Labs Onesie" to the cart
        When I capture "Sauce Labs Onesie" price
        Then I check if Remove button is enable for "Sauce Labs Fleece Jacket"
        Then I check if Remove button is enable for "Sauce Labs Onesie"
        Then I validate that the number of product added is "2"

        When I click on Cart icon
        When I validate "Sauce Labs Fleece Jacket" price on YourCart vs Product Page
        When I validate "Sauce Labs Onesie" price on YourCart vs Product Page

        When I click on Remove button for "Sauce Labs Onesie" on YourCart
        Then I validate the quantity of "Sauce Labs Fleece Jacket"
        Then I validate number of products that Cart Icon shows

        When I click on Checkout button on YouCart page
            And Fill checkout information
        Then I validate Item total from Checkout Overview page

        When I click Finish button from Checkout Overview page
        Then I see the checkout Complete
            And I can logout to Login page