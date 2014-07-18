package sites.google;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import selenium.InvalidWebDriverState;
import sites.beatport.model.Track;
import sites.google.model.WebLink;
import sites.linkedIn.Utils;

import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import org.hamcrest.text.*;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;
import static sites.linkedIn.Utils.getWebDriver;
import static org.hamcrest.text.IsEmptyString.isEmptyString;

public class GoogleTest {

    private Google google;

    @Before
    public void setUp() throws Exception {
        WebDriver driver = getWebDriver();
        google = new Google(driver);
    }

    @Test
    public void search() throws InvalidWebDriverState {
        List<WebLink> results = google.search("test",10);
        assertThat(results.size(), is(100));
        for (WebLink webLink : results) {
            assertThat(webLink,notNullValue());
            assertThat(webLink.getName(),not(isEmptyString()));
            assertThat(webLink.getDescription(),not(isEmptyString()));
        }
    }

}