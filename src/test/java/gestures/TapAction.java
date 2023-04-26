package gestures;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.BrowserUtils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class TapAction {
    @Test
    public void tapTest() throws MalformedURLException {
        File apkFile = new File("src/test/resources/ApiDemos-debug.apk");
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setPlatform(Platform.ANDROID);
        caps.setCapability("deviceName", "mydevice");
        caps.setCapability("automationName", "uiautomator2");
        caps.setCapability("app", apkFile.getAbsolutePath());

        URL serverUrl = new URL("http://0.0.0.0:4723/wd/hub");
        AndroidDriver driver = new AndroidDriver(serverUrl, caps);

        WebElement viewsButton =
                driver.findElement(By.xpath("//android.widget.TextView[@text='Views']"));
        //Actions actions = new Actions(driver);   // use from Selenium interactions
        TouchAction touchAction = new TouchAction(driver);
        touchAction.tap(TapOptions.tapOptions().withElement(ElementOption.element(viewsButton))).perform();
        //tap works as click, but it is more mechanical, but better and professional use 'tap'

        //Task
        WebElement expandableLists =
                driver.findElementByAccessibilityId("Expandable Lists");
        touchAction.tap(TapOptions.tapOptions().withElement(ElementOption.element(expandableLists))).perform();


        WebElement customAdapter =
                driver.findElement(By.xpath("//android.widget.TextView[@text='1. Custom Adapter']"));
        touchAction.tap(TapOptions.tapOptions().withElement(ElementOption.element(customAdapter))).perform();

        WebElement peopleNameButn =
                driver.findElement(By.xpath("//*[@text='People Names']"));
        touchAction.longPress(LongPressOptions.longPressOptions()
                .withElement(ElementOption.element(peopleNameButn))
                .withDuration(Duration.ofSeconds(2))).perform();

        //HomeWork

        WebElement sampleMenu = driver.findElement(By.xpath("//*[@text='Sample menu']"));
        WebElement sampleAction = driver.findElement(By.xpath("//*[@text='Sample action']"));
        String actualText = sampleMenu.getText() + " " + sampleAction.getText();
        String expectedText = "Sample menu Sample action";
        Assert.assertEquals(expectedText, actualText);

    }

    @Test
    public void scrollTest() throws MalformedURLException {

        File apkFile = new File("src/test/resources/ApiDemos-debug.apk");
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "mydevice");
        caps.setCapability(MobileCapabilityType.APP, apkFile.getAbsolutePath());
        caps.setCapability(MobileCapabilityType.APPLICATION_NAME, "uiautomator2");
        caps.setPlatform(Platform.ANDROID);

        URL appiumServerUrl = new URL("http://0.0.0.0:4723/wd/hub");

        AndroidDriver driver = new AndroidDriver(appiumServerUrl, caps);

        WebElement viewsButton =
                driver.findElement(By.xpath("//android.widget.TextView[@text='Views']"));

        TouchAction touchAction = new TouchAction(driver);
        touchAction.tap(TapOptions.tapOptions().withElement(ElementOption.element(viewsButton))).perform();

        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Tabs\"))");
        WebElement tabsButton = driver.findElementByAccessibilityId("Tabs");
        //command --> shift--> v --> it will show the history on copy before (good for reusable)
    }

    @Test
    public void setClockArrow() throws MalformedURLException, InterruptedException {
//Homework:
//   1. Navigate Views -> Date Widgets -> Inline
//   2. Set clock arrow to 3:45, 6:05, 10:25
        File apkFile = new File("src/test/resources/ApiDemos-debug.apk");
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "mydevice");
        caps.setCapability(MobileCapabilityType.APP, apkFile.getAbsolutePath());
        caps.setCapability(MobileCapabilityType.APPLICATION_NAME, "uiautomator2");
        caps.setPlatform(Platform.ANDROID);
        URL appiumServerUrl = new URL("http://0.0.0.0:4723/wd/hub");
        AndroidDriver driver = new AndroidDriver(appiumServerUrl, caps);

        WebElement viewsButton =
                driver.findElement(By.xpath("//android.widget.TextView[@text='Views']"));
        TouchAction touchAction = new TouchAction(driver);
        BrowserUtils.tapOptions(touchAction, viewsButton);

        WebElement dateWidgetsButton = driver.findElement(By.xpath("//android.widget.TextView[@text='Date Widgets']"));
        BrowserUtils.tapOptions(touchAction, dateWidgetsButton);
        WebElement inlineButton = driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"2. Inline\"]"));
        BrowserUtils.tapOptions(touchAction, inlineButton);

        WebElement hour = driver.findElement(By.xpath("//android.widget.RadialTimePickerView.RadialPickerTouchHelper[@content-desc=\"3\"]"));
        touchAction.tap(TapOptions.tapOptions().withElement(ElementOption.element(hour))).perform();
        // Set the minute to 45
        WebElement minute = driver.findElement(By.xpath("//android.widget.RadialTimePickerView.RadialPickerTouchHelper[@content-desc=\"45\"]"));
        touchAction.longPress(LongPressOptions.longPressOptions()
                        .withDuration(Duration.ofSeconds(1))
                        .withElement(ElementOption.element(minute))).
                release().perform();
        WebElement setHour = driver.findElement(By.id("android:id/hours"));
        BrowserUtils.tapOptions(touchAction, setHour);
Thread.sleep(1000);
//set 6:05
        hour = driver.findElement(By.xpath("//android.widget.RadialTimePickerView.RadialPickerTouchHelper[@content-desc=\"6\"]"));
        touchAction.tap(TapOptions.tapOptions().withElement(ElementOption.element(hour))).perform();
        Thread.sleep(1000);
        minute = driver.findElement(By.xpath("//android.widget.RadialTimePickerView.RadialPickerTouchHelper[@content-desc=\"5\"]"));
        touchAction.longPress(LongPressOptions.longPressOptions()
                        .withDuration(Duration.ofSeconds(2))
                        .withElement(ElementOption.element(minute)))
                .release().perform();

        BrowserUtils.tapOptions(touchAction, setHour);
        Thread.sleep(1000);
        //set 10:25
        hour = driver.findElement(By.xpath("//android.widget.RadialTimePickerView.RadialPickerTouchHelper[@content-desc=\"10\"]"));
        touchAction.tap(TapOptions.tapOptions().withElement(ElementOption.element(hour))).perform();
        Thread.sleep(1000);
        minute = driver.findElement(By.xpath("//android.widget.RadialTimePickerView.RadialPickerTouchHelper[@content-desc=\"25\"]"));
        touchAction.longPress(LongPressOptions.longPressOptions()
                        .withDuration(Duration.ofSeconds(2))
                        .withElement(ElementOption.element(minute)))
                .release().perform();


    }
}