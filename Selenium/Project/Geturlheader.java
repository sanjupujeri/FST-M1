package activities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Geturlheader {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.get("https://alchemy.hguy.co/jobs");

        WebElement img = driver.findElement(By.xpath("//header//img"));
        String url = img.getAttribute("src");

        System.out.println( "The url of the header image is :" + url);

        driver.quit();
    }
}
