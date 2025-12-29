package activities;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class Activity1 {

	AndroidDriver driver;

	@BeforeClass
	public void setUp() throws MalformedURLException, URISyntaxException {
		// Desired Capabilities
		UiAutomator2Options options = new UiAutomator2Options();
		options.setPlatformName("android");
		options.setAutomationName("UiAutomator2");
		options.setApp("C:\\Users\\SanjayaPujeri\\eclipse-workspace\\FST_Appium\\src\\test\\resources\\Calculator.apk");
		options.noReset();

		URL serverURL = new URI("http://localhost:4723").toURL();

		driver = new AndroidDriver(serverURL, options);
	}

	@Test
	public void multiplyTest() {

		driver.findElement(AppiumBy.id("digit_5")).click();
		driver.findElement(AppiumBy.accessibilityId("multiply")).click();
		driver.findElement(AppiumBy.id("digit_8")).click();
		driver.findElement(AppiumBy.accessibilityId("equals")).click();

		String result = driver.findElement(AppiumBy.id("result_final")).getText();

		Assert.assertEquals(result, "40");
	}

	@AfterClass
	public void tearDown() {

		driver.quit();
	}
}