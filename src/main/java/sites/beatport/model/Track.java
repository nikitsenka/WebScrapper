package sites.beatport.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Track implements Serializable, Comparable {
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    private String id;
    private String title;
    private String artist;
    private String secondArtist = "";
    @XmlElement
    private Date releaseDate;
    private String releaseName;
    @XmlElement
    private Date lastModifiedDate;
    private String pictureUrl;
    private String label;
    private String genre;
    private static final String CHAR_FILTER = "[^\\x00-\\x7F]";
    private static final long serialVersionUID = 42L;

    public Track() {

    }

    public final String getReleaseName() {
        return releaseName;
    }

    public final void setReleaseName(String releaseName) {
        this.releaseName = releaseName;
    }

    public final String getSecondArtist() {
        return secondArtist;
    }

    public final void setSecondArtist(String secondArtist) {
        this.secondArtist = secondArtist;
    }

    public final String getPictureUrl() {
        return pictureUrl;
    }

    public final void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public final String getLabel() {
        return label;
    }

    public final void setLabel(String label) {
        this.label = label;
    }

    public final String getGenre() {
        return genre;
    }

    public final void setGenre(String genre) {
        this.genre = genre;
    }

    public final void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = (Date) lastModifiedDate.clone();
    }

    public final Date getLastModifiedDate() {
        return (Date) lastModifiedDate.clone();
    }

    public Track(String title, String artist){
        setTitle(title);
        setArtist(artist);
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Track track = (Track) o;

        if (!artist.equals(track.artist)) {
            return false;
        }
        return title.equals(track.title);

    }

    @Override
    public final int hashCode() {
        int result = title.hashCode();
        result = 31 * result + artist.hashCode();
        return result;
    }

    public final String getTitle() {
        return title;

    }

    public final void setTitle(String title) {
        this.title = preprocessInput(title);
        if (this.title == null) {
            throw new IllegalArgumentException("Invalid Title name");
        }
    }

    public final String getArtist() {
        return artist;
    }

    public final void setArtist(String artist) throws IllegalArgumentException {
        this.artist = preprocessInput(artist);
        if (this.artist == null) {
            throw new IllegalArgumentException("Invalid Artist name");
        }
    }

    private String preprocessInput(String input) {
        String temp = input;
        if (temp != null) {
            temp = temp.trim();
            if (temp.length() > 1) {
                temp = temp.toLowerCase().replaceAll(CHAR_FILTER, "");
                return temp;
            }
        }
        return null;
    }


    public final Date getReleaseDate() {
        return (Date) releaseDate.clone();
    }

    public final String getId() {
        return id;
    }

    public final void setId(String id) {
        this.id = id;
    }

    @Override
    public final int compareTo(Object o) {
        return artist.compareTo(((Track) o).artist);  //To change body of implemented methods use File | Settings | File Templates.
    }

    public final String getReleaseDateFormated() {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return releaseDate != null ? sdf.format(releaseDate):"";
    }

    public final void setReleaseDate(String date_s) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        try {
            releaseDate = sdf.parse(date_s);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Can't parse date" + date_s + " using " + DATE_FORMAT + "format",e);
        }
    }

}
