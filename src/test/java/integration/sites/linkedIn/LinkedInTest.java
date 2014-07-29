package integration.sites.linkedIn;

import api.Page;
import integration.sites.IntegrationTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebDriver;
import sites.linkedIn.LinkedIn;
import sites.linkedIn.model.Person;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;
import static integration.sites.linkedIn.Utils.getWebDriver;
@Category(IntegrationTest.class)
public class LinkedInTest {
    private LinkedIn linkedIn;

    @Before
    public void setUp() throws Exception {
        WebDriver driver = getWebDriver();
        linkedIn = new LinkedIn(driver,Fixture.USER_EMAIL, Fixture.USER_PASSWORD);
    }

    @Test
    public void logIn() throws Exception {
        String homePage = linkedIn.getHomePageUrl();
        Page page = linkedIn.logIn();
        String actual = page.getUrl();
        assertThat(homePage, is(actual));
    }
    @Test
    public void mainSearch(){
        Page page = linkedIn.mainSearch("test");
        String expect = "test";
        String actual = page.getUrl();
        assertThat(actual, is(containsString(expect)));
    }
    @Test
    public void searchPeople(){
        List<Person> results = linkedIn.searchPeople("ivan");
        assertThat(results.size(), is(10));
        Person actualPerson = results.get(0);
        assertThat(actualPerson.getName(), is(containsString("ivan")));
    }
    @Test
    public void searchPeopleWithNoResults(){
        List<Person> results = linkedIn.searchPeople("$@%@$%");
        assertThat(results.size(), is(0));
    }
}