package steps;

import java.util.Locale;
import org.json.JSONObject;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.Checkout;
import pages.CheckoutCompletePage;
import pages.CheckoutOverviewPage;
import pages.LoginPage;
import pages.ProductDetailPage;
import pages.ProductsPage;
import pages.YourCartPage;
import com.github.javafaker.Faker;

public class WorkflowSteps {
    LoginPage landingPage = new LoginPage();
    ProductsPage productsPage = new ProductsPage();
    ProductDetailPage productDetailPage = new ProductDetailPage();
    YourCartPage yourCartPage = new YourCartPage();
    Checkout checkoutPage = new Checkout();
    CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage();
    CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage();

    Locale locale = Locale.getDefault();
    Faker fake = new Faker(locale);
    JSONObject productPrices = new JSONObject();
    JSONObject yourCartPrices = new JSONObject();

    @When("^I add product \"([^\"]*)\" to the cart$")
    public void addProductToCart(String product){
        productsPage.clickOnProduct(product);
        productDetailPage.clickOnAddToCartButton();
        productDetailPage.clickOnBackToProductLink();   
    }

    @And("I checkout the product")
    public void checkoutProduct(){
        productsPage.clickOnCart();
        yourCartPage.clickOnCheckoutButton();
    }

    @And("Fill checkout information")
    public void fillCheckoutInformation(){
        String firstName = fake.name().firstName();
        String lastName = fake.name().lastName();
        String postalCode = fake.address().zipCode();

        checkoutPage.fillFirstName(firstName);
        checkoutPage.fillLastName(lastName);
        checkoutPage.fillPostalCode(postalCode);
        checkoutPage.clickOnContinueButton();
    }

    @Then("I see the checkout Overview")
    public void finishCheckoutOverview(){
        Assert.assertEquals(checkoutOverviewPage.getPageTittle(), "Checkout: Overview");
        checkoutOverviewPage.clickOnFinishButton();
        Assert.assertEquals(checkoutCompletePage.getThanksMessage(),"Thank you for your order!");
    }

    @And("I can logout to Login page")
    public void logoutFromMenu(){
        checkoutCompletePage.clickOnMenuButton();
        checkoutCompletePage.clickOnLogoutButton();
    }

    @Given("^I change filter \"([^\"]*)\"$")
    public void changeFilter(String filter){
        productsPage.clickOnFilterButton();
        String option = productsPage.getFilterValue(filter);
        productsPage.selectFilterValue(option);
        productsPage.clickOnFilterButton();
        Assert.assertEquals(productsPage.activeFilter(), filter);
        Assert.assertTrue(productsPage.validatePriceInAscendingOrder());
    }

    @Then("^I check if Remove button is enable for \"([^\"]*)\"$")
    public void validateIfRemoveButtonEnable(String product){
        productsPage.clickOnProduct(product);
        Assert.assertEquals(productDetailPage.getTextFromButton(),"Remove");
        productDetailPage.clickOnBackToProductLink();
    }

    @Then("^I validate that the number of product added is \"([^\"]*)\"$")
    public void validateNumberOfProductAdded(String number){
        Assert.assertEquals(productsPage.getNumberOfProductAdded(),number);
    }

    @When("^I capture \"([^\"]*)\" price$")
    public void capturePricefromProduct(String product){
        productsPage.clickOnProduct(product);
        productPrices.put(product, productDetailPage.getProductPrice());
        productDetailPage.clickOnBackToProductLink();
    }

    @When("I click on Cart icon")
    public void clickOnCartIconFromProduct(){
        productsPage.clickOnCart();
    }

    @When("^I validate \"([^\"]*)\" price on YourCart vs Product Page$")
    public void capturePricefromYourCart(String product){
        yourCartPrices.put(product, yourCartPage.capturePrice(product));
        String productPrice = productPrices.getString(product);
        String yourCartPrice = yourCartPrices.getString(product);
        Assert.assertEquals(productPrice, yourCartPrice);
    }

    @When("^I click on Remove button for \"([^\"]*)\" on YourCart$")
    public void removeProductFromYourCart(String product){
        yourCartPage.clickOnRemoveButton(product);
    }

    @Then("^I validate the quantity of \"([^\"]*)\"$")
    public void validateNewQuantity(String product){
        Assert.assertEquals(yourCartPage.getQuantityOfProduct(product), "1");
    }

    @Then("^I validate number of products that Cart Icon shows$")
    public void validateNumberOfCartIcon(){
        Assert.assertEquals(yourCartPage.getNumberOfCartIcon(), "1");
    }

    @When("I click on Checkout button on YouCart page")
    public void clickOnCheckoutButtonFromYourCart(){
        yourCartPage.clickOnCheckoutButton();
    }

    @Then("I validate Item total from Checkout Overview page")
    public void validateItemTotalFromOverviewPage(){
        String productPrice = productPrices.getString("Sauce Labs Fleece Jacket");
        Assert.assertEquals(checkoutOverviewPage.getItemTotal(), productPrice);
    }

    @When("I click Finish button from Checkout Overview page")
    public void clickOnFinishButtonFromOverviePage(){
        checkoutOverviewPage.clickOnFinishButton();
    }

    @Then("I see the checkout Complete")
    public void validateCheckoutComplete(){
        Assert.assertEquals(checkoutCompletePage.getThanksMessage(),"Thank you for your order!");
    } 
}
