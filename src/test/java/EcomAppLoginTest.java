import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.junit.Test;
import pages.HomePage;
import utils.DriverUtils;

import java.net.MalformedURLException;

public class EcomAppLoginTest {

    @Test
    public void loginTest() {
        AndroidDriver driver = DriverUtils.getAndroidDriver("Codefish-Store");
        HomePage homePage = new HomePage(driver);
        homePage.enterName("mydevice");
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        homePage.selectGender();
        homePage.sighIn();
    }

}