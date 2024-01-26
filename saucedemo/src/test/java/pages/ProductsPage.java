package pages;

import java.util.List;

import org.openqa.selenium.WebElement;

public class ProductsPage extends BasePage {

    private String pageTitle = "//span[@class='title']";
    private String itemAddToCartButton = "//button[@id='add-to-cart-sauce-labs-bike-light']";
    private String cartButton = "//div[@id='shopping_cart_container']";
    private String productLinkName = "//div[normalize-space()='%s']";
    private String filterButton = "//select[@class='product_sort_container']";
    private String activeFilter = "//span[@class='active_option']";
    private String productList = "//div[@class='pricebar']/div[@class='inventory_item_price']";
    private String numberOfProductAdded = "//span[@class='shopping_cart_badge']"; 

    public ProductsPage() {
        super(driver);
    }

    public void addProductToCart(String product) {

        clickElement(itemAddToCartButton);
    }

    public void clickOnCart() {
        clickElement(cartButton);
    }

    public String getPageTittle() {
        return getElementText(pageTitle);
    }

    public void clickOnProduct(String product) {
        String xpathProductName = String.format(productLinkName, product);
        clickElement(xpathProductName);
    }

    public void clickOnFilterButton() {
        clickElement(filterButton);
    }

    public void selectFilterValue(String option) {
        selectFromDropDownByValue(filterButton, option);
    }

    public String getFilterValue(String filter) {
    //You can get the values from the DOM filter 
        switch (filter) {
            case "Name (A to Z)": return "az";
            case "Name (Z to A)": return "za";
            case "Price (low to high)": return "lohi";
            case "Price (high to low)": return "hilo";
            default: break;
        }
        return "az";
    }

    public String activeFilter(){
        return getElementText(activeFilter);
    }

    public Boolean validatePriceInAscendingOrder(){
    //Here I collect all prices and I validate that are in ascending order
        List<WebElement> list = FindList(productList);
        //I initilize the ref variable in the minimun value: 0.00
        Double ref=0.00;
        for(int i=0;i<list.size();i++){
            String price = list.get(i).getText();
            price = price.replace("$", "");
            if(Double.parseDouble(price)<ref) return false;
            ref = Double.parseDouble(price);
        }
        return true;
    }

    public String getNumberOfProductAdded() {
        return getElementText(numberOfProductAdded);
    }

}
