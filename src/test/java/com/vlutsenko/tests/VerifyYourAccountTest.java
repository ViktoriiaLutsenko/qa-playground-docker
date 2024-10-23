package com.vlutsenko.tests;

import com.vlutsenko.pages.HomePage;
import com.vlutsenko.pages.VerifyYourAccountPage;
import com.vlutsenko.util.Config;
import com.vlutsenko.util.ConfigConstants;
import org.testng.annotations.Test;

import static com.vlutsenko.util.Constants.ChallengeNames.VERIFY_YOUR_ACCOUNT;
import static com.vlutsenko.util.Constants.ChallengeTasks.VERIFY_YOUR_ACCOUNT_TASK;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class VerifyYourAccountTest extends AbstractTest {

    @Test
    public void verifyYourAccountChallengeTest() {
        HomePage homePage = new HomePage(driver);
        VerifyYourAccountPage verifyYourAccountPage = new VerifyYourAccountPage(driver);
        homePage.goTo(Config.get(ConfigConstants.QA_PLAYGROUND_URL));
        assertTrue(homePage.isAt());

        assertEquals(homePage
                .getChallengeTask(VERIFY_YOUR_ACCOUNT),
            VERIFY_YOUR_ACCOUNT_TASK);

        homePage.goToChallengePage(VERIFY_YOUR_ACCOUNT);
        assertTrue(verifyYourAccountPage.isAt());

        char[] confirmationCode = verifyYourAccountPage.getConfirmationCode();

        verifyYourAccountPage.enterCodeByKeys(confirmationCode);
        assertTrue(verifyYourAccountPage.successMessageIsPresent());

        verifyYourAccountPage.goToApps();
        assertTrue(homePage.isAt());

        homePage.goToChallengePage(VERIFY_YOUR_ACCOUNT);
        assertTrue(verifyYourAccountPage.isAt());

        verifyYourAccountPage.enterCodeByValues(confirmationCode);
        assertTrue(verifyYourAccountPage.successMessageIsPresent());
    }

}
