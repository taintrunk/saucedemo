package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductsPage extends BasePage {

    private final By PRODUCTS_PAGE_TITLE = By.xpath("//span[text()='Products']");
    private final String ADD_TO_CART_BUTTON_PATTERN = "//div[text()='%s']//ancestor::div[@class='inventory_item_description']//child::button[text()='Add to cart']";
    private final By GO_TO_CART_ICON = By.xpath("//a[@class='shopping_cart_link']");
    private final By NUMBER_ON_CART_ICON = By.xpath("//span[@class='shopping_cart_badge']");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Проверка отображения заголовка")
    public boolean isProductsTitleDisplayed() {
        return driver.findElement(PRODUCTS_PAGE_TITLE).isDisplayed();
    }

    @Step("Добавление в корзину")
    public void addToCart(String goodsName) {
        By addToCart = By.xpath(String.format(ADD_TO_CART_BUTTON_PATTERN, goodsName));
        driver.findElement(addToCart).click();
    }

    public void clickOnCartIcon() {
        driver.findElement(GO_TO_CART_ICON).click();
    }

    @Step("Проверка количества на иконке корзины")
    public int numberOnCartIcon() {
        return Integer.parseInt(wait.until(ExpectedConditions.visibilityOfElementLocated(NUMBER_ON_CART_ICON)).getText());
    }

}
