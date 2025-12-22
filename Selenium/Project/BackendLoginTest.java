package backend;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class BackendLoginTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().window().maximize();
        driver.get("https://alchemy.hguy.co/jobs/wp-admin");
    }

    @Test
    public void verifyBackendLogin() {
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user_login")));
        username.sendKeys("root");

        driver.findElement(By.id("user_pass")).sendKeys("pa$$w0rd");
        driver.findElement(By.id("wp-submit")).click();

        WebElement profileName = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(@class,'display-name') and text()='root']")));
        Assert.assertEquals(profileName.getText(), "root");
        System.out.println("Login successful. Dashboard loaded.");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
