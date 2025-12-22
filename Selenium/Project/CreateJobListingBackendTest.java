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

public class CreateJobListingBackendTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().window().maximize();
    }

    @Test
    public void createJobListingFromBackend() {

        driver.get("https://alchemy.hguy.co/jobs/wp-admin");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user_login"))).sendKeys("root");
        driver.findElement(By.id("user_pass")).sendKeys("pa$$w0rd");
        driver.findElement(By.id("wp-submit")).click();

        WebElement dashboard = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h1[contains(text(),'Dashboard')]")));
        Assert.assertTrue(dashboard.isDisplayed());

        driver.findElement(By.xpath("//div[text()='Job Listings']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//h1[normalize-space()='Jobs']/following-sibling::a[1]"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//textarea[@placeholder='Position']"))).sendKeys("Automation Test Engineer");

        driver.findElement(By.id("_job_location")).sendKeys("Remote");
        driver.findElement(By.id("_application")).sendKeys("testjob@company.com");
        driver.findElement(By.id("_company_name")).sendKeys("Alchemy Technologies");
        driver.findElement(By.id("_company_tagline")).sendKeys("Innovating with Automation");

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(),'Publish')]"))).click();

       
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(@class,'editor-post-publish-button') and contains(@class,'is-primary') and not(contains(@class,'panel__toggle'))]"))).click();

        WebElement publishMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'published')]")));
        Assert.assertTrue(publishMsg.isDisplayed());
        System.out.println("Job listing published successfully.");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
