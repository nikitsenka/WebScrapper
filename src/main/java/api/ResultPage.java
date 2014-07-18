package api;

import org.openqa.selenium.WebDriver;
import selenium.InvalidWebDriverState;

public interface ResultPage extends Page {

    boolean hasNextPage();

    ResultPage getPageByPosNumber(Integer posNumber) throws InvalidWebDriverState;
    boolean isResultPage(WebDriver webDriver);
}
