package integration.sites.beatport;

import integration.sites.IntegrationTest;
import integration.sites.linkedIn.Utils;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebDriver;
import sites.beatport.BeatPort;
import sites.beatport.model.Track;

import java.util.Set;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.text.IsEmptyString.isEmptyString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
@Category(IntegrationTest.class)
public class BeatPortTest {

    private BeatPort beatPort;

    @Before
    public void setUp() throws Exception {
        WebDriver driver = Utils.getWebDriver();
        beatPort = new BeatPort(driver);
    }

    @Test
    public void testGetTracksFromDeepHouseTop100() throws Exception {
        Set<Track> result = beatPort.getTracksFromDeepHouseTop100(100);
        assertTrue(result.size() != 0);
        for (Track track : result) {
            assertThat(track,notNullValue());
            assertThat(track.getTitle(),not(isEmptyString()));
            assertThat(track.getArtist(),not(isEmptyString()));
        }
    }

}