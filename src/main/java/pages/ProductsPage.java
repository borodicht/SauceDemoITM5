package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductsPage extends BasePage {

    private final By TITLE = By.cssSelector("[data-test=title]");
    private final String ADD_TO_CART_PATTERN =
            "//*[text()='%s']/ancestor::div[@class='inventory_item']//button[text()='Add to cart']";

    public ProductsPage(WebDriver driver) {
        super(driver);
    }


    @Override
    public ProductsPage open() {
        driver.get(BASE_URL + "/inventory.html");
        return this;
    }

    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    @Override
    public ProductsPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        return this;
    }

    @Step("Добавление товра с именем '{product}' в корзину")
    public ProductsPage addToCart(String product) {
        driver.findElement(By.xpath(String.format(ADD_TO_CART_PATTERN, product))).click();
        return this;
    }

    public CartPage clickCart() {
        driver.findElement(By.cssSelector("[data-test='shopping-cart-link']")).click();
        return new CartPage(driver);
    }
}
