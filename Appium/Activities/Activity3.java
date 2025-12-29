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

public class Activity3 {

	AndroidDriver driver;

	@BeforeClass
	public void setUp() throws MalformedURLException, URISyntaxException {

		UiAutomator2Options options = new UiAutomator2Options();
		options.setPlatformName("android");
		options.setAutomationName("UiAutomator2");
		options.setApp(
				"C:\\\\Users\\\\SanjayaPujeri\\\\eclipse-workspace\\\\FST_Appium\\\\src\\\\test\\\\resources\\\\Calculator.apk");
		options.noReset();

		URL serverURL = new URI("http://localhost:4723").toURL();

		driver = new AndroidDriver(serverURL, options);
	}

	@Test
	public void additionTest() {

		driver.findElement(AppiumBy.id("digit_5")).click();
		driver.findElement(AppiumBy.accessibilityId("plus")).click();
		driver.findElement(AppiumBy.id("digit_9")).click();
		driver.findElement(AppiumBy.accessibilityId("equals")).click();

		String result = driver.findElement(AppiumBy.id("result_final")).getText();

		Assert.assertEquals(result, "14");
		System.out.println("Addition  Test Result is : " + result);
	}

	@Test
	public void subtractTest() {

		driver.findElement(AppiumBy.id("digit_1")).click();
		driver.findElement(AppiumBy.id("digit_0")).click();
		driver.findElement(AppiumBy.accessibilityId("minus")).click();
		driver.findElement(AppiumBy.id("digit_5")).click();
		driver.findElement(AppiumBy.accessibilityId("equals")).click();

		String result = driver.findElement(AppiumBy.id("result_final")).getText();

		Assert.assertEquals(result, "5");
		System.out.println("Substract  Test Result is : " + result);
	}

	@Test
	public void multiplyTest() {

		driver.findElement(AppiumBy.id("digit_5")).click();
		driver.findElement(AppiumBy.accessibilityId("multiply")).click();
		driver.findElement(AppiumBy.id("digit_1")).click();
		driver.findElement(AppiumBy.id("digit_0")).click();
		driver.findElement(AppiumBy.id("digit_0")).click();
		driver.findElement(AppiumBy.accessibilityId("equals")).click();

		String result = driver.findElement(AppiumBy.id("result_final")).getText();

		Assert.assertEquals(result, "500");
		System.out.println("Multiply  Test Result is : " + result);
	}

	@Test
	public void divideTest() {

		driver.findElement(AppiumBy.id("digit_5")).click();
		driver.findElement(AppiumBy.id("digit_0")).click();
		driver.findElement(AppiumBy.accessibilityId("divide")).click();
		driver.findElement(AppiumBy.id("digit_2")).click();
		driver.findElement(AppiumBy.accessibilityId("equals")).click();

		String result = driver.findElement(AppiumBy.id("result_final")).getText();

		Assert.assertEquals(result, "25");
		System.out.println("Divide Test Result is : " + result);
	}

	@AfterClass
	public void tearDown() {

		driver.quit();
	}
}