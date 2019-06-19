package acro.Pages;

import acro.framework.BasePage;
import acro.framework.BaseTest;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

public class StartPage extends BasePage {

    public ExtentTest test = BaseTest.test;

    private static final String SIGNIN_BUTTON_XPATH = "//button[@class='signinOpenBrowserButton submitButton']";
    private static final String YES_BUTTON_XPATH = "//div[@class='analyticsDialogBody']/button[@class='submitButton']";
    private static final String WEBCHECKER_IFRAME_XPATH = "//div[@id='webchecker']//iframe";
    private static final String SIDEBAR_CONTAINER_IFRAME_XPATH = "//div[@id='sidebarContainer']//iframe";
    private static final String TEXTAREA_XPATH = "//textarea[@id='editor']";
    private static final String CHECK_ICON_XPATH = "//span[@class='btn-group-item-icon icon-canCheck']";
    private static final String CHECK_BUTTON_XPATH = "//span[@class='button-check--inline']";
    private static final String ISSUES_COUNTER_XPATH = "//div[@class='issue-count-banner']";
    private static final String SUGGESTION_XPATH = "//div[@class='suggestions']/div";
    public String windowHandler;


    @FindBy(xpath = SIGNIN_BUTTON_XPATH)
    private WebElement signInButton;

    @FindBy(xpath = YES_BUTTON_XPATH)
    private WebElement yesButton;

    @FindBy(xpath = WEBCHECKER_IFRAME_XPATH)
    private WebElement webcheckerIframe;

    @FindBy(xpath = SIDEBAR_CONTAINER_IFRAME_XPATH)
    private WebElement sidebarContainerIframe;

    @FindBy(xpath = SUGGESTION_XPATH)
    private List<WebElement> suggestions;

    @FindBy(xpath = CHECK_ICON_XPATH)
    private WebElement checkIcon;

    @FindBy(xpath = CHECK_BUTTON_XPATH)
    private WebElement checkButton;

    @FindBy(xpath = TEXTAREA_XPATH)
    private WebElement textArea;

    @FindBy(xpath = ISSUES_COUNTER_XPATH)
    private WebElement issuesCounter;

    public void enterText(String text) {
        test.log(LogStatus.INFO, "Typing text...");
        switchToSidebarContainerIframe();
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(checkIcon));
        new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='tab-content improveTab']")));
        switchToWebcheckerIframe();
        textArea.sendKeys(text);
    }


    public SignInPage clickSignInButton() {
        test.log(LogStatus.INFO, "Clicking 'SignIn' button...");
        switchToSidebarContainerIframe();
        this.windowHandler = driver.getWindowHandle();
        final Set<String> oldWindowsSet = driver.getWindowHandles();
        signInButton.click();
        String newWindow = (new WebDriverWait(driver, 10))
                .until(new ExpectedCondition<String>() {
                           public String apply(WebDriver driver) {
                               Set<String> newWindowsSet = driver.getWindowHandles();
                               newWindowsSet.removeAll(oldWindowsSet);
                               return newWindowsSet.size() > 0 ?
                                       newWindowsSet.iterator().next() : null;
                           }
                       }
                );

        driver.switchTo().window(newWindow);
        return initPage(SignInPage.class);
    }

    public void sayYestoStatistics() {
        test.log(LogStatus.INFO, "Clicking 'YES' button...");
        switchToSidebarContainerIframe();
        yesButton.click();
    }

    private void switchToWebcheckerIframe() {
        driver.switchTo().defaultContent();
        driver.switchTo().frame(webcheckerIframe);
    }

    private void switchToSidebarContainerIframe() {
        switchToWebcheckerIframe();
        driver.switchTo().frame(sidebarContainerIframe);
    }

    public void clickCheckIcon() {
        test.log(LogStatus.INFO, "Clicking 'Check' icon...");
        switchToSidebarContainerIframe();
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(checkIcon));
        checkIcon.click();

    }

    public void clickCheckButton() {
        test.log(LogStatus.INFO, "Clicking 'Check' button...");
        switchToSidebarContainerIframe();
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(checkButton));
        checkButton.click();
    }


    public void chooseSuggestionByText(String text) {
        test.log(LogStatus.INFO, "Clicking '" + text + "' suggestion...");
        switchToSidebarContainerIframe();
        driver.findElement(By.xpath("//span[@class='suggestionLabel'][text()='" + text + "']")).click();
        new WebDriverWait(driver, 3).until(ExpectedConditions.invisibilityOfAllElements(suggestions));
    }

    public String getIssuesCounterText() {
        test.log(LogStatus.INFO, "Getting number of issues...");
        return issuesCounter.getText();
    }

}
