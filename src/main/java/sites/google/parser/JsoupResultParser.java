package sites.google.parser;

import api.Page;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sites.google.model.WebLink;

import java.util.ArrayList;
import java.util.List;

public class JsoupResultParser implements ResultParser{

    @Override
    public List<WebLink> getResults(Page searchResultPage) {
        Document doc = Jsoup.parse(searchResultPage.getPageSource());
        List<WebLink> webLinks = new ArrayList<>();
        Elements elements = doc.select(ResultPageCSSLocators.WEB_LINK_LIST);
        if(elements.size() != 0 ){
            for(Element element: elements){
                Element title = element.select(ResultPageCSSLocators.WEB_LINK_TITLE).first();
                String url = title.attr(ResultPageCSSLocators.WEB_LINK_URL);
                String name = title.text();
                String description = element.getElementsByClass(ResultPageCSSLocators.WEB_LINK_DESCRIPTION).first().text();
                WebLink webLink = new WebLink();
                webLink.setName(name);
                webLink.setDescription(description);
                webLink.setUrl(url);
                webLinks.add(webLink);
            }
        }
        return webLinks;
    }
}
