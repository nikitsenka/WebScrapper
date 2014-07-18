package sites.google;

import api.Page;
import api.ResultPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.SeleniumForm;
import sites.google.parser.ResultPageCSSLocators;

import static selenium.Utils.copyCurrentPage;
import static selenium.Utils.waitWhileResultPageLoaded;

public class SearchForm extends SeleniumForm {
    private final String searchInputLocator = "#lst-ib";

    public SearchForm(WebDriver webDriver, String searchStr) {
        super(webDriver,"btnK");
        setInput(searchInputLocator, searchStr);
    }
    @Override
    public ResultPage submit() {
        super.submit();
        new Wait(webDriver).waitResultPageLoaded();
        Page page = copyCurrentPage(webDriver);
        return new ResultPageImpl(page, webDriver);
    }


}
