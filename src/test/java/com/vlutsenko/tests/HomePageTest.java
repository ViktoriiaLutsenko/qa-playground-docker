package com.vlutsenko.tests;

import com.vlutsenko.pages.HomePage;
import com.vlutsenko.util.Config;
import com.vlutsenko.util.ConfigConstants;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static com.vlutsenko.util.Constants.ChallengeNames.*;
import static com.vlutsenko.util.Constants.Headers.HOME_PAGE_HEADER;
import static com.vlutsenko.util.Constants.URLs.APPS_URL;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class HomePageTest extends AbstractTest {

    @Test
    public void homePageTest() {
        HomePage homePage = new HomePage(driver);
        homePage.goTo(Config.get(ConfigConstants.QA_PLAYGROUND_URL));
        assertTrue(homePage.isAt());
        assertEquals(homePage.getHeaderElement(), HOME_PAGE_HEADER);

        homePage.goToMiniWebsApps();
        assertEquals(driver.getCurrentUrl(),
            Config.get(ConfigConstants.QA_PLAYGROUND_URL) + APPS_URL);

        List<String> expectedListOfChallengeName = Arrays.asList(
            DYNAMIC_TABLE,
            VERIFY_YOUR_ACCOUNT,
            TAGS_INPUT_BOX,
            MULTI_LEVEL_DROPDOWN,
            SORTABLE_LIST,
            NEW_TAB,
            POP_UP_WINDOW,
            NESTED_IFRAME,
            SHADOW_DOM,
            STARS_RATING_WIDGET,
            COVERED_ELEMENTS,
            UPLOAD_FILE,
            DOWNLOAD_FILE,
            ONBOARDING_MODAL_POPUP,
            BUDGET_TRACKER,
            RIGHT_CLICK_CONTEXT_MENU,
            MOUSE_HOVER,
            GEOLOCATION,
            NAVIGATION_MENU,
            REDIRECT_CHAIN,
            FETCHING_DATA,
            QR_CODE_GENERATOR,
            CHANGEABLE_IFRAME,
            RATING_RANGE_SLIDER);
        assertEquals(homePage.getChallengeNameElementList(), expectedListOfChallengeName);
    }

}
