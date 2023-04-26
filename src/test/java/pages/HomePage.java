package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class HomePage {

    public HomePage(AndroidDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);

    }
    @AndroidFindBy (id = "com.androidsample.generalstore:id/nameField")
    WebElement nameField;

    @AndroidFindBy (id = "com.androidsample.generalstore:id/radioFemale")
    WebElement femaleRadioButton;

    @AndroidFindBy (id = "com.androidsample.generalstore:id/btnLetsShop")
    WebElement letsShopButton;


    public void enterName(String name){
        this.nameField.click();
        this.nameField.sendKeys(name);
    }

    public void selectGender(){
        femaleRadioButton.click();
    }

    public void sighIn(){
        letsShopButton.click();
    }
}
