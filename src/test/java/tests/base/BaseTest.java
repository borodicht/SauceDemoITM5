package tests.base;

import io.qameta.allure.Description;
import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.ProductsPage;
import utils.TestListener;

import java.util.HashMap;

@Listeners({AllureTestNg.class, TestListener.class})
public class BaseTest {

    protected LoginPage loginPage;
    protected ProductsPage productsPage;
    protected WebDriver driver;


    @Parameters({"browser"})
    @BeforeMethod (alwaysRun = true, description = "Настройка браузера")
    @Description("Настройка браузера")
    public void setUp(@Optional("chrome") String browser, ITestContext iTestContext) {
        System.out.println(System.getProperty("blablabla"));

        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            HashMap<String, Object> chromePrefs = new HashMap<>();
            chromePrefs.put("credentials_enable_service", false);
            chromePrefs.put("profile.password_manager_enabled", false);
            options.setExperimentalOption("prefs", chromePrefs);
            options.addArguments("--incognito");
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--disable-infobars");
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
        }

        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);

        iTestContext.setAttribute("driver", driver);
    }

    @AfterMethod(alwaysRun = true, description = "Закрытие браузера")
    @Description("Закрытие браузера")
    public void tearDown() {
        driver.quit();
    }
}
