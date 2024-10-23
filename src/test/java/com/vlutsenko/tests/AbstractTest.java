package com.vlutsenko.tests;

import com.vlutsenko.listener.TestListener;
import com.vlutsenko.util.Config;
import com.vlutsenko.util.ConfigConstants;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import java.net.MalformedURLException;
import java.net.URL;

@Listeners({TestListener.class})
public abstract class AbstractTest {
    private static final Logger log = LoggerFactory.getLogger(AbstractTest.class);

    protected WebDriver driver;

    @BeforeSuite
    public void setUpConfig(){
        Config.initialize();
    }

    @BeforeTest
    public void setDriver(ITestContext context) throws MalformedURLException {
        this.driver = Boolean.parseBoolean(Config.get(ConfigConstants.GRID_ENABLED))
            ? getRemoteDriver()
            : getLocalDriver();
        context.setAttribute(ConfigConstants.DRIVER, this.driver);
    }

    private WebDriver getLocalDriver(){
        return  new ChromeDriver();
    }

    private WebDriver getRemoteDriver() throws MalformedURLException {
        Capabilities capabilities;
        String browser = Config.get(ConfigConstants.BROWSER);
        if(ConfigConstants.FIREFOX.equalsIgnoreCase(browser)){
            capabilities = new FirefoxOptions();
        } else {
            capabilities = new ChromeOptions();
        }

        String urlFormat = Config.get(ConfigConstants.GRID_URL_FORMAT);
        String hubHost = Config.get(ConfigConstants.GRID_HUB_HOST);
        String url = urlFormat.formatted(hubHost);
        log.info("Grid url: {}", url);
        return new RemoteWebDriver(new URL(url), capabilities);
    }

    @AfterTest
    public void quitDriver() {
        this.driver.quit();
    }
}
