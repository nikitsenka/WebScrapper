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
    private final String url = "http://www.linkedin.com/";
    private final String homePageUrl = "https://www.linkedin.com/nhome/?trk=";
    private boolean isLogged = false;

    public LinkedIn(WebDriver webDriver,String username, String pass) {
        this.webDriver = webDriver;
        this.username = username;
        this.pass = pass;
    }

    @Override
    public boolean hasLoginPage() {
        return true;
    }
    @Override
    public String getHomePageUrl(){
        return homePageUrl;
    }

    public String getUrl() {
        return url;
    }

    public Page logIn() {
        webDriver.get(url);
        Form loginForm = new LoginForm(webDriver,username,pass);
        Page page = loginForm.submit();
        isLogged = true;
        return page;
    }

    public Page mainSearch(String query){
        logIn();
        SeleniumForm globalSeleniumForm = new GlobalSearchForm(webDriver,query);
        return globalSeleniumForm.submit();
    }
    public List<Person> searchPeople(String query){
        logIn();
        GlobalSearchForm globalSearchForm = new GlobalSearchForm(webDriver,query);
        globalSearchForm.setSearchType(GlobalSearchForm.SearchType.PEOPLE);
        Page resultPage = globalSearchForm.submit();
        return new JsoupPersonParser().getPersonsDescription(resultPage);
    }

}
