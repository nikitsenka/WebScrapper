package sites.linkedIn.io;

import sites.linkedIn.model.Person;

import java.util.List;

public interface PersonRepository {
    List<Person> getAll() throws InvalidInputData;
    void addAll(List<Person> persons);
}
