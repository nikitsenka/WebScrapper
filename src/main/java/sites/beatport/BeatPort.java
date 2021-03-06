package sites.beatport;


import api.Page;
import api.Site;
import org.openqa.selenium.WebDriver;
import selenium.Utils;
import sites.beatport.model.Track;
import sites.beatport.parser.JsoupTracksParser;

import java.text.ParseException;
import java.util.Set;

public class BeatPort implements Site {
    private String homePageUrl = "www.beatport.com";
    private WebDriver webDriver;

    public BeatPort(WebDriver webDriver) {
        this.webDriver = webDriver;

    }

    public final Set<Track> getTracksFromDeepHouseTop100(int size) throws ParseException {
        webDriver.get("http://www.beatport.com/genre/deep-house/12/top-100");
        Page page = Utils.copyCurrentPage(webDriver);
        return new JsoupTracksParser().getTracks(page, size);
    }

    @Override
    public final boolean hasLoginPage() {
        return false;
    }

    @Override
    public final String getHomePageUrl() {
        return homePageUrl;
    }
}
