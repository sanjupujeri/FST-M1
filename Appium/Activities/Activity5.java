package activities;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Activity5 {

	AndroidDriver driver;
	WebDriverWait wait;


	@BeforeClass
	public void setUp() throws MalformedURLException, URISyntaxException {
		
		UiAutomator2Options options = new UiAutomator2Options();
		options.setPlatformName("android");
		options.setAutomationName("UiAutomator2");
		options.setAppPackage("com.google.android.apps.messaging");
		options.setAppActivity(".ui.ConversationListActivity");

		
		URL serverURL = new URI("http://localhost:4723").toURL();
	
		driver = new AndroidDriver(serverURL, options);
	}


	@Test
	public void smsTest() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath(
				"//android.widget.Button[@resource-id=\"com.google.android.apps.messaging:id/secondary_action_button\"]")))
				.click();

		wait.until(ExpectedConditions
				.elementToBeClickable(AppiumBy.xpath("//android.widget.Button[@content-desc='Start chat']"))).click();

		
		wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.className("android.widget.EditText"))).click();
		System.out.println("tested");

		
		WebElement phoneInput = wait.until(
			    ExpectedConditions.elementToBeClickable(
			        AppiumBy.className("android.widget.EditText")
			    )
			);

			phoneInput.click();
			phoneInput.sendKeys("9591163389");
		
		driver.pressKey(new KeyEvent(AndroidKey.ENTER));

		
		wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.className("android.widget.EditText")));

	
		driver.findElement(AppiumBy.className("android.widget.EditText"))
				.sendKeys("Hello from Appium");
		
		driver.findElement(AppiumBy.xpath("//android.view.View[@content-desc=\"Send SMS\"]")).click();

	
		String messageTextSent = driver.findElement(AppiumBy.className("android.widget.TextView")).getText();
		Assert.assertEquals(messageTextSent, "Hello from Appium");
		System.out.println("Messege sent is :" + messageTextSent);
	}


	@AfterClass
	public void tearDown() {
		
		driver.quit();
	}
}