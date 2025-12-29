package activities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class ScreenshotExample {
    static AppiumDriver driver;
 
    @BeforeClass
    public void setUp() throws MalformedURLException {
        // Desired Capabilities
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setAutomationName("UiAutomator2");
        options.setAppPackage("com.android.chrome");
        options.setAppActivity("com.google.android.apps.chrome.Main");
        options.noReset();
 
        // Server URL
        URL serverURL = new URL("http://localhost:4723");
 
        // Driver initialization
        driver = new AndroidDriver(serverURL, options);
        driver.get("https://training-support.net");
    }
 
    // Test method
    @Test
    public void chromeTest() throws IOException {
        // Find heading on the page
        String pageHeading = driver.findElement(AppiumBy.xpath(
                "//android.widget.TextView[@text=\"Training Support\"]"
        )).getText();
 
        // Print to console
        System.out.println("Heading: " + pageHeading);
 
        // Take screenshot of the page
        takeScreenshot();
 
    }
 
    public static void takeScreenshot() throws IOException {
        // Take screenshot
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
 
        // This specifies the location the screenshot will be saved
        File screenShotName = new File("src/test/resources/screenshot.jpg");
 
        // This will copy the screenshot to the file created
        FileUtils.copyFile(scrFile, screenShotName);
 
        // Set filepath for image
        String filePath = "../" + screenShotName;
 
        // Set image HTML in the report
        String path = "<img src='" + filePath + "'/>";
 
        // Show image in report
        Reporter.log(path);
    }
 
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}