package sites.linkedIn;

import api.Form;
import api.Page;
import api.Site;
import org.openqa.selenium.WebDriver;
import selenium.SeleniumForm;
import sites.linkedIn.model.Person;
import sites.linkedIn.parser.JsoupPersonParser;

import java.util.List;

public class LinkedIn implements Site {
    private final String username;
    private final String pass;
    private WebDriver webDriver;
    private static final String url = "http://www.linkedin.com/";
    private static final String homePageUrl = "https://www.linkedin.com/nhome/?trk=";

    public LinkedIn(WebDriver webDriver,String username, String pass) {
        this.webDriver = webDriver;
        this.username = username;
        this.pass = pass;
    }

    @Override
    public final boolean hasLoginPage() {
        return true;
    }
    @Override
    public final String getHomePageUrl(){
        return homePageUrl;
    }

    public final String getUrl() {
        return url;
    }

    public final Page logIn() {
        webDriver.get(url);
        Form loginForm = new LoginForm(webDriver,username,pass);
        return loginForm.submit();
    }

    public final Page mainSearch(String query){
        logIn();
        SeleniumForm globalSeleniumForm = new GlobalSearchForm(webDriver,query);
        return globalSeleniumForm.submit();
    }
    public final List<Person> searchPeople(String query){
        logIn();
        GlobalSearchForm globalSearchForm = new GlobalSearchForm(webDriver,query);
        globalSearchForm.setSearchType(GlobalSearchForm.SearchType.PEOPLE);
        Page resultPage = globalSearchForm.submit();
        return new JsoupPersonParser(resultPage).getPersonsDescription();
    }

}
