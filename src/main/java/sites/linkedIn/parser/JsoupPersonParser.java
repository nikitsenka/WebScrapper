package sites.linkedIn.parser;

import api.Page;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sites.linkedIn.model.Person;

import java.util.ArrayList;
import java.util.List;

public class JsoupPersonParser implements PersonParser {
    @Override
    public final List<Person> getPersonsDescription(Page searchResultPage){
        Document doc = Jsoup.parse(searchResultPage.getPageSource());
        List<Person> persons = new ArrayList<>();
        Elements elements = doc.select("#results > li");
        if(elements.size() != 0 ){
            for(Element element: elements){
                Element title = element.getElementsByClass("title").first();
                String url = title.attr("href");
                String name = title.text();
                String description = element.getElementsByClass("description").first().text();
                String pictureUrl = element.getElementsByClass("entity-img").first().text();
                Person person = new Person(name);
                person.setDescription(description);
                person.setPictureUrl(pictureUrl);
                person.setProfileUrl(url);
                persons.add(person);
            }
        }
        return persons;
    }
}
