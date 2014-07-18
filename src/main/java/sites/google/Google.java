package sites.google;

import api.ResultPage;
import api.Site;
import org.openqa.selenium.WebDriver;
import selenium.InvalidWebDriverState;
import sites.google.model.WebLink;
import sites.google.parser.JsoupResultParser;
import sites.google.parser.ResultParser;

import java.util.ArrayList;
import java.util.List;

public class Google implements Site {
    private String homePageUrl = "http://www.google.com";
    private WebDriver webDriver;
    private ResultParser jsoupResultParser = new JsoupResultParser();

    public Google(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Override
    public final boolean hasLoginPage() {
        return false;
    }

    @Override
    public final String getHomePageUrl() {
        return homePageUrl;
    }

    public final List<WebLink> search(String str, int numberOfPages) throws InvalidWebDriverState {
        List<WebLink> results = new ArrayList<>();
        webDriver.get(homePageUrl);
        ResultPage resultPage = new SearchForm(webDriver, str).submitAndWait();
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

    public final void addWebLinksIfExist(List<WebLink> results, ResultPage resultPage) {
        List<WebLink> webLinks = jsoupResultParser.getResults(resultPage);
        if(webLinks.size()!=0){
            results.addAll(webLinks);
        }
    }

}
