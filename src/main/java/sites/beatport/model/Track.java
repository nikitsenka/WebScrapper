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
    private final static String CHAR_FILTER = "[^\\x00-\\x7F]";
    private static final long serialVersionUID = 42L;

    public Track() {

    }

    public String getReleaseName() {
        return releaseName;
    }

    public void setReleaseName(String releaseName) {
        this.releaseName = releaseName;
    }

    public String getSecondArtist() {
        return secondArtist;
    }

    public void setSecondArtist(String secondArtist) {
        this.secondArtist = secondArtist;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public Track(String title, String artist){
        setTitle(title);
        setArtist(artist);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Track track = (Track) o;

        if (!artist.equals(track.artist)) {
            return false;
        }
        return title.equals(track.title);

    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + artist.hashCode();
        return result;
    }

    public String getTitle() {
        return title;

    }

    public void setTitle(String title) {
        this.title = preprocessInput(title);
        if (this.title == null) {
            throw new IllegalArgumentException("Invalid Title name");
        }
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) throws IllegalArgumentException {
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


    public Date getReleaseDate() {
        return releaseDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int compareTo(Object o) {
        return artist.compareTo(((Track) o).artist);  //To change body of implemented methods use File | Settings | File Templates.
    }

    public String getReleaseDateFormated() {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return releaseDate != null ? sdf.format(releaseDate):"";
    }

    public void setReleaseDate(String date_s) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        try {
            releaseDate = sdf.parse(date_s);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Can't parse date" + date_s + " using " + DATE_FORMAT + "format",e);
        }
    }

}
