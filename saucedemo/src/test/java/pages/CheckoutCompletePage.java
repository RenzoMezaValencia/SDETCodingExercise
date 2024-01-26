package pages;

public class CheckoutCompletePage extends BasePage {
    
    private String thanksMessage = "//h2[@class='complete-header']";
    private String menuButton = "//button[@id='react-burger-menu-btn']";
    private String logoutButton = "//a[@id='logout_sidebar_link']";

    public CheckoutCompletePage(){
        super(driver);
    }

    public String getThanksMessage(){
        return getElementText(thanksMessage);
    }

    public void clickOnMenuButton(){
        clickElement(menuButton);
    }

    public void clickOnLogoutButton(){
        clickElementDelay(logoutButton);
    }
}
