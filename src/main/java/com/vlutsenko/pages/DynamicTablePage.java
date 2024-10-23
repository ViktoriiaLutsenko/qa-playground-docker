package com.vlutsenko.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DynamicTablePage extends AbstractPage {
    private static final Logger log = LoggerFactory.getLogger(DynamicTablePage.class);

    @FindBy(css = "div.container table")
    private WebElement superHeroTable;

    private String superHeroRealNameElementTemplate =
        "//td//div[contains(text(),'%s')]//ancestor::tr//td[3]";

    public DynamicTablePage(WebDriver driver) {
        super(driver);
    }

    public String getSuperHeroRealName(String superHeroName) {
        By superHeroRealNameLocator = By.xpath(String.format(superHeroRealNameElementTemplate, superHeroName));
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(superHeroRealNameLocator));
        String realName = this.driver.findElement(superHeroRealNameLocator).getText();
        log.info("Real name of {} is: {}", superHeroName, realName);
        return realName;
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.superHeroTable));
        return this.superHeroTable.isDisplayed();
    }

}
