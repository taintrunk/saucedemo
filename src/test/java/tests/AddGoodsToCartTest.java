package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import user.UserFactory;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AddGoodsToCartTest extends BaseTest {

    @DataProvider
    public Object[][] productsNames() {
        return new Object[][]{
                { new String[]{"Sauce Labs Bike Light", "Sauce Labs Fleece Jacket"} },
                { new String[]{"Sauce Labs Bolt T-Shirt", "Sauce Labs Onesie"} }
        };
    }

    @Epic("Модуль логина интернет магазина")
    @Feature("Юр лица")
    @Story("STG")
    @Severity(SeverityLevel.MINOR)
    @Step("Проверка отображения двух товаров на иконке корзины")
    @Owner("Kondyshev Lev levisniy@gmail.com")
    @TmsLink("saucedemo")
    @Issue("example")
    @Test(dataProvider = "productsNames", description = "Проверяем отображение количества товаров на иконке корзины")
    public void numberOnCartIconCheck(String[] productNames) {
        loginPage.open();
        loginPage.login(UserFactory.withAdminPermission());
        assertTrue(productsPage.isProductsTitleDisplayed(), "Products title is not displayed");

        for (String productName : productNames) {
            productsPage.addToCart(productName);
        }

        assertEquals(productsPage.numberOnCartIcon(), productNames.length, "Should be 2");
    }
}
