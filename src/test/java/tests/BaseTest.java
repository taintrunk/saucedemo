package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;
import utils.PropertyReader;
import utils.TestListener;

import java.time.Duration;

@Listeners(TestListener.class)
public class BaseTest {
    public WebDriver driver;
    public WebDriverWait wait;
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;
    String user;
    String password;

    @Parameters({"browser"})
    @Step()
    @BeforeMethod()
    public void setup(@Optional("chrome") String browser, ITestContext context) {
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            // может быть уберет chrome change password alert
            options.addArguments("--disable-infobars"); // Отключить информационные уведомления
            options.addArguments("--disable-extensions"); // Отключить расширения
            options.addArguments("--disable-features=PasswordManager"); // отключить менеджер паролей
            options.addArguments("--disable-autofill-password-saving"); // отключить сохранение паролей
            options.addArguments("--guest");
            options.addArguments("start-maximized");
//        options.addArguments("headless");
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            EdgeOptions options = new EdgeOptions();
        }
        context.setAttribute("driver", driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);

        System.setProperty("BASE_URL", PropertyReader.getProperty("saucedemo.url"));
        user = PropertyReader.getProperty("saucedemo.user");
        password = PropertyReader.getProperty("saucedemo.password");
    }

      @Step("Закрытие")
      @AfterMethod
      public void close() {
          driver.quit();
      }
}