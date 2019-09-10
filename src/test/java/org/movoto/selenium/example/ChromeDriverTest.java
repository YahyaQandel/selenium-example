package org.movoto.selenium.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.MarionetteDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by haozuo on 3/22/16.
 */
public class ChromeDriverTest {

    private String testUrl;
    private WebDriver driver;

    @Before
    public void prepare() {
        //setup chromedriver
        System.setProperty(
                "webdriver.chrome.driver",
                "webdriver/chromedriver");

        testUrl = "https://web.vodafone.com.eg/ar/home";

        // Create a new instance of the Chrome driver
        // Notice that the remainder of the code relies on the interface,
        // not the implementation.
        driver = new ChromeDriver();

        //maximize window
//        driver.manage().window().maximize();

        // And now use this to visit myBlog
        // Alternatively the same thing can be done like this
        // driver.navigate().to(testUrl);
        driver.get(testUrl);
    }

    @Test
    public void testTitle() throws IOException {

        // Find elements by attribute lang="READ_MORE_BTN"
        List<WebElement> loginLayoutBtn = driver
                .findElements(By.id("innerLoginBtn"));
        //Click the selected button
        loginLayoutBtn.get(0).click();

        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement loginNumberField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("loginNum")));

        loginNumberField.sendKeys("01090614633");


        WebElement loginPasswordField = driver
                .findElements(By.id("loginPassword")).get(0);
        loginPasswordField.sendKeys("oNe_status_1");
//
        WebElement loginBtn = driver
                .findElements(By.id("loginButton")).get(0);
        loginBtn.click();


        WebElement accountHeadingTitle = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.className("service-selector__active-heading")));
        String AccountHeadingTitle = accountHeadingTitle.getText();
        assertTrue(AccountHeadingTitle.contains("Yahya"));
        assertTrue(AccountHeadingTitle.contains("01090614633"));

    }

    @After
    public void teardown() throws IOException {
        driver.quit();
    }

}
