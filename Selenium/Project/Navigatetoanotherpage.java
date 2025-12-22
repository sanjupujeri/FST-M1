package activities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Navigatetoanotherpage {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.get("https://alchemy.hguy.co/jobs");

        driver.findElement(By.linkText("Jobs")).click();

        String title = driver.getTitle();

        if (title.contains("Jobs")) {
            driver.quit();
            System.out.println("On Jobs page: " + title);
            
        } else {
            System.out.println("Not on Jobs page: " + title);
            driver.quit();
        }
    }
}
