package de.demoshop.fw;

import de.demoshop.core.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageHelper extends BaseHelper {


    public HomePageHelper(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public boolean isHomePageMainBlockPresent() {
        return isElementPresent(By.className("topic-html-content-header"));
    }

    public boolean isWishListEmpty() {
        return verifyByText(By.className("wishlist-qty"), "(0)");
    }

    public boolean isCartEmpty() {
        return verifyByText(By.className("cart-qty"), "(0)");
    }

    public void clickOnShoppingCartLinkAtTheHeader() {
        click(By.cssSelector("#topcartlink a"));
    }

    public void clickOnLogo() {
        click(By.className("header-logo"));
    }
}
