package activities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Verifywebsecondheading {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.get("https://alchemy.hguy.co/jobs");

        WebElement heading = driver.findElement(By.xpath("//h2[contains(text(),'Quia quis non')]"));
        String actualText = heading.getText();

        if (actualText.equals("Quia quis non")) {
            driver.quit();
            System.out.println("Text matched: " + actualText);
        } else {
            System.out.println("Text mismatch: " + actualText);
            driver.quit();
        }
    }
}
