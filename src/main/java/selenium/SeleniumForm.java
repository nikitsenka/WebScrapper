package selenium;


import api.Form;
import api.InvalidFieldException;
import api.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.NoSuchElementException;

import static selenium.Utils.copyCurrentPage;
import static selenium.Utils.getWebElement;


public abstract class SeleniumForm implements Form {
    private WebDriver webDriver;
    private String submitButtonLocator;
    public SeleniumForm(WebDriver webDriver,String submitButtonLocator) {
        this.webDriver = webDriver;
        this.submitButtonLocator = submitButtonLocator;
    }

    @Override
    public final void setInput(String inputLocator, String value) {
        WebElement formInput = getWebElement(webDriver,inputLocator);
        formInput.clear();
        formInput.sendKeys(value);
    }


    @Override
    public final Page submit() {
        WebElement submitButton;
        try {
            submitButton = getWebElement(webDriver, submitButtonLocator);
        } catch (NoSuchElementException e) {
            throw new InvalidFieldException("Invalid submit button locator",e);
        }
        submitButton.submit();
        return copyCurrentPage(webDriver);
    }

    public final WebDriver getWebDriver() {
        return webDriver;
    }
}
