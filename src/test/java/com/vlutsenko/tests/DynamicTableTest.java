package com.vlutsenko.tests;

import com.vlutsenko.models.DynamicTableTestData;
import com.vlutsenko.pages.DynamicTablePage;
import com.vlutsenko.pages.HomePage;
import com.vlutsenko.util.Config;
import com.vlutsenko.util.ConfigConstants;
import com.vlutsenko.util.JsonUtil;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static com.vlutsenko.util.Constants.ChallengeNames.DYNAMIC_TABLE;
import static com.vlutsenko.util.Constants.ChallengeTasks.DYNAMIC_TABLE_TASK;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class DynamicTableTest extends AbstractTest {

    private DynamicTableTestData testData;

    @BeforeTest
    @Parameters("testDataPath")
    public void setTestData(String testDataPath) {
        this.testData = JsonUtil.getTestData(testDataPath, DynamicTableTestData.class);
    }

    @Test
    public void dynamicTableChallengeTest() {
        HomePage homePage = new HomePage(driver);
        DynamicTablePage dynamicTablePage = new DynamicTablePage(driver);
        homePage.goTo(Config.get(ConfigConstants.QA_PLAYGROUND_URL));
        assertTrue(homePage.isAt());

        assertEquals(homePage.getChallengeTask(DYNAMIC_TABLE),
            DYNAMIC_TABLE_TASK);

        homePage.goToChallengePage(DYNAMIC_TABLE);
        assertTrue(dynamicTablePage.isAt());

        assertEquals(dynamicTablePage
                .getSuperHeroRealName(testData.superHeroName()),
            testData.superHeroRealName());
    }

}
