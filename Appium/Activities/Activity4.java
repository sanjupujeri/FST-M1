package activities;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Activity4 {

	AndroidDriver driver;
	WebDriverWait wait;

	@BeforeClass
	public void setUp() throws MalformedURLException, URISyntaxException {

		UiAutomator2Options options = new UiAutomator2Options();
		options.setPlatformName("android");
		options.setAutomationName("UiAutomator2");
		options.setAppPackage("com.google.android.contacts");
		options.setAppActivity("com.android.contacts.activities.PeopleActivity");
		options.noReset();

		URL serverURL = new URI("http://localhost:4723").toURL();

		driver = new AndroidDriver(serverURL, options);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@Test
	public void contactsTest() {

		driver.findElement(AppiumBy.className("android.widget.Button")).click();

		wait.until(ExpectedConditions
				.elementToBeClickable(AppiumBy.xpath("//android.widget.EditText[@text='First name']")));

		driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text='First name']")).sendKeys("Aaditya");
		driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text='Last name']")).sendKeys("Varma");
		driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text=\"+1\"]/android.view.View[3]"))
				.sendKeys("999148292");

		driver.findElement(AppiumBy.id("editor_menu_save_button")).click();

		wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("large_title")));

		String contactName = driver.findElement(AppiumBy.id("large_title")).getText();
		Assert.assertEquals(contactName, "Aaditya Varma");
	}

	@AfterClass
	public void tearDown() {

		driver.quit();
	}
}
