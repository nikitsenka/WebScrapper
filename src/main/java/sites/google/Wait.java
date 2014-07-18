package sites.google;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sites.google.parser.ResultPageCSSLocators;

public class Wait {
    private static final int timeOutInSeconds = 30;
    private WebDriver webDriver;

    public Wait(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    /**
     * Wait until result page loaded using page position number
     * @param pagePosNumber
     */
    public final void waitResultPageLoaded(Integer pagePosNumber) {
        new WebDriverWait(webDriver, timeOutInSeconds)
                .until(ExpectedConditions.textToBePresentInElementLocated(
                        By.cssSelector(".cur"), pagePosNumber.toString()));

    }

    /**
     * Wait until result page loaded using result list presence.
     */
    public final void waitResultPageLoaded() {
        new WebDriverWait(webDriver, timeOutInSeconds)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.cssSelector(ResultPageCSSLocators.WEB_LINK_LIST)));
    }
}
