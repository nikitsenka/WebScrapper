package sites.beatport;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import sites.beatport.model.Track;

import java.util.Set;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.text.IsEmptyString.isEmptyString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
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
        Set<Track> result = beatPort.getTracksFromDeepHouseTop100(100);
        assertTrue(result.size() != 0);
        for (Track track : result) {
            assertThat(track,notNullValue());
            assertThat(track.getTitle(),not(isEmptyString()));
            assertThat(track.getArtist(),not(isEmptyString()));
        }
    }

}