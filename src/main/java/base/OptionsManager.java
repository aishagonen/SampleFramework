package base;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Properties;

public class OptionsManager {

    ChromeOptions co;

    FirefoxOptions fo;

    Properties prop;

    public OptionsManager(Properties prop) {
        this.prop = prop;
        co = new ChromeOptions();
        fo = new FirefoxOptions();
    }

    public ChromeOptions getChromeOptions() {

        if (prop.getProperty("headless").equals("yes")) {
            co.addArguments("--headless");
        }

        if (prop.getProperty("private").equals("yes")) {
            co.addArguments("--incognito");
        }
        return co;
    }

    public FirefoxOptions getFirefoxOptions() {
        if (prop.getProperty("headless").equals("yes")) {
            fo.addArguments("--headless");
        }

        if (prop.getProperty("private").equals("yes")) {
            fo.addArguments("-private");
        }
        return fo;
    }


}
