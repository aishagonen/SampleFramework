package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Sample {
    public static void main(String[] args) {
        BasePage basePage=new BasePage();
        WebDriver driver = basePage.init_driver();
        driver.get("https://www.google.com");
    }

}
