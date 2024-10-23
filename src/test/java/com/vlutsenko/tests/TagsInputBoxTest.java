package com.vlutsenko.tests;

import com.vlutsenko.pages.HomePage;
import com.vlutsenko.pages.TagsInputBoxPage;
import com.vlutsenko.util.Config;
import com.vlutsenko.util.ConfigConstants;
import com.vlutsenko.util.RandomStringUtil;
import org.testng.annotations.Test;

import static com.vlutsenko.util.Constants.ChallengeNames.TAGS_INPUT_BOX;
import static com.vlutsenko.util.Constants.ChallengeTasks.TAGS_INPUT_BOX_TASK;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TagsInputBoxTest extends AbstractTest {

    @Test
    public void tagsInputBoxChallengeTest() {
        HomePage homePage = new HomePage(driver);
        TagsInputBoxPage tagsInputBoxPage = new TagsInputBoxPage(driver);
        homePage.goTo(Config.get(ConfigConstants.QA_PLAYGROUND_URL));
        assertTrue(homePage.isAt());

        assertEquals(homePage
                .getChallengeTask(TAGS_INPUT_BOX),
            TAGS_INPUT_BOX_TASK);

        homePage.goToChallengePage(TAGS_INPUT_BOX);
        assertTrue(tagsInputBoxPage.isAt());

        assertEquals(tagsInputBoxPage.getNumberOfPresentTags(), 2);
        assertEquals(tagsInputBoxPage.getNumberOfRemainingTags(), 8);

        String tag1 = RandomStringUtil.generateRandomAlphabetical(5);
        tagsInputBoxPage.addTag(tag1);
        assertEquals(tagsInputBoxPage.getNumberOfPresentTags(), 3);
        assertEquals(tagsInputBoxPage.getNumberOfRemainingTags(), 7);

        String tag2 = RandomStringUtil.generateRandomAlphabetical(5);
        String tagList3 = tag2 + ", " + RandomStringUtil.generateRandomAlphabetical(5)
            + ", " + RandomStringUtil.generateRandomAlphabetical(5);
        tagsInputBoxPage.addTag(tagList3);
        assertEquals(tagsInputBoxPage.getNumberOfPresentTags(), 6);
        assertEquals(tagsInputBoxPage.getNumberOfRemainingTags(), 4);

        String tagList4 = RandomStringUtil.generateRandomAlphabetical(5)
            + ", " + RandomStringUtil.generateRandomAlphabetical(5)
            + ", " + RandomStringUtil.generateRandomAlphabetical(5)
            + ", " + RandomStringUtil.generateRandomAlphabetical(5)
            + ", " + RandomStringUtil.generateRandomAlphabetical(5);
        tagsInputBoxPage.addTag(tagList4);
        assertEquals(tagsInputBoxPage.getNumberOfPresentTags(), 11);
        assertEquals(tagsInputBoxPage.getNumberOfRemainingTags(), -1);

        tagsInputBoxPage.removeTag(tag1);
        tagsInputBoxPage.removeTag(tag2);
        assertEquals(tagsInputBoxPage.getNumberOfPresentTags(), 9);
        assertEquals(tagsInputBoxPage.getNumberOfRemainingTags(), 1);

        tagsInputBoxPage.removeAll();
        assertEquals(tagsInputBoxPage.getNumberOfPresentTags(), 0);
        assertEquals(tagsInputBoxPage.getNumberOfRemainingTags(), 10);
    }

}
