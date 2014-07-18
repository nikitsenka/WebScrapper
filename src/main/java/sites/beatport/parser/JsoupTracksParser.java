package sites.beatport.parser;

import api.Page;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sites.beatport.model.Track;

import java.text.ParseException;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

public class JsoupTracksParser implements TracksParser {

    @Override
    public final Set<Track> getTracks(Page top100Page, int size) throws ParseException {
        Set<Track> tracks = new LinkedHashSet<>();
        Document doc = Jsoup.parse(top100Page.getPageSource());
        Elements units = doc.getElementsByAttributeValueStarting("name", "tracks-grid-browse_track_");
        if (units != null) {
            if (size == 0) {
                size = units.size();
            }
            for (int i = 0; i < (size - 1); i++) {
                Element element = units.get(i);
                Track track = getTrackFromHTML(element);
                tracks.add(track);
            }
        }
        return tracks;
    }

    public final Track getTrackFromHTML(Element element)throws ParseException {
        Track track;
        String title = element.getElementsByClass("secondColumn").text();
        Element node = element.child(4);
        String artist = node.child(0).text();
        String secondArtist = "";
        if (node.children().size() > 1) {
            secondArtist = element.child(4).child(1).text();
        }

        String label = element.child(6).text().trim();
        String genre = element.child(7).text().trim();
        String date_s = element.child(8).text().trim();
        String pictureUrl = element.getElementsByClass("tile-image").get(0).attr("src").replaceFirst("24x24", "500x500");
        String releaseHref = element.getElementsByClass("tile-image-wrapper").get(0).child(0).attr("href");
        String releaseName = null;
        if (releaseHref != null) {
            releaseName = releaseHref.split("/")[2].replaceAll("-", " ");
        }
        track = new Track(title, artist);
        track.setSecondArtist(secondArtist);
        track.setPictureUrl(pictureUrl);
        track.setGenre(genre);
        track.setLabel(label);
        track.setLastModifiedDate(new Date());
        track.setReleaseDate(date_s);
        track.setReleaseName(releaseName);
        return track;
    }
}
