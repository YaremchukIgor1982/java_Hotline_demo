package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Smartphones {
    WebDriver driver;

    private By catalog = By.cssSelector("#catalog-products");
    private By prod = By.cssSelector(".product-item");
    private By product_name = By.cssSelector(".item-info p.h4 a");
    private  By product_price = By.cssSelector(".item-price.stick-bottom span.value");

    public Smartphones(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickRandomProductToOpen() {
        WebElement itemBox = driver.findElement(catalog);
//        List<WebElement> products = itemBox.findElements(prod);
        List<WebElement> names = itemBox.findElements(product_name);
        Random random = new Random();
        int result = random.nextInt(names.size());
        System.out.println("Randomly used for click to buy : " + names.get(result).getText());
        names.get(result).click();
    }

}
