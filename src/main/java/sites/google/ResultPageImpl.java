package sites.google;

import api.Page;
import api.ResultPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.InvalidWebDriverState;
import selenium.PageImpl;
import sites.google.parser.ResultPageCSSLocators;

import java.util.List;

import static selenium.Utils.copyCurrentPage;

public class ResultPageImpl extends PageImpl implements ResultPage{
    private final WebDriver webDriver;
    private static final String nextButtonSelector = "#pnnext";

    public ResultPageImpl(String title, String url, String pageSource,WebDriver webDriver) {
        super(title, url, pageSource);
        this.webDriver = webDriver;
    }

    public ResultPageImpl(Page page,WebDriver webDriver) {
        super(page.getTitle(),page.getUrl(),page.getPageSource());
        this.webDriver = webDriver;
    }
    @Override
    public final boolean hasNextPage() {
        List<WebElement> elements = webDriver.findElements(By.cssSelector(nextButtonSelector));
        return elements.size()!=0;
    }

    @Override
    public final ResultPage getPageByPosNumber(Integer posNumber) throws InvalidWebDriverState {
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
    public final boolean isResultPage(WebDriver webDriver) {
        List<WebElement> elements = webDriver.findElements(By.cssSelector(ResultPageCSSLocators.WEB_LINK_LIST));
        return elements.size()!=0;
    }

}
