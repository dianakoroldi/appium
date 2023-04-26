package locators;

import io.appium.java_client.android.AndroidDriver;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class XpathLocator {

    @Test
    public void connectionTest() throws MalformedURLException {
        File apkFile=new File("src/test/resources/ApiDemos-debug.apk");
        DesiredCapabilities caps=new DesiredCapabilities();
        caps.setCapability("platformName","android");
        caps.setCapability("deviceName","mydevice");
        caps.setCapability("automationName","uiautomator2"); //android specific automation tool
        caps.setCapability("app",apkFile.getAbsolutePath());

        URL appiumServerUrl =new URL("http://0.0.0.0:4723/wd/hub");
        AndroidDriver driver=new AndroidDriver(appiumServerUrl,caps);

       WebElement accessibilityButton =
               driver.findElement(By.xpath("//android.widget.TextView[@text='Accessibility']"));
       accessibilityButton.click();

       List<WebElement> elements = driver.findElements(By.id("android:id/text1"));
        Assert.assertEquals(4, elements.size());


    }
}
