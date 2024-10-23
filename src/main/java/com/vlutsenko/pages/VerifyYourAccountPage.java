package com.vlutsenko.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class VerifyYourAccountPage extends AbstractPage {

    private static final Logger log = LoggerFactory.getLogger(VerifyYourAccountPage.class);

    @FindBy(className = "info")
    private WebElement confirmationCodeElement;

    @FindBy(className = "code")
    private List<WebElement> codeInputList;

    @FindBy(css = ".info.success")
    private WebElement successMessageElement;

    @FindBy(css = "a[href='/#apps']")
    private WebElement appsButton;

    public VerifyYourAccountPage(WebDriver driver) {
        super(driver);
    }

    public char[] getConfirmationCode() {
        String fullMessage = this.confirmationCodeElement.getText();
        String[] splitFullMessage = fullMessage.split(" ");
        char[] confirmationCode = splitFullMessage[splitFullMessage.length - 1]
            .replace("-", "")
            .toCharArray();
        log.info("Confirmation code is: {}", confirmationCode);
        return confirmationCode;
    }

    public void enterCodeByKeys(char[] code) {
        for (int i = 0; i < codeInputList.size(); i++) {
            for (int j = 0; j < code[i] - '0'; j++) {
                codeInputList.get(i).sendKeys(Keys.UP);
            }
        }
    }

    public void enterCodeByValues(char[] code) {
        for (int i = 0; i < codeInputList.size(); i++) {
            codeInputList.get(i).sendKeys(String.valueOf(code[i]));
        }
    }

    public boolean successMessageIsPresent() {
        this.wait.until(ExpectedConditions.visibilityOf(this.successMessageElement));
        return this.successMessageElement.isDisplayed();
    }

    public void goToApps() {
        this.appsButton.click();
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.confirmationCodeElement));
        return this.confirmationCodeElement.isDisplayed();
    }
}
