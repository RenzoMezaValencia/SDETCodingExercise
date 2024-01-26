package pages;

import java.util.List;

import org.openqa.selenium.WebElement;

public class YourCartPage extends BasePage {
    
    private String checkoutButton ="//button[@id='checkout']";
    private String productLinkName = "//div[normalize-space()='%s']";
    private String productListName = "//div[@class='cart_item_label']/a/div";
    private String productListPrice = "//div[@class='cart_item_label']/div/div[@class='inventory_item_price']";
    private String productListQuantity = "//div[@class='cart_item']/div[@class=\"cart_quantity\"]";
    private String listOfRemoveButton = "//button[contains(text(),'Remove')]";
    private String numberOfIcon = "//span[@class='shopping_cart_badge']";

    public YourCartPage(){
        super(driver);
    }

    public void clickOnCheckoutButton(){
        clickElement(checkoutButton);
    }

    public void clickOnProduct(String product) {
        String xpathProductName = String.format(productLinkName, product);
        clickElement(xpathProductName);
    }

    // Here I list of WebElements to identify the position with the name list 
    // and then get the value with the price list, if I can't find the price
    // I return 0.00

    public String capturePrice(String product) {
        List<WebElement> listName = FindList(productListName);
        List<WebElement> listPrice = FindList(productListPrice);
        for(int i=0;i<listName.size();i++){
            if(listName.get(i).getText().equals(product)){
               String price = listPrice.get(i).getText();
               return price.replace("$", "");
            }
        }
        return "0.00"; 
    }

    // Here I list of WebElements to identify the position with the name list 
    // and then click the button with the button list

    public void clickOnRemoveButton(String product) {
        List<WebElement> listName = FindList(productListName);
        List<WebElement> listOfButtons = FindList(listOfRemoveButton);
        for(int i=0;i<listName.size();i++){
            if(listName.get(i).getText().equals(product)){
                listOfButtons.get(i).click();
            }
        }
    }

    // Here I list of WebElements to identify the position with the name list 
    // and then get the value with the quantity list, if I can't find the value
    // I return 0

    public String getQuantityOfProduct(String product){
        List<WebElement> listName = FindList(productListName);
        List<WebElement> listOfQuantity = FindList(productListQuantity);
        for(int i=0;i<listName.size();i++){
            if(listName.get(i).getText().equals(product)){
                return listOfQuantity.get(i).getText();
            }
        }
        return "0";
    }

    public String getNumberOfCartIcon(){
        return getElementText(numberOfIcon);
    }
}
