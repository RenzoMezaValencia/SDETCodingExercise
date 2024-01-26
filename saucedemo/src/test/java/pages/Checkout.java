package pages;

public class Checkout extends BasePage {
    
    private String firstNameText = "//input[@id='first-name']";
    private String lastNameText = "//input[@id='last-name']";
    private String postalCodeText = "//input[@id='postal-code']";
    private String cancelButton = "//button[@id='cancel']";
    private String continueButton = "//input[@id='continue']";

    public Checkout(){
        super(driver);
    }

    public void clickOnCancelButton(){
        clickElement(cancelButton);
    }

    public void clickOnContinueButton(){
        clickElement(continueButton);
    }

    public void fillFirstName(String name){
        write(firstNameText, name);
    }

    public void fillLastName(String lastname){
        write(lastNameText, lastname);
    }

    public void fillPostalCode(String postalCode){
        write(postalCodeText, postalCode);
    }

}