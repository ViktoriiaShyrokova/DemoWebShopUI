package de.demoshop.fw;

import de.demoshop.core.BaseHelper;
import de.demoshop.model.Desktop;
import de.demoshop.model.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductHelper extends BaseHelper {

    public ProductHelper(WebDriver driver) {
        super(driver);
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

    public void removeProductFromCart() {
        click(By.name("removefromcart"));
        click(By.name("updatecart"));
    }

    public int sizeOfCartList() {
        return driver.findElements(By.className("cart-item-row")).size();
    }

    public boolean isProductShownInRecentlyViewedBlock(Product<?> product) {
        return isElementPresent(By.xpath(String.format("//div[@class='listbox']//a[.='%s']",
                        product.getTitle())
        ));
    }

    public boolean isRecentlyViewedBlockPresent() {
        return isElementPresent(By.xpath("//strong[.='Recently viewed products']"));
    }

    public void clickOnProduct(Product<?> product) {
        click(By.cssSelector(String.format("[data-productid='%d'] a",product.getDataProductid())));
    }

    public void clickOnAddToCartButtonOnProductDetailsPage(Product<?> product) {
        click(By.id(String.format("add-to-cart-button-%d", product.getDataProductid())));
    }

    public void clickOnProductAttributes(Desktop desktop) {
        desktop.getValueAttributes().forEach(a -> click(By.id(a)));
    }

    public boolean isOutOfStockMessagePresent() {
        return isElementPresent(By.xpath("//p[.='Out of stock']"));
    }

    public void clickOnApparelShoesInTopMenu() {
        click(By.cssSelector(".top-menu a[href='/apparel-shoes']"));
    }
}
