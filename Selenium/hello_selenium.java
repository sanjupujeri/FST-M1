package examples;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class hello_selenium {

	public static void main(String[] args) {
		// Initialize the browser
		WebDriver driver = new ChromeDriver();

		// OPEN THE browser
		driver.get("https://training-support.net");

		// do something on browser
		driver.getTitle();
		System.out.println("The tile is:" + driver.getTitle());

		// close the browser
		driver.quit();

	}

}
