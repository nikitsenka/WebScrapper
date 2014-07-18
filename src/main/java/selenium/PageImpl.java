package selenium;

public class PageImpl implements api.Page{
    private final String pageSource;
    private final String title;
    private final String url;

    public PageImpl(String title, String url, String pageSource) {
        this.title=title;
        this.url=url;
        this.pageSource=pageSource;
    }

    @Override
    public final String getTitle() {
        return title;
    }

    @Override
    public final String getUrl() {
        return url;
    }
    @Override
    public final String getPageSource() {
        return pageSource;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PageImpl)) return false;

        PageImpl pageImpl = (PageImpl) o;

        if (!title.equals(pageImpl.title)) return false;
        if (!url.equals(pageImpl.url)) return false;

        return true;
    }

    @Override
    public final int hashCode() {
        int result = title.hashCode();
        result = 31 * result + url.hashCode();
        return result;
    }
}