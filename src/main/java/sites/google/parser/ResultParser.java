package sites.google.parser;


import api.Page;
import sites.google.model.WebLink;

import java.util.List;

public interface ResultParser {
    List<WebLink> getResults(Page result);
}
