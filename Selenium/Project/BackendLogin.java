package activities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BackendLogin {

    public static void main(String[] args) {


        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        
        driver.get("https://alchemy.hguy.co/jobs/wp-admin");
        System.out.println("Login Page Title: " + driver.getTitle());

        
        WebElement username = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("user_login"))
        );
        username.sendKeys("");

        
        driver.findElement(By.id("user_pass"))
              .sendKeys("");

     
        driver.findElement(By.id("wp-submit")).click();

        
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("admin")
        ));
        System.out.println("Login successful. Dashboard loaded.");

        
        driver.quit();
    }
}
