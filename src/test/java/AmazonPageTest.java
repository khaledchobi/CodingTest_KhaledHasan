import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class AmazonPageTest {


    public static void main(String[] args) throws InterruptedException {
        String exePath = "/Users/khaledhasan/Desktop/Java_Github/Jenkins/CodingTest_KhaledHasan/src/main/resources/drivers/chromedriver";
        System.setProperty("webdriver.chrome.driver", exePath);
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // 1. Visit amazon.com Page
        driver.get("https://www.amazon.com/");


        // 2. Search for Book 'qa testing for beginners'
        driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("qa testing for beginners");
        driver.findElement(By.xpath("//input[@id='nav-search-submit-button']")).click();

        // 3. Click on 1st item in the listed results.
        driver.findElement(By.xpath("//*[@id='search']//*[@data-component-id='1']//*[@data-image-source-density='1']")).click();

        // 4. Before Click on add to cart Add to Cart asset price from Step3.
        WebElement price = driver.findElement(By.xpath("//div[@id='buyNew_cbb']//span[@id='newBuyBoxPrice']"));
        System.out.println("Add to Cart asset price is: " + price.getText());

        // 5. Click on Add to Cart.
        driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();

        // 6. Before Click on Proceed to Checkout asset price from Step3.
        WebElement beforeProceedPrice = driver.findElement(By.xpath("//div[@id='hlb-subcart']//span[@class='a-color-price hlb-price a-inline-block a-text-bold']"));
        System.out.println("Before Proceed to Checkout asset price is: " + beforeProceedPrice.getText());

        // 7. Click on proceed to checkout
        driver.findElement(By.xpath("//a[@id='hlb-ptc-btn-native']")).click();

        Thread.sleep(3000);

        driver.close();
    }


    @Test
    public void amazonPage() throws InterruptedException {
        String exePath = "/Users/khaledhasan/Desktop/Java_Github/Jenkins/CodingTest_KhaledHasan/src/main/resources/drivers/chromedriver";
        System.setProperty("webdriver.chrome.driver", exePath);
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // 1. Visit amazon.com Page
        driver.get("https://www.amazon.com/");


        // 2. Search for Book 'qa testing for beginners'
        driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("qa testing for beginners");
        driver.findElement(By.xpath("//input[@id='nav-search-submit-button']")).click();

        // 3. Click on 1st item in the listed results.
        driver.findElement(By.xpath("//*[@id='search']//*[@data-component-id='1']//*[@data-image-source-density='1']")).click();

        // 4. Before Click on add to cart Add to Cart asset price from Step3.
        WebElement price = driver.findElement(By.xpath("//div[@id='buyNew_cbb']//span[@id='newBuyBoxPrice']"));
        System.out.println("Add to Cart asset price is: " + price.getText());

        // 5. Click on Add to Cart.
        driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();

        // 6. Before Click on Proceed to Checkout asset price from Step3.
        WebElement beforeProceedPrice = driver.findElement(By.xpath("//div[@id='hlb-subcart']//span[@class='a-color-price hlb-price a-inline-block a-text-bold']"));
        System.out.println("Before Proceed to Checkout asset price is: " + beforeProceedPrice.getText());

        // 7. Click on proceed to checkout
        driver.findElement(By.xpath("//a[@id='hlb-ptc-btn-native']")).click();

        Thread.sleep(3000);

        driver.close();
    }

}
