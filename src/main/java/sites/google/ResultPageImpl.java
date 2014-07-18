package sites.google;

import api.Page;
import api.ResultPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.InvalidWebDriverState;
import selenium.PageImpl;
import sites.google.parser.ResultPageCSSLocators;

import java.util.List;

import static selenium.Utils.copyCurrentPage;

public class ResultPageImpl extends PageImpl implements ResultPage{
    private final WebDriver webDriver;
    private final String nextButtonSelector = "#pnnext";

    public ResultPageImpl(String title, String url, String pageSource,WebDriver webDriver) {
        super(title, url, pageSource);
        this.webDriver = webDriver;
    }

    public ResultPageImpl(Page page,WebDriver webDriver) {
        super(page.getTitle(),page.getUrl(),page.getPageSource());
        this.webDriver = webDriver;
    }
    @Override
    public boolean hasNextPage() {
        List<WebElement> elements = webDriver.findElements(By.cssSelector(nextButtonSelector));
        if (elements.size()!=0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public ResultPage getPageByPosNumber(Integer posNumber) throws InvalidWebDriverState {
        if(isResultPage(webDriver)){
            List<WebElement> elements = webDriver.findElements(By.linkText(posNumber.toString()));
            if(elements.size()!=0){
                elements.get(0).click();
                new Wait(webDriver).waitResultPageLoaded(posNumber);
                Page page = copyCurrentPage(webDriver);
                return new ResultPageImpl(page, webDriver);
            }else{
                throw new InvalidWebDriverState(String.format("The result page with number %s doesn't exist",posNumber));
            }
        }else{
            throw new InvalidWebDriverState("The page is not a Google result page. Url: "+webDriver.getCurrentUrl());
        }
    }
    @Override
    public boolean isResultPage(WebDriver webDriver) {
        List<WebElement> elements = webDriver.findElements(By.cssSelector(ResultPageCSSLocators.WEB_LINK_LIST));
        if(elements.size()!=0){
            return true;
        }else{
            return false;
        }
    }

}
