package pages;

import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public BasePage isPageOpened() {
        return null;
    }

    @Override
    public CartPage open() {
        driver.get(BASE_URL + "/cart.html");
        return this;
    }
}
