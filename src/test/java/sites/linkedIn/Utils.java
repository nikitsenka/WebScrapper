package sites.linkedIn;


import com.gargoylesoftware.htmlunit.BrowserVersion;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class Utils {

    public static WebDriver getWebDriver(){
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.link.open_newwindow.restriction", 0);
        //for open browser
        return new FirefoxDriver(profile);

    }


}
