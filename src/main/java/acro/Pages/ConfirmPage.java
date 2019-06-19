package acro.Pages;

import acro.framework.BasePage;
import acro.framework.BaseTest;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ConfirmPage extends BasePage {

    public ExtentTest test = BaseTest.test;


    private static final String CONFIRM_BUTTON_XPATH = "//button[text()='CONFIRM']";
    private static final String CLOSE_CONFIRMATION_XPATH = "//p[contains(text(),'You can now close this page')]";


    @FindBy(xpath = CONFIRM_BUTTON_XPATH)
    private WebElement confirmButton;

    @FindBy(xpath = CLOSE_CONFIRMATION_XPATH)
    private WebElement closeConfirmation;

    public StartPage clickConfirm(String window) {
        test.log(LogStatus.INFO, "Clicking CONFIRM button...");
        confirmButton.click();
        test.log(LogStatus.INFO, "Closing current tab...");
        driver.close();
        test.log(LogStatus.INFO, "Switching to original window...");
        driver.switchTo().window(window);
        return initPage(StartPage.class);
    }
}
