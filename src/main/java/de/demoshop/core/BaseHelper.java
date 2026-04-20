package de.demoshop.core;

import com.google.common.io.Files;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;

public class BaseHelper {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BaseHelper(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    protected boolean isElementPresent(By locator) {
        return wait.until(driver -> !driver.findElements(locator).isEmpty());
    }

    protected boolean isElementNotPresent(By locator) {
        return driver.findElements(locator).isEmpty();
    }

    public void type(By locator, String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).click();
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }
    public void typeAndSubmit(By locator, String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).click();
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
        driver.findElement(locator).sendKeys(Keys.ENTER);
    }

    public void click(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).click();
    }

    public boolean verifyByText(By locator, String expectedText) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        WebElement uiText = driver.findElement(locator);
        return uiText.getAttribute("textContent").trim().equals(expectedText);
    }

    public int sizeOfList(By locator) {
        //wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElements(locator).size();
    }

    public void waitForNotificationToDisappear(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String takeScreenshot(){
        File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File screenshot = new File("screenshots/screen-"+ System.currentTimeMillis() + ".png");

        try {
            Files.copy(tmp,screenshot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return screenshot.getPath();
    }
    public WebElement find(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return driver.findElement(locator);
    }
}
