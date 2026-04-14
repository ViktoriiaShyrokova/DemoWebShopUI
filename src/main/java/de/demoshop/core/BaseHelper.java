package de.demoshop.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BaseHelper {

    protected WebDriver driver;

    public BaseHelper(WebDriver driver) {
        this.driver = driver;
    }

    protected boolean isElementPresent(By locator) {
        return !driver.findElements(locator).isEmpty();
    }

    public void type(By locator, String text) {
        driver.findElement(locator).click();
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public boolean verifyByText(By locator, String expectedText) {
        WebElement uiText = driver.findElement(locator);
        return uiText.getAttribute("textContent").trim().equals(expectedText);
        //Assert.assertEquals(uiText.getAttribute("textContent").trim(), expectedText);
    }

    public boolean isValidationPresent() {
        return isElementPresent(By.className("validation-summary-errors"));
    }

    public boolean isSuccessMessageShown() {
        return isElementPresent(By.xpath("//p[contains(.,'The product has been added to your')]"));
    }

    public void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
