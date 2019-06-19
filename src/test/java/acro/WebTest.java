package acro;

import acro.Pages.*;
import acro.framework.BasePage;
import acro.framework.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;


public class WebTest extends BaseTest {

    private StartPage startPage;
    private static final String USERNAME = "alexander.ognev";
    private static final String PASSWORD = "SsN2zTEp51tw";

    @Test
    public void acrolinxTest() {
        startPage = BasePage.initPage(StartPage.class);
        SignInPage signInPage = startPage.clickSignInButton();
        ConfirmPage confirmPage = signInPage.signIn(USERNAME, PASSWORD);
        startPage = confirmPage.clickConfirm(startPage.windowHandler);
        startPage.sayYestoStatistics();
        startPage.enterText("This is a testt");
        startPage.clickCheckIcon();
        startPage.clickCheckButton();
        startPage.chooseSuggestionByText("test");
        Assert.assertEquals(startPage.getIssuesCounterText(), "0 issues", "Number of issues is wrong");
    }

}


