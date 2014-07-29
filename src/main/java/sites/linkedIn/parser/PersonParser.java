package sites.linkedIn.parser;

import api.Page;
import sites.linkedIn.model.Person;

import java.util.List;

public interface PersonParser {
    List<Person> getPersonsDescription();
}
