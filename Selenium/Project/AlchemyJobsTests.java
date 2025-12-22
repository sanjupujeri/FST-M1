package frontend;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class AlchemyJobsTests {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void verifyTitle() {
        driver.get("https://alchemy.hguy.co/jobs/");
        Assert.assertEquals(driver.getTitle(), "Alchemy Jobs – Job Board Application");
    }

    @Test(priority = 2)
    public void verifyMainHeading() {
        driver.get("https://alchemy.hguy.co/jobs");
        WebElement heading = driver.findElement(By.xpath("//h1[text()='Welcome to Alchemy Jobs']"));
        Assert.assertEquals(heading.getText(), "Welcome to Alchemy Jobs");
    }

    @Test(priority = 3)
    public void verifySecondHeading() {
        driver.get("https://alchemy.hguy.co/jobs");
        WebElement heading = driver.findElement(By.xpath("//h2[contains(text(),'Quia quis non')]"));
        Assert.assertEquals(heading.getText(), "Quia quis non");
    }

    @Test(priority = 4)
    public void navigateToJobsPage() {
        driver.get("https://alchemy.hguy.co/jobs");
        driver.findElement(By.linkText("Jobs")).click();
        Assert.assertTrue(driver.getTitle().contains("Jobs"));
    }

    @Test(priority = 5)
    public void getHeaderImageUrl() {
        driver.get("https://alchemy.hguy.co/jobs");
        WebElement img = driver.findElement(By.xpath("//header//img"));
        String url = img.getAttribute("src");
        System.out.println("Header image URL: " + url);
        Assert.assertTrue(url.contains("http"));
    }

    @Test(priority = 6)
    public void applyToJob() {
        driver.get("https://alchemy.hguy.co/jobs");
        driver.findElement(By.linkText("Jobs")).click();

        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='Keywords']")));
        searchBox.sendKeys("Banking");

        driver.findElement(By.xpath("//input[@value='Search Jobs']")).click();

        // Use refreshed condition to avoid stale element
        WebElement firstJob = wait.until(ExpectedConditions.refreshed(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("(//ul[contains(@class,'job_listings')]//a)[1]"))));
        firstJob.click();

        WebElement applyBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@value='Apply for job']")));
        applyBtn.click();

        WebElement emailText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(@class,'job_application_email')]")));
        System.out.println("Apply Email: " + emailText.getText());
        Assert.assertTrue(emailText.isDisplayed());
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
