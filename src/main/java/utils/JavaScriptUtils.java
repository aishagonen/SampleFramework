package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtils {
    public void flash(WebElement element, WebDriver driver) {
        String bgcolor = element.getCssValue("backgroundColor");
        for (int i = 0; i < 20; i++) {
            changeColor("rgb(0,200,0)", element, driver);// 1
            changeColor(bgcolor, element, driver);//
        }
    }

    public void changeColor(String color, WebElement element, WebDriver driver) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);

        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
        }
    }

    public void drawBorder(WebElement element, WebDriver driver) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].style.border='3px solid red'", element);
    }

    public void generateAlert(WebDriver driver, String message) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("alert('" + message + "')");
    }

    public void clickElementByJS(WebElement element, WebDriver driver) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].click();", element);

    }

    public void refreshBrowserByJS(WebDriver driver) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("history.go(0)");
    }

    public String getTitleByJS(WebDriver driver) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        String title = js.executeScript("return document.title;").toString();
        return title;
    }

    public String getPageInnerText(WebDriver driver) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        String pageText = js.executeScript("return document.documentElement.innerText;").toString();
        return pageText;
    }

    public void scrollPageDown(WebDriver driver) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
    }

    public void specificScrollPageDown(WebDriver driver) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("scroll(0, 750);");
    }

    public void scrollIntoView(WebElement element, WebDriver driver) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public String getBrowserInfo(WebDriver driver) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        String uAgent = js.executeScript("return navigator.userAgent;").toString();
        return uAgent;
    }

    public void sendKeysUsingJSWithID(WebDriver driver, String id, String value) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("document.getElementById('" + id + "').value='" + value + "'");
    }

    public void sendKeysUsingJSWithClassName(WebDriver driver, String className, String value) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("document.getElementsByClassName('" + className + "').value='" + value + "'");
    }

    public void sendKeysUsingJSWithName(WebDriver driver, String name, String value) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("document.getElementByName('" + name + "').value='" + value + "'");
    }
}
