package sites.google;

import api.Page;
import api.ResultPage;
import org.openqa.selenium.WebDriver;
import selenium.SeleniumForm;

import static selenium.Utils.copyCurrentPage;

public class SearchForm extends SeleniumForm {
    private static final String searchInputLocator = "#lst-ib";

    public SearchForm(WebDriver webDriver, String searchStr) {
        super(webDriver,"btnK");
        setInput(searchInputLocator, searchStr);
    }
    public final ResultPage submitAndWait() {
        super.submit();
        new Wait(getWebDriver()).waitResultPageLoaded();
        Page page = copyCurrentPage(getWebDriver());
        return new ResultPageImpl(page, getWebDriver());
    }


}
