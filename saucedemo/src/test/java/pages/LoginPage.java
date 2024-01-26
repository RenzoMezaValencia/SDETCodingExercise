package pages;

public class LoginPage extends BasePage {
    
    private String userNameTextBox = "//input[@id='user-name']";
    private String passwordTextBox = "//input[@id='password']";
    private String loginButton = "//input[@id='login-button']";
    private String errorMessage = "//h3[@data-test='error']";

    public LoginPage(){
        super(driver);
    }

    public void navigateToSaucedemo(){
        navigateTo("https://www.saucedemo.com/");
    }

    public void loginWithUser(String userName, String password) {
       write(userNameTextBox, userName);
       write(passwordTextBox, password);
       clickElement(loginButton);
    }

    public String getErrorMessage(){
        return getElementText(errorMessage);
    }
    
}
