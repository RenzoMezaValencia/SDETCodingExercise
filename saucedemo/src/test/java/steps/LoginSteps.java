package steps;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import pages.ProductsPage;

public class LoginSteps {

    LoginPage landingPage = new LoginPage();
    ProductsPage productsPage = new ProductsPage();

    @Given("I navigate to www.saucedemo.com")
    public void iNavigateToSaucedemo(){
        landingPage.navigateToSaucedemo();
    }

    @When("^I enter \"([^\"]*)\" and password$")
    public void loginWithValidUser(String userName){
        landingPage.loginWithUser(userName,"secret_sauce");
    }

    @Then("I go to Product page")
    public void validateGoToProductPage(){
        Assert.assertEquals(productsPage.getPageTittle(),"Products");
    }

    @Then("An Error message apears")
    public void validateErrorMessage(){
        Assert.assertEquals(landingPage.getErrorMessage(),"Epic sadface: Sorry, this user has been locked out.");
    }
}
