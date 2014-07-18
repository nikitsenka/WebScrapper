package sites.beatport.parser;

import api.Page;
import sites.beatport.model.Track;

import java.text.ParseException;
import java.util.Set;

public interface TracksParser {
    Set<Track> getTracks(Page top100Page, int size) throws ParseException;
}
