
package activities;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Applytojob {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://alchemy.hguy.co/jobs");

        driver.findElement(By.linkText("Jobs")).click();

        WebElement searchBox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//input[@placeholder='Keywords']")
                )
        );
        searchBox.sendKeys("Banking");

        driver.findElement(By.xpath("//input[@value='Search Jobs']")).click();

        WebElement firstJob = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("(//ul[contains(@class,'job_listings')]//a)[1]")
                )
        );
        firstJob.click();

        WebElement applyBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//input[@value='Apply for job']")
                )
        );
        applyBtn.click();

        WebElement emailText = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//a[contains(@class,'job_application_email')]")
                )
        );

        System.out.println( "After Clicking apply button printing email on the consol" +emailText.getText());

        driver.quit();
    }
}

