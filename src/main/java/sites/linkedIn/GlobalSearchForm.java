package sites.linkedIn;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.SeleniumForm;

import static selenium.Utils.getWebElement;

public class GlobalSearchForm extends SeleniumForm {
    enum SearchType{
        ALL("all"),
        PEOPLE("people"),
        JOBS("jobs"),
        COMPANIES("companies"),
        GROUPS("groups");
        private final String type;
        SearchType(String type) {
            this.type = type;
        }
        @Override
        public String toString() {
            return type;
        }
    }
    private final String searchInputLocator = "#main-search-box";
    public GlobalSearchForm(WebDriver webDriver,String searchStr) {
        super(webDriver,".search-button");
        setInput(searchInputLocator,searchStr);
    }

    public void setSearchType(SearchType type){
        WebElement webElement = getWebElement(webDriver, "#main-search-category > option."+type);
        webElement.click();
    }
}
