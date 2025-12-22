package activities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class VerifyTitle {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();

		driver.get("https://alchemy.hguy.co/jobs/");

		String actualTitle = driver.getTitle();
		String expectedTitle = "Alchemy Jobs – Job Board Application";

		if (actualTitle.equals(expectedTitle)) {
			System.out.println("Title verification PASSED");
			System.out.println("Title is: " + actualTitle);
		} else {
			System.out.println("Title verification FAILED");
			System.out.println("Expected: " + expectedTitle);
			System.out.println("Actual: " + actualTitle);
		}

		driver.quit();
	}
}
