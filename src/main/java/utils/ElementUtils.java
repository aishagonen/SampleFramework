package utils;


import base.BasePage;
import base.OptionsManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ElementUtils {

    public WebDriver driver;

    public Select select;

    JavaScriptUtils javaScriptUtils;

    Properties prop;
    BasePage basePage;
    WebDriverWait wait;
    WebElement element;

    private boolean highLightElement;

    public ElementUtils() {

        basePage = new BasePage();
//		driver=basePage.init_driver();
//		wait=new WebDriverWait(driver, AppConstants.DEFAULT_TIMEOUT);
        prop = basePage.init_properties();
        javaScriptUtils = new JavaScriptUtils();


    }

    public WebDriver launchBrowser(OptionsManager optionsManager) {
        optionsManager = new OptionsManager(prop);
        if (prop.getProperty("browser").equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver(optionsManager.getChromeOptions());
        } else if (prop.getProperty("browser").equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
        } else if (prop.getProperty("browser").equalsIgnoreCase("safari")) {
            driver = new SafariDriver();
        } else {
            System.out.println("A problem with the browser!!!");
        }
        deleteCookies(driver);
        maximize(driver);
        return driver;

    }

    public void deleteCookies(WebDriver driver) {
        driver.manage().deleteAllCookies();
    }

    public void launchURL(WebDriver driver, Properties prop) {
        try {
            driver.get(prop.getProperty("url"));
        } catch (Exception e) {

            System.out.println("A problem with URL!!!");
        }
    }

    public void maximize(WebDriver driver) {
        driver.manage().window().maximize();
    }


    public void handleAlertDismiss(WebDriver driver) {
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
        System.out.println("Alert is handled by dissmissing it!!!");
    }

    public void handleAlertAccept(WebDriver driver) {
        Alert alert = driver.switchTo().alert();
        try {
            alert.accept();
            System.out.println("Alert is handled by accepting it!!!");
        } catch (Exception e) {

            System.out.println("A problem with handling the alert");
        }
    }

    public String getAlertTextAndHandleAndVerify(WebDriver driver, String verifyText) {
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        System.out.println(text);
        if (text.equals(verifyText)) {
            System.out.println("Correct Text!");
        } else {
            System.out.println("Incorrect Text!");
        }

        alert.accept();
        return text;
    }

    public String getAlertTextAndHandle(WebDriver driver) {
        Alert alert = driver.switchTo().alert();
        String text = null;

        try {
            text = alert.getText();
            System.out.println(text);
            alert.accept();
        } catch (Exception e) {

            System.out.println("A problem with handling the alert");
        }
        return text;
    }

    public void sendAlertText(WebDriver driver, String key) {
        Alert alert = driver.switchTo().alert();
        try {
            alert.sendKeys(key);
        } catch (Exception e) {

            System.out.println("A problem with sending a key to the alert");
        }
    }

    /**
     * @param driver
     * @return
     */
    public String getPageTitle(WebDriver driver) {

        String title = null;
        try {
            title = driver.getTitle();
            System.out.println("Page title is: " + title);
        } catch (Exception e) {

            System.out.println("A problem with getting the page title");
        }

        return title;
    }

    public void clearText(By locator) {
        try {
            driver.findElement(locator).clear();
        } catch (Exception e) {
            System.out.println("A problem with clearing the text!!");
        }
    }
    /**
     * @param driver
     * @param url
     */

    /**
     * This method clicks on elements.
     *
     * @param driver
     * @param locator
     */
    public void clickOn(WebDriver driver, By locator) {
        try {
            //explicitWaitPresenceOfElement(driver, locator, AppConstants.DEFAULT_TIMEOUT);
            driver.findElement(locator).click();
        } catch (Exception e) {

            System.out.println("A problem with clicking the element!!");
        }
    }

    public void submit(WebDriver driver, By locator) {
        try {
            explicitWaitPresenceOfElement(driver, locator, AppConstants.DEFAULT_TIMEOUT);
            driver.findElement(locator).submit();
        } catch (Exception e) {

            System.out.println("A problem with submitting!!");
        }
    }

    /**
     * This method sends keys to elements.
     *
     * @param driver
     * @param locator
     * @param key
     */
    public void sendKeys(WebDriver driver, By locator, String key) {
        try {
            explicitWaitPresenceOfElement(driver, locator, AppConstants.DEFAULT_TIMEOUT);
            driver.findElement(locator).sendKeys(key);
        } catch (Exception e) {

            System.out.println("A problem with sending keys!!!");
        }

    }

    /**
     * This method gets the text of the elements, verifies and returns string.
     *
     * @param driver
     * @param locator
     * @param verifyText
     * @return
     */


    public String getTextOfElement(WebDriver driver, By locator) {

        String textOfElement = null;
        try {
            explicitWaitPresenceOfElement(driver, locator, AppConstants.DEFAULT_TIMEOUT);
            textOfElement = driver.findElement(locator).getText();
        } catch (Exception e) {

            System.out.println("A problem with getting text of the element!!");
        }


        return textOfElement;
    }

    /**
     * @param driver
     * @param locator
     * @return
     */
    public WebElement getElement(WebDriver driver, By locator) {
        WebElement element = null;

        try {
            element = driver.findElement(locator);
            if (highLightElement) {
                javaScriptUtils.flash(element, driver);
            }
        } catch (Exception e) {

            System.out.println("A problem with getting element");
        }

        return element;
    }

    /**
     * @param driver
     */
    public void closeBrowser(WebDriver driver) {
        try {
            driver.close();
        } catch (Exception e) {

            System.out.println("A problem with closing the browser!!");
        }
    }

    public void quitBrowser(WebDriver driver) {
        try {
            driver.quit();
        } catch (Exception e) {

            System.out.println("A problem with quitting the browser");
        }
    }

    /**
     * This method selects from drop-down by index number.
     *
     * @param element
     * @param index
     */
    public void selectByIndex(WebDriver driver, By locator, int index) {
        element = driver.findElement(locator);
        select = new Select(element);
        try {
            select.selectByIndex(index);
        } catch (Exception e) {

            System.out.println("A problem with selecting the element with INDEX!!!");
        }
    }

    /**
     * This method selects from drop-down by a visible text.
     *
     * @param element
     * @param text
     */
    public void selectByText(WebDriver driver, By locator, String text) {

        element = driver.findElement(locator);
        select = new Select(element);
        try {
            select.selectByVisibleText(text);
        } catch (Exception e) {

            System.out.println("A problem with selecting the element with TEXT!!!");

        }
    }

    public void isMultiple(Select select) {

        if (select.isMultiple() == false) {
            System.out.println("No, it is not Multiple Select Menu");
        } else {
            System.out.println("Yes, it is Multiple Select Menu");
        }

    }

    public String getSelectedOptionText(WebDriver driver, String verifyText) {

        String selectedOption = null;

        try {
            selectedOption = select.getFirstSelectedOption().getText();

            System.out.println(selectedOption + " is selected!!");
        } catch (Exception e) {

            System.out.println("A problem with selected option!!");
        }

        return selectedOption;
    }

    public void printDropDownOptions(List<WebElement> list, WebElement element) {
        select = new Select(element);
        list = select.getOptions();

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getText());
        }

    }

    public void implicitWait(WebDriver driver, long second) {
        driver.manage().timeouts().implicitlyWait(second, TimeUnit.SECONDS);
    }

    public void explicitWaitPresenceOfElement(WebDriver driver, By locator, long second) {
        WebDriverWait wait = new WebDriverWait(driver, second);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void explicitWaitTextToBePresentInElement(WebDriver driver, By locator, long second, String textOfElement) {

        WebDriverWait wait = new WebDriverWait(driver, second);
        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(locator), textOfElement));
    }

//	public void fluentWait(WebDriver driver, final By locator, long totalSecond, long interval,
//			final String verifyText) {
//
//		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(totalSecond))
//				.pollingEvery(Duration.ofSeconds(interval)).ignoring(NoSuchMethodException.class);
//
//		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
//
//			public WebElement apply(WebDriver driver) {
//				WebElement element = driver.findElement(locator);
//				String text = element.getText();
//				if (text.equals(verifyText)) {
//					System.out.println(text);
//					System.out.println("Correct Text!!");
//					return element;
//				} else {
//					return null;
//				}
//			}
//		});
//	}

//	public void screenShot(WebDriver driver, String fileName) throws IOException{
//		String path = "/users/firatmeydan/Desktop/pic";
//		String file = fileName;
//		driver.manage().window().maximize();
//		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); 
//		//FileUtils.copyFile(src, new File( path +"/" + file +".png"));
//	}

}
