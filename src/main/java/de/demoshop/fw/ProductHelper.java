package de.demoshop.fw;

import de.demoshop.core.BaseHelper;
import de.demoshop.model.Desktop;
import de.demoshop.model.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductHelper extends BaseHelper {


    public ProductHelper(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void clickOnAddToCart(Product<?> product) {
        click(By.cssSelector(
                String.format("[data-productid='%s'] input[value='Add to cart']",
                        product.getDataProductid())
        ));
    }

    public boolean isProductAddedToTheCartMessagePresent() {
        return isElementPresent(By.xpath("//p[contains(.,'The product has been added to your')]"));
    }

    public void waitBeforeSuccessMessageDisappear() {
        waitForNotificationToDisappear(By.id("bar-notification"));
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
        return sizeOfList(By.className("cart-item-row"));
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
        click(By.cssSelector(String.format("[data-productid='%d'] a", product.getDataProductid())));
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

    public void fillQty(Product<?> product, Integer qty) {
        type(By.id(String.format("addtocart_%d_EnteredQuantity", product.getDataProductid())), qty.toString());
    }

    public boolean isQtyPresentInTheCart(Integer qty) {
        return isElementPresent(By.cssSelector(String.format("input[value='%d']", qty)));
    }

    public void changeQtyInTheCart(Integer qty) {
        typeAndSubmit(By.className("qty-input"), qty.toString());
    }

    public boolean isSubTotalCalculatedCorrect() {
        WebElement priceElement = driver.findElement(By.className("product-unit-price"));
        Double price = Double.parseDouble(priceElement.getText().trim());
        WebElement qtyElement = driver.findElement(By.className("qty-input"));
        Double qty = Double.parseDouble(qtyElement.getAttribute("value"));
        WebElement subTotalElement = driver.findElement(By.className("product-subtotal"));
        Double subTotal = Double.parseDouble(subTotalElement.getAttribute("textContent").trim());
        return subTotal == price * qty;
    }
}
