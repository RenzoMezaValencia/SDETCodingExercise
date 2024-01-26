package pages;

public class CheckoutOverviewPage extends BasePage {
    
    private String paymentInformation ="(//div[@class='summary_info']/div[@class='summary_value_label'])[1]";
    private String shippingInformation ="(//div[@class='summary_info']/div[@class='summary_value_label'])[2]";
    private String itemTotal ="//div[@class='summary_subtotal_label']";
    private String tax = "//div[@class='summary_tax_label']";
    private String total = "//div[@class='summary_info_label summary_total_label']";
    private String pageTittle = "//span[@class='title']";
     
    private String cancelButton = "//button[@id='cancel']";
    private String finishButton = "//button[@id='finish']";

    public CheckoutOverviewPage(){
        super(driver);
    }

    public void clickOnCancelcButton(){
        clickElement(cancelButton);
    }

    public void clickOnFinishButton(){
        clickElement(finishButton);
    }

    public String getPaymentInformation(){
        return getElementText(paymentInformation);
    }

    public String getShippingInformation(){
        return getElementText(shippingInformation);
    }

    public String getTotal(){
        String fTotal = getElementText(total);
        return fTotal.replace("Total: $", "");
    } 

    public String getTax(){
        String taxA = getElementText(tax);
        return taxA.replace("Tax: $", "");
    } 

    public String getItemTotal(){
        String iTotal = getElementText(itemTotal);
        return iTotal.replace("Item total: $", "");
    } 

    public String getPageTittle(){
        return getElementText(pageTittle);
    }
}
