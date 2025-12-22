package activities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Verifywebheading {

    public static void main(String[] args) {

        
        WebDriver driver = new ChromeDriver();


        driver.get("https://alchemy.hguy.co/jobs");

        
        WebElement heading = driver.findElement(By.xpath("//h1[text()='Welcome to Alchemy Jobs']"));

        String actualHeading = heading.getText();
        String expectedHeading = "Welcome to Alchemy Jobs";


        if (actualHeading.equals(expectedHeading)) {
            System.out.println("Heading verification PASSED");
            System.out.println("Heading is: " + actualHeading);
        } else {
            System.out.println("Heading verification FAILED");
            System.out.println("Expected: " + expectedHeading);
            System.out.println("Actual: " + actualHeading);
        }

       
        driver.quit();
    }
}
