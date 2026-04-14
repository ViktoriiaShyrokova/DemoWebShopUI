package de.demoshop.test;

import de.demoshop.test.entity.Desktop;
import de.demoshop.test.entity.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class ApplicationManager {
    WebDriver driver;

    public void init() {
        driver = new ChromeDriver();
        driver.get("https://demowebshop.tricentis.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public void stop() {
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

    public boolean isErrorCredentialsAreIncorrectPresent() {
        return verifyByText(By.className("validation-summary-errors"), "Login was unsuccessful. Please correct the errors and try again." +
                "\nThe credentials provided are incorrect");
    }

    public void clickOnShoppingCartLinkAtTheHeader() {
        click(By.cssSelector("#topcartlink a"));
    }

    public void clickOnAddToCart(Product<?> product) {
        click(By.cssSelector(
                String.format("[data-productid='%s'] input[value='Add to cart']",
                        product.getDataProductid())
        ));
    }

    public boolean isItemPresentInTheCart(Product<?> product) {
        return isElementPresent(By.xpath(String.format("//td/a[.='%s']",
                        product.getTitle())));
    }

    public boolean isItemDetailsPresentInTheCart(Desktop desktop) {

        String xpath = "//tr//div[contains(@class,'attributes')";

        xpath += String.format(" and contains(., '%s')", desktop.getProcessor());
        xpath += String.format(" and contains(., '%s')", desktop.getRam());
        xpath += String.format(" and contains(., '%s')", desktop.getHdd());

        if (desktop.getSoftware() != null) {
            xpath += String.format(" and contains(., '%s')", desktop.getSoftware());
        }

        xpath += "]";

        return isElementPresent(By.xpath(xpath));
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

    public boolean isOutOfStockMessagePresent() {
        return isElementPresent(By.xpath("//p[.='Out of stock']"));
    }

    public void clickOnApparelShoesInTopMenu() {
        click(By.cssSelector(".top-menu a[href='/apparel-shoes']"));
    }
}
