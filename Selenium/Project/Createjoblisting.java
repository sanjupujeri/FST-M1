package activities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Createjoblisting {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        
        driver.get("https://alchemy.hguy.co/jobs");
        System.out.println("Page Title: " + driver.getTitle());


        wait.until(ExpectedConditions.elementToBeClickable(
                By.linkText("Post a Job"))).click();

       
        driver.findElement(By.id("company_name")).sendKeys("Test Company");
        driver.findElement(By.id("company_website")).sendKeys("https://testcompany.com");
        driver.findElement(By.id("company_tagline")).sendKeys("Automation Test Job");
        driver.findElement(By.id("create_account_email")).sendKeys("sanjupujeri@gmail.com");
        driver.findElement(By.id("job_title")).sendKeys("Test Job");
        driver.findElement(By.xpath("//label[text()='Job type']/following::select[1]"));
       
        driver.switchTo().frame(
                driver.findElement(By.xpath("//iframe[@id='job_description_ifr']"))
        );

    
        driver.findElement(By.xpath("//body[@id='tinymce']"))
              .sendKeys("This is a sample job description for automation testing.");

      
        driver.switchTo().defaultContent();
        driver.findElement(By.id("application")).sendKeys("https://alchemy.hguy.co/jobs/job/Test Job/");
        
      
      
     
        driver.findElement(By.xpath("//input[@value='Preview']")).click();

     
        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("job_preview_submit_button"))).click();

       
        wait.until(ExpectedConditions.elementToBeClickable(
                By.linkText("Jobs"))).click();

        WebElement jobTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h3[contains(text(),'Test Job')]")));

        System.out.println("Job Posted Successfully: " + jobTitle.getText());

      
        driver.quit();
    }
}
