package de.demoshop.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class TestBase {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.get("https://demowebshop.tricentis.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod(enabled = false)
    public void tearDown() {
        if(driver != null) driver.quit();
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

    public boolean verifyUserEmailInHeader(String email) {
        return verifyByText(By.cssSelector(".header-links .account"), email);
    }

    public boolean isLogoutLinkPresent() {
        return isElementPresent(By.cssSelector("[href='/logout']"));
    }

    public void clickOnRegisterButton() {
        click(By.id("register-button"));
    }

    public String fillRegisterFormWithGeneratedEmail(User user) {
        user.setEmail(generateEmail(user.getName(), user.getLastName()));
        fillRegisterForm(user);
        return user.getEmail();
    }

    public void fillRegisterForm(User user) {
        type(By.id("FirstName"), user.getName());
        type(By.id("LastName"), user.getLastName());
        type(By.id("Email"), user.getEmail());
        type(By.id("Password"), user.getPassword());
        type(By.id("ConfirmPassword"), user.getPassword());
    }

    private String generateEmail(String name, String lastName) {
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
        return name + i + "@" + lastName + ".com";
    }

    public void clickOnRegisterLink() {
        click(By.cssSelector("[href='/register']"));
    }

    public void openRegisterPageFromSignInPage() {
        click(By.cssSelector("[href='/login']"));
        click(By.className("register-button"));
    }

    public boolean isEmailAlreadyExists() {
        return verifyByText(By.className("validation-summary-errors"), "The specified email already exists");
    }

    public boolean isValidationPresent() {
        return isElementPresent(By.className("validation-summary-errors"));
    }

    public void clickOnLoginInButton() {
        click(By.cssSelector("[value='Log in']"));
    }

    public void fillInLoginForm(User user) {
        type(By.id("Email"), user.getEmail());
        type(By.id("Password"), user.getPassword());
    }

    public void clickOnLoginLink() {
        click(By.cssSelector("[href='/login']"));
    }

    public boolean isWishListEmpty() {
        return verifyByText(By.className("wishlist-qty"), "(0)");
    }

    public boolean isCartEmpty() {
        return verifyByText(By.className("cart-qty"), "(0)");
    }

    public boolean isRegisterLinkPresent() {
        return isElementPresent(By.cssSelector("[href='/register']"));
    }

    public boolean isLoginLinkPresent() {
        return isElementPresent(By.cssSelector("[href='/login']"));
    }

    public void clickOnLogoutLink() {
        click(By.cssSelector("[href='/logout']"));
    }

    public boolean isErrorNoCustomerFoundPresent() {
        return verifyByText(By.className("validation-summary-errors"), "Login was unsuccessful. Please correct the errors and try again." +
                "\nNo customer account found");
    }

    public boolean isErrorCredentialsAreIncorectPresent() {
        return verifyByText(By.className("validation-summary-errors"), "Login was unsuccessful. Please correct the errors and try again." +
                "\nThe credentials provided are incorrect");
    }

    public void clickOnShoppingCartLinkAtTheHeader() {
        click(By.cssSelector("#topcartlink a"));
    }

    public void clickOnAddToCart() {
        click(By.cssSelector("[data-productid='31'] input[value='Add to cart']"));
    }

    public boolean isItemPresentInTheCart() {
        return isElementPresent(By.xpath("//td/a[.='14.1-inch Laptop']"));
    }

    public boolean isSuccessMessageShown() {
        return isElementPresent(By.xpath("//p[contains(.,'The product has been added to your')]"));
    }

    public void removeProductFromCart() {
        click(By.name("removefromcart"));
        click(By.name("updatecart"));
    }

    public int sizeOfCartList() {
        return driver.findElements(By.className("cart-item-row")).size();
    }

    public void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isProductShownInRecentlyViewedBlock() {
        return isElementPresent(By.cssSelector(".product-picture a[href='/141-inch-laptop']"));
    }

    public boolean isRecentlyViewedBlockPresent() {
        return isElementPresent(By.xpath("//strong[.='Recently viewed products']"));
    }

    public void clickOnLogo() {
        click(By.className("header-logo"));
    }

    public void clickOnProduct() {
        click(By.cssSelector("[data-productid='31'] a"));
    }
}
//jack@sparrow.com
//Password1!