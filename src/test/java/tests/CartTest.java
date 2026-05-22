package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CartTest extends BaseTest {

    @Test(description = "Проверка входа в систему с пустым полем пароля",
            testName = "Проверка входа в систему с пустым полем пароля",
            groups = {"regression"})
    public void checkLoginWithEmptyPassword() {
        loginPage.open()
                .isPageOpened()
                .login(user, password)
                .isPageOpened()
                .addToCart("Test.allTheThings() T-Shirt (Red)")
                .addToCart("Sauce Labs Bike Light")
                .clickCart();
    }
}
