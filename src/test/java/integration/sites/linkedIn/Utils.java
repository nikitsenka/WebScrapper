package integration.sites.linkedIn;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public final class Utils {

    public static WebDriver getWebDriver(){
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.link.open_newwindow.restriction", 0);
        //for open browser
        return new FirefoxDriver(profile);

    }


}
