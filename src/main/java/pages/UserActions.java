package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class UserActions {

    WebDriver driver;

    private By iconToAccount = By.cssSelector(".item-login");
    private By login=By.cssSelector("[name='login']");
    private By password=By.cssSelector("[name='password']");
    private By submit_button = By.cssSelector(".btn-graphite");
    private By nameFirst = By.cssSelector("[name='user_personal_data_form[firstName]']");
    private By nameLast= By.cssSelector("[name='user_personal_data_form[lastName]']");
    private By personal = By.cssSelector("a[href*='/personal/']");
    private By site_search = By.cssSelector("#searchbox");

    public UserActions(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public void iconAccountClick(){
        driver.findElement(iconToAccount).click();
        }

        public void logIn(String userEmail, String pwd){
            driver.findElement(login).sendKeys(userEmail);
            driver.findElement(password).sendKeys(pwd);

        }
        public void submitButtonClick(){
            driver.findElement(submit_button).click();
        }

        public void accountChange(String firstName,String lastName){
            driver.findElement(personal).click();
            System.out.println(driver.getCurrentUrl());
            driver.findElement(nameFirst).clear();
            driver.findElement(nameFirst).sendKeys(firstName);
            driver.findElement(nameLast).clear();
            driver.findElement(nameLast).sendKeys(lastName);
    }

    public void searchProduct(String searchitem){
        WebElement search = driver.findElement(site_search);
        search.sendKeys(searchitem);
        search.sendKeys(Keys.ENTER);
    }

    public void goToSmartphones(){
        List<WebElement> dash = driver.findElements(By.cssSelector(".active li a"));
        dash.get(1).click();
    }
    public void sortToBuyViaUrl(){
        String cur = driver.getCurrentUrl();
        driver.get(cur + "?checkout=1");
    }


}