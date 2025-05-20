package utils;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("➡️ Тест начался: " + iTestResult.getName());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("✅ Тест успешен: " + iTestResult.getName());
        WebDriver driver = (WebDriver) iTestResult.getTestContext().getAttribute("driver");
        takeScreenshot(driver);
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("❌ Тест провален: " + iTestResult.getName());
        WebDriver driver = (WebDriver) iTestResult.getTestContext().getAttribute("driver");
        takeScreenshot(driver);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("⚠️ Тест пропущен: " + iTestResult.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("📊 Тест частично провален: " + iTestResult.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("🏁 Начало тестового набора: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("🔚 Окончание тестового набора: " + context.getName());
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] takeScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}