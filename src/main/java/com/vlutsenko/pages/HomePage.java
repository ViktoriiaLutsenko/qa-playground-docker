package com.vlutsenko.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AbstractPage {

    private static final Logger log = LoggerFactory.getLogger(HomePage.class);
    @FindBy(css = ".home-hero h1")
    private WebElement headerElement;

    @FindBy(css = "a[href='/#apps']")
    private WebElement miniWebAppsButton;

    @FindBy(css = "h3")
    private List<WebElement> challengeNameElementList;

    private String challengeTaskTemplate = "//h3[contains(text(), '%s')]//ancestor::div/p";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void goTo(String url) {
        this.driver.get(url);
    }

    public String getHeaderElement() {
        return this.headerElement.getText();
    }

    public void goToMiniWebsApps() {
        this.miniWebAppsButton.click();
    }

    public List<String> getChallengeNameElementList() {
        List<String> challengeNames = new ArrayList<>();
        for (WebElement challengeName : this.challengeNameElementList) {
            challengeNames.add(challengeName.getText());
        }
        return challengeNames;
    }

    public String getChallengeTask(String challengeName) {
        String challengeTask = driver.findElement(By.xpath(
            String.format(this.challengeTaskTemplate, challengeName))).getText();
        log.info("Task for \"{}\" is: {}", challengeName, challengeTask);
        return challengeTask;
    }

    public void goToChallengePage(String challengeName) {
        driver.findElement(By.xpath(
            String.format(this.challengeTaskTemplate, challengeName))).click();
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.headerElement));
        return this.headerElement.isDisplayed();
    }

}
