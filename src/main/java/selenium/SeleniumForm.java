package selenium;


import api.Form;
import api.InvalidFieldException;
import api.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import java.util.NoSuchElementException;

import static selenium.Utils.copyCurrentPage;
import static selenium.Utils.getWebElement;
import static selenium.Utils.waitWhileResultPageLoaded;


public abstract class SeleniumForm implements Form {
    protected WebDriver webDriver;
    protected String submitButtonLocator;
    public SeleniumForm(WebDriver webDriver,String submitButtonLocator) {
        this.webDriver = webDriver;
        this.submitButtonLocator = submitButtonLocator;
    }

    @Override
    public void setInput(String inputLocator, String value) {
        WebElement formInput = getWebElement(webDriver,inputLocator);
        formInput.clear();
        formInput.sendKeys(value);
    }


    @Override
    public Page submit() {
        WebElement submitButton = null;
        try {
            submitButton = getWebElement(webDriver, submitButtonLocator);
        } catch (NoSuchElementException e) {
            throw new InvalidFieldException("Invalid submit button locator",e);
        }
        submitButton.submit();
        return copyCurrentPage(webDriver);
    }
}
