package utils;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class BrowserUtils {
    public String scrollByUIAutomator (String attribute, String value){
        String scroll = "new UiScrollable(new UiSelector()).scrollIntoView(".concat(attribute).concat("(\"")
                .concat(value).concat("\"))");
        return scroll;
    }

    public static  void tapOptions(TouchAction touchAction, WebElement element){
        touchAction.tap(TapOptions.tapOptions()
                        .withElement(ElementOption.element(element)))
                .perform();
    }
}
