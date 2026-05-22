package tests;

import io.qameta.allure.*;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tests.base.BaseTest;
import utils.Retry;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test(priority = 1,
            description = "Проверка входа в систему с позитивными кредами",
            testName = "Проверка входа в систему с позитивными кредами",
            groups = {"smoke"},
            retryAnalyzer = Retry.class)
    @Description("Проверка входа в систему с позитивными кредами")
    @Epic("E2E")
    @Feature("Login in ti SauceDemo")
    @Story("Positive Login")
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://www.saucedemo.com/")
    @TmsLink("ITM-5")
    @Issue("ITM-5")
    @Flaky
    @Owner("Borodich Timofei")
    public void checkLoginWithPositiveCred() {
        loginPage.open();
        loginPage.isPageOpened();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(productsPage.getTitle(), "Products");
        productsPage.addToCart("Sauce Labs Backpack");
    }

    @Test(priority = 4, description = "Проверка входа в систему с пустым полем пароля",
            testName = "Проверка входа в систему с пустым полем пароля",
            groups = {"regression"})
    public void checkLoginWithEmptyPassword() {
        loginPage.open();
        loginPage.isPageOpened();
        loginPage.login(user, "");
        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Password is required");
    }

    @Test(priority = 3, groups = {"regression"})
    public void checkLoginWithEmptyUser() {
        loginPage.open();
        loginPage.isPageOpened();
        loginPage.login("", password);
        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username is required");
    }

    @Test(priority = 2, groups = {"regression"})
    public void checkLoginWithNegativeCred() {
        loginPage.open();
        loginPage.login("test", "test");
        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username and password do not match any " +
                "user in this service");
    }


    @DataProvider(name = "Тестовые данные для негативного логина", indices = {0, 2})
    public Object[][] loginData() {
        return new Object[][]{
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"},
                {"test", "test", "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Test(dataProvider = "Тестовые данные для негативного логина")
    public void negativeLogin(String user, String password, String errorMessage) {
        loginPage.open();
        loginPage.login(user, password);
        assertThat(loginPage.getErrorMessage()).isEqualTo(errorMessage);

        assertThat(5)
                .isEqualTo(5)
                .isGreaterThan(3)
                .isLessThan(10);

        assertThat("Hello Java")
                .isEqualTo("Hello Java")
                .contains("Java")
                .startsWith("He")
                .endsWith("va");


        SoftAssertions softAssertions = new SoftAssertions();
        List<String> names = List.of("Anna", "Lera", "Artem");
        softAssertions.assertThat(names)
                .hasSize(3)
                .contains("Anna")
                .containsExactly("Anna", "Lera", "Artem")
                .containsExactlyInAnyOrder("Anna", "Artem", "Lera")
                .doesNotContain("Dima");
        softAssertions.assertAll();


        assertEquals(loginPage.getErrorMessage(), errorMessage);
    }
}
