package sites.google.model;

public class WebLink {
    private String name;
    private String url;
    private String description;

    public final String getName() {
        return name;
    }

    public final void setName(String name) {
        this.name = name;
    }

    public final String getUrl() {
        return url;
    }

    public final void setUrl(String url) {
        this.url = url;
    }

    public final String getDescription() {
        return description;
    }

    public final void setDescription(String description) {
        this.description = description;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WebLink)) return false;

        WebLink webLink = (WebLink) o;

        if (description != null ? !description.equals(webLink.description) : webLink.description != null) return false;
        if (name != null ? !name.equals(webLink.name) : webLink.name != null) return false;
        if (url != null ? !url.equals(webLink.url) : webLink.url != null) return false;

        return true;
    }

    @Override
    public final int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public final String toString() {
        final StringBuffer sb = new StringBuffer("WebLink{");
        sb.append("name='").append(name).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
