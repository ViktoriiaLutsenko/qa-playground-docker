package com.vlutsenko.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TagsInputBoxPage extends AbstractPage {

    private static final Logger log = LoggerFactory.getLogger(TagsInputBoxPage.class);

    @FindBy(css = ".content ul li")
    private List<WebElement> tagElementList;

    @FindBy(css = ".content input")
    private WebElement tagInput;

    @FindBy(css = ".details")
    private WebElement tagRemainingElement;

    @FindBy(css = ".details button")
    private WebElement removeAllButton;

    private String tagRemoveElementTemplate = "//ul//li[text()='%s ']//i";

    public TagsInputBoxPage(WebDriver driver) {
        super(driver);
    }

    public int getNumberOfPresentTags() {
        return this.tagElementList.size();
    }

    public int getNumberOfRemainingTags() {
        String fullMessage = this.tagRemainingElement.getText();
        String[] splitFullMessage = fullMessage.split(" ");
        int numberOfRemainingTags = Integer.parseInt(splitFullMessage[0]);
        log.info("Remaining amount of tags is {}", numberOfRemainingTags);
        return numberOfRemainingTags;
    }

    public void addTag(String tags) {
        this.tagInput.sendKeys(tags);
        this.tagInput.sendKeys(Keys.ENTER);
        log.info("Next tag(s) are added: {}", tags);
    }

    public void removeTag(String tag) {
        driver.findElement(By.xpath(
            String.format(this.tagRemoveElementTemplate, tag))).click();
        log.info("Next tag is removed: {}", tag);
    }

    public void removeAll() {
        this.removeAllButton.click();
        log.info("All tags are removed");
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.removeAllButton));
        return this.removeAllButton.isDisplayed();
    }
}
