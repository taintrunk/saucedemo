package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import user.User;

public class LoginPage extends BasePage {

    private static final By USERNAME_INPUT = By.id("user-name");
    private static final By PASSWORD_INPUT = By.id("password");
    private static final By LOGIN_BUTTON = By.id("login-button");
    private static final By ERROR_MESSAGE_CONTAINER = By.xpath("//div[@class='error-message-container error']");
    private static final By ERROR_MESSAGE_TEXT = By.xpath("//div[@class='error-message-container error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие браузера")
    public void open() {
        driver.get(BASE_URL);
    }

    @Step("Проверка отображения контейнера сообщения")
    public boolean isErrorContainerDisplayed() {
        return driver.findElement(ERROR_MESSAGE_CONTAINER).isDisplayed();
    }

    @Step("Получаем текст ошибки для дальнейшей проверки")
    public String getRequiredErrorMessage() {
        return driver.findElement(ERROR_MESSAGE_TEXT).getText();
    }

    @Step("Заполнение поля user")
    public void fillUserInput (String user) {
        driver.findElement(USERNAME_INPUT).sendKeys(user);
    }

    @Step("Заполнение пароля")
    public void fillPasswordInput(String password) {
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
    }

    @Step("Нажимаем на кнопку Login")
    public void clickSubmitBtn() {
        driver.findElement(LOGIN_BUTTON).submit();
    }

    @Step("Вход пользователя")
    public void login(User user) {
        fillUserInput(user.getEmail());
        fillPasswordInput(user.getPassword());
        clickSubmitBtn();
    }
}
