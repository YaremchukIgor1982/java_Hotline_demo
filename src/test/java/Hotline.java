import com.github.javafaker.Faker;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.Smartphones;
import pages.UserActions;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.Random;



public class Hotline<objUserActions> {
    public String baseUrl = "https://hotline.ua";
    public WebDriver driver;
    UserActions user;
    Smartphones smarts;

    @BeforeTest
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Administrator\\IdeaProjects\\Hl_demo\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();

        options.addArguments("start-maximized");
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver,5);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        smarts = new Smartphones(driver);
        driver.get(baseUrl);
    }

    @AfterTest
    public void endSession() {
        driver.quit();
    }

    @Test(description = "Login with existed user")
    public void login_as_ExistedUser() throws InterruptedException {
        user = new UserActions(driver);
        user.iconAccountClick();
        user.logIn("hot_user@mailinator.com","Test123#");
        user.submitButtonClick();
    }

    @Test(description = "Change user settings as name and last name")
    public void  change_User_settings(){
        user.iconAccountClick();
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        user.accountChange(firstName, lastName);
        user.submitButtonClick();


    }
    @Test(description = " Find any lenovo via site search")
    public void searching_Lenovo () {
        user.searchProduct("Lenovo");
        user.goToSmartphones();
        user.sortToBuyViaUrl();
        smarts.clickRandomProductToOpen();
//        stop working here with Demo test in Java

    }


}
