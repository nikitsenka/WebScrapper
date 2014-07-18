package sites.google;

import api.ResultPage;
import api.Site;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.InvalidWebDriverState;
import selenium.SeleniumForm;
import sites.google.model.WebLink;
import sites.google.parser.JsoupResultParser;
import sites.google.parser.ResultPageCSSLocators;
import sites.google.parser.ResultParser;

import java.util.ArrayList;
import java.util.List;

public class Google implements Site {
    private String homePageUrl = "http://www.google.com";
    protected WebDriver webDriver;
    ResultParser jsoupResultParser = new JsoupResultParser();

    public Google(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Override
    public boolean hasLoginPage() {
        return false;
    }

    @Override
    public String getHomePageUrl() {
        return homePageUrl;
    }

    public List<WebLink> search(String str, int numberOfPages) throws InvalidWebDriverState {
        List<WebLink> results = new ArrayList<>();
        webDriver.get(homePageUrl);
        ResultPage resultPage = new SearchForm(webDriver, str).submit();
        addWebLinksIfExist(results, resultPage);
        for(int i = 2; i<=numberOfPages;i++){
            if(resultPage.hasNextPage()){
                resultPage = resultPage.getPageByPosNumber(i);
                addWebLinksIfExist(results, resultPage);
            }else{
                break;
            }
        }
        return results;
    }

    public void addWebLinksIfExist(List<WebLink> results, ResultPage resultPage) {
        List<WebLink> webLinks = jsoupResultParser.getResults(resultPage);
        if(webLinks.size()!=0){
            results.addAll(webLinks);
        }
    }

}
