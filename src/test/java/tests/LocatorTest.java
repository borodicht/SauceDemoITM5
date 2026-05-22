package tests;

import org.testng.annotations.Test;
import tests.base.BaseTest;

import static org.testng.Assert.assertEquals;

public class LocatorTest extends BaseTest {

    @Test(groups = {"regression"})
    public void checkLoginWithEmptyUser() {
        loginPage.open();
        loginPage.isPageOpened();
        loginPage.login("", "secret_sauce");
        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username is required");
    }
}
