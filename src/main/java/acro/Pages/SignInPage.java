package acro.Pages;

import acro.framework.BasePage;
import acro.framework.BaseTest;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPage extends BasePage {

    public ExtentTest test = BaseTest.test;

    private static final String USERNAME_TEXTBOX_XPATH = "//input[@placeholder='Username']";
    private static final String PASSWORD_TEXTBOX_XPATH = "//input[@placeholder='Password']";
    private static final String SIGNIN_BUTTON_XPATH = "//button[@data-test-id='button--signin']";


    @FindBy(xpath = USERNAME_TEXTBOX_XPATH)
    private WebElement usernameTextbox;

    @FindBy(xpath = PASSWORD_TEXTBOX_XPATH)
    private WebElement passwordTextbox;

    @FindBy(xpath = SIGNIN_BUTTON_XPATH)
    private WebElement signInButton;

    public ConfirmPage signIn(String username, String password) {
        test.log(LogStatus.INFO, "Signing in...");
        usernameTextbox.sendKeys(username);
        passwordTextbox.sendKeys(password);
        signInButton.click();
        return initPage(ConfirmPage.class);
    }

}
