import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class IphoneX {
    public String baseUrl = "https://hotline.ua";
    public WebDriver driver;

    @BeforeTest
    public void Open() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Administrator\\IdeaProjects\\Hl_demo\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();

        options.addArguments("start-maximized");
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(baseUrl);
    }

    @AfterTest
    public void endSession() {
        driver.quit();
    }


    @Test
    public void checkLowestValue() {
        driver.get(baseUrl + "/mobile/mobilnye-telefony-i-smartfony/?checkout=1&q=Iphone+X");
        List<Product> allProducts = fetchDataFromProductsToDict();

        int lowestPrice = allProducts.stream().map(Product::getAmount)
                .min(Integer::compareTo)
                .get();

        Product cheapestProduct = allProducts.stream().filter(product -> product.getAmount().equals(lowestPrice))
                .findFirst()
                .get();

        System.out.println(String.format("Product with the lowest price %d is %s." +
                " Link to product: %s", cheapestProduct.getAmount(),cheapestProduct.getName(),cheapestProduct.getLink()));

    }

    public  List<Product> fetchDataFromProductsToDict() {
        List<WebElement> devices = driver.findElements(By.cssSelector(".tile-viewbox .product-item"));
        devices.remove(0);

        return devices.stream().map(productLine -> {
            Product product = new Product();

            WebElement model = productLine.findElement(By.cssSelector(".item-info p a"));

            String productModel = model.getText();
    //        Integer productPrice = Integer.valueOf(driver.findElement(By.cssSelector(".item-price.stick-bottom span.value")).getText());
            String productLink = model.getAttribute("href");

            Integer productPrice = Integer.valueOf(driver.findElement(By.cssSelector(".item-price.stick-bottom span.value")).getText().replace(" ",""));
            product.setName(productModel);
            product.setAmount(productPrice);
            product.setLink(productLink);

            return product;
        }).collect(Collectors.toList());
    }
}