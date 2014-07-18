package selenium;

import api.Page;
import com.google.common.base.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;

public final class Utils {
    public static Page copyCurrentPage(WebDriver webDriver){
        String pageSource = webDriver.getPageSource();
        String currentUrl = webDriver.getCurrentUrl();
        String title = webDriver.getTitle();
        return new PageImpl(title, currentUrl, pageSource);
    }
    public static WebElement getWebElement(WebDriver webDriver, String inputLocator) {
        WebElement webElement;
        List<WebElement> elements = webDriver.findElements(By.id(inputLocator));
        if(elements.size() == 0){
            elements =  webDriver.findElements(By.cssSelector(inputLocator));
        }
        if(elements.size() == 0){
            elements = webDriver.findElements(By.name(inputLocator));
        }
        if(elements.size() == 0){
            elements = webDriver.findElements(By.xpath(inputLocator));
        }
        if(elements.size() != 0){
            webElement = elements.get(0);
        }else {
            throw new NoSuchElementException(String.format("Unable to locate element by %s",inputLocator));
        }
        return webElement;
    }

    public static void waitWhileResultPageLoaded(WebDriver webDriver) {
        new WebDriverWait(webDriver,10).until(new Function<WebDriver,Boolean>(){
            @Override
            public Boolean apply(WebDriver webDriver) {
                if (webDriver instanceof JavascriptExecutor) {
                    Object r = ((JavascriptExecutor) webDriver).executeScript("return document.readyState");
                    return "complete".equals(r.toString());
                }
                return false;
            }
        });

    }
}
