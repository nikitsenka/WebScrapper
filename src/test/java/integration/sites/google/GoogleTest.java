package integration.sites.google;

import integration.sites.IntegrationTest;
import integration.sites.linkedIn.Utils;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebDriver;
import selenium.InvalidWebDriverState;
import sites.google.Google;
import sites.google.model.WebLink;

import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.text.IsEmptyString.isEmptyString;
import static org.junit.Assert.assertThat;
@Category(IntegrationTest.class)
public class GoogleTest {

    private Google google;

    @Before
    public void setUp() throws Exception {
        WebDriver driver = Utils.getWebDriver();
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