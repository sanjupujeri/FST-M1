package activities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class activity1 {

	public static void main(String[] args) throws InterruptedException {

		WebDriver driver = new ChromeDriver();

		driver.get("https://training-support.net/");

		System.out.println("Title of the Page is " + driver.getTitle());

		driver.findElement(By.linkText("About Us")).click();

		System.out.println("About Us Page Title: " + driver.getTitle());

		driver.quit();

	}

}
