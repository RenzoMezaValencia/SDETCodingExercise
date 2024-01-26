package pages;

public class ProductDetailPage extends BasePage {
    
    private String addToCartButton = "//button[normalize-space()='Add to cart']";
    private String cartButton = "//a[@class='shopping_cart_link']";
    private String backToProductsLink = "//button[@id='back-to-products']";
    private String actionButton = "//button[contains(@class, 'btn_inventory')]";
    private String productPrice = "//div[@class='inventory_details_price']";

    public ProductDetailPage(){
        super(driver);
    }

    public void clickOnAddToCartButton(){
        clickElement(addToCartButton);
    }

    public void clickOnCartButton(){
        clickElement(cartButton);
    }

    public void clickOnBackToProductLink(){
        clickElement(backToProductsLink);
    }

    public String getTextFromButton(){
        return getElementText(actionButton);
    }

    public String getProductPrice() {
        String price = getElementText(productPrice);
        return price.replace("$", "");
    }
}
