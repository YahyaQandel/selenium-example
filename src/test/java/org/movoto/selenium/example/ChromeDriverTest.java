package org.movoto.selenium.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by haozuo on 3/22/16.
 */
public class ChromeDriverTest {

    public static final String ACCOUNT_USERNAME = "Yahya";
    public static final String ACCOUNT_PHONENUMBER = "01090614633";
    private static final String VODAFONE_TEST_URL = "https://web.vodafone.com.eg/ar/home";
    private WebDriver driver;

    @Before
    public void prepare() {
        driver = new ChromeDriver();
        driver.get(VODAFONE_TEST_URL);
    }

    @Test
    public void testAccountLogin() throws IOException {
        openLoginLayout();
        loginToAccount();
        WebElement accountHeadingTitle = waitForElementToBeVisible(By.className("service-selector__active-heading"));
        String AccountHeadingTitle = accountHeadingTitle.getText();
        assertAccountLoginDetailsAreExists(AccountHeadingTitle);

    }
    private void loginToAccount() {
        WebElement loginNumberField = waitForElementToBeVisible(By.id("loginNum"));
        loginNumberField.sendKeys("01090614633");
        WebElement loginPasswordField = waitForElementToBeVisible(By.id("loginPassword"));
        loginPasswordField.sendKeys("oNe_status_1");
        WebElement loginBtn = waitForElementToBeVisible(By.id("loginButton"));
        loginBtn.click();
    }
    private void openLoginLayout() {
        WebElement loginLayoutBtn = waitForElementToBeVisible(By.id("innerLoginBtn"));
        loginLayoutBtn.click();
    }
    private void assertAccountLoginDetailsAreExists(String accountHeadingTitle) {
        assertTrue(accountHeadingTitle.contains(ACCOUNT_USERNAME));
        assertTrue(accountHeadingTitle.contains(ACCOUNT_PHONENUMBER));
    }
    private WebElement waitForElementToBeVisible(By selector) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(selector));
    }

    @After
    public void teardown() throws IOException {
        driver.quit();
    }

}
