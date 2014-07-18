package sites.beatport;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import sites.beatport.model.Track;
import sites.google.model.WebLink;

import java.util.Set;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.text.IsEmptyString.isEmptyString;
import static org.junit.Assert.*;
import static sites.linkedIn.Utils.getWebDriver;

public class BeatPortTest {

    private BeatPort beatPort;

    @Before
    public void setUp() throws Exception {
        WebDriver driver = getWebDriver();
        beatPort = new BeatPort(driver);
    }

    @Test
    public void testGetTracksFromDeepHouseTop100() throws Exception {
        Set<Track> result = beatPort.getTracksFromDeepHouseTop100();
        assertTrue(result.size() != 0);
        for (Track track : result) {
            assertThat(track,notNullValue());
            assertThat(track.getTitle(),not(isEmptyString()));
            assertThat(track.getArtist(),not(isEmptyString()));
        }
    }

}