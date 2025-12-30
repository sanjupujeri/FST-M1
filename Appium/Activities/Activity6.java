package activities;

import static org.testng.Assert.assertTrue;

import java.awt.Dimension;
import java.awt.Point;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Activity6 {
	AndroidDriver driver;
	WebDriverWait wait;

	@BeforeClass
	public void setUp() throws MalformedURLException, URISyntaxException {
		UiAutomator2Options options = new UiAutomator2Options();
		options.setPlatformName("ANDROID");
		options.setAutomationName("UiAutomator2");
		options.setAppPackage("com.android.chrome");
		options.setAppActivity("com.google.android.apps.chrome.Main");
		options.setCapability("chromedriverAutodownload", true);

		URL serverURL = new URI("http://localhost:4723").toURL();

		driver = new AndroidDriver(serverURL, options);
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		driver.get("https://training-support.net/webelements/sliders");
	}

	@Test
	public void volume75Test() {

		for (String context : driver.getContextHandles()) {
			if (context.contains("WEBVIEW")) {
				driver.context(context);
				break;
			}
		}

		WebElement slider = wait.until(ExpectedConditions.elementToBeClickable(By.id("volume")));

		Actions actions = new Actions(driver);
		actions.clickAndHold(slider).moveByOffset(150, 0).release().perform();

		String volumeText = driver.findElement(By.id("value")).getText();
		assertTrue(volumeText.contains("75"));
	}

	@Test
	public void volume25Test() {

		for (String context : driver.getContextHandles()) {
			if (context.contains("WEBVIEW")) {
				driver.context(context);
				break;
			}
		}

		WebElement slider = wait.until(ExpectedConditions.elementToBeClickable(By.id("volume")));

		Actions actions = new Actions(driver);
		actions.clickAndHold(slider).moveByOffset(-150, 0).release().perform();

		String volumeText = driver.findElement(By.id("value")).getText();
		assertTrue(volumeText.contains("25"));
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
