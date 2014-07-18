package sites.linkedIn;

import org.openqa.selenium.WebDriver;
import selenium.SeleniumForm;

public class LoginForm extends SeleniumForm {
    private static final String userNameLocator = "session_key-login";
    private static final String passLocator = "session_password-login";

    public LoginForm(WebDriver webDriver, String username,String pass) {
        super(webDriver,"signin");
        setInput(userNameLocator,username);
        setInput(passLocator, pass);
    }

}
