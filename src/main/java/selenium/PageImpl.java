package selenium;

import org.openqa.selenium.WebDriver;

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
    public String getTitle() {
        return title;
    }

    @Override
    public String getUrl() {
        return url;
    }
    @Override
    public String getPageSource() {
        return pageSource;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PageImpl)) return false;

        PageImpl pageImpl = (PageImpl) o;

        if (!title.equals(pageImpl.title)) return false;
        if (!url.equals(pageImpl.url)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + url.hashCode();
        return result;
    }
}