package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import user.UserFactory;
import utils.AllureUtils;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static utils.AllureUtils.takeScreenshot;

public class LoginTest extends BaseTest {

    @Test(enabled = true)
    public void correctLogin() {
        loginPage.open();
        loginPage.login(UserFactory.withAdminPermission());
//        takeScreenshot(driver);
        assertTrue(productsPage.isProductsTitleDisplayed(), "Products title is not displayed");
    }

    @DataProvider
    public Object[][] loginData() {
        return new Object[][] {
                {"locked_out_user", "secret-sauce", "Epic sadface: Username and password do not match any user in this service"},
                {"", "secret-sauce", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"}
        };
    }

    @Test(dataProvider = "loginData")
    public void incorrectLogin(String errorMsg) {
        loginPage.open();
        loginPage.login(UserFactory.withAdminPermission());
        assertEquals(loginPage.getRequiredErrorMessage(), errorMsg);
    }
}
