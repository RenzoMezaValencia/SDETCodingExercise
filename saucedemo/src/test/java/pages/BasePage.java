package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

//I'm using WebDriverManger to manage the drivers (search and versions)
import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
    
    protected static WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    //Here you can set the Browser you need
    static{
        //FireFox
        //WebDriverManager.firefoxdriver().setup();
        //driver = new FirefoxDriver();

        //Chrome
        //WebDriverManager.chromedriver().setup();
        //driver = new ChromeDriver();

        //Edge
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
    }

    public BasePage(WebDriver driver){
        BasePage.driver = driver;
    }

    //Functions I will use
    public static void navigateTo(String url){
        driver.get(url);
    }

    public static void closeBrowser(){
        driver.quit();
    }

    private WebElement Find(String locator){
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    }

    public List<WebElement> FindList(String locator){
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(locator)));
    }

    private WebElement FindClickable(String locator){
        return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
    }

    public void clickElement(String locator){
        Find(locator).click();
    }

    public void clickElementDelay(String locator){
        FindClickable(locator).click();
    }

    public void write(String locator, String keysToSend){
        Find(locator).clear();
        Find(locator).sendKeys(keysToSend);
    }

    public void selectFromDropDownByValue(String locator, String value){
        Select dropdown = new Select(Find(locator));
        dropdown.selectByValue(value);
    }

    public void selectFromDropDownByIndex(String locator, Integer index){
        Select dropdown = new Select(Find(locator));
        dropdown.selectByIndex(index);
    }

    public int dropdownSize(String locator){
        Select dropdwon = new Select(Find(locator));
        List<WebElement> dropdownOptions = dropdwon.getOptions();
        return dropdownOptions.size();
    }

    public String getElementText(String locator){
        return Find(locator).getText();
    }

    public List<WebElement> getWebElementList(String locator){
        return FindList(locator);
    }

    public WebElement getWebElementWithIndex(List<WebElement> list,Integer index){
        return list.get(index);
    }
}
