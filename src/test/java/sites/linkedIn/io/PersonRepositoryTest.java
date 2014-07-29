package sites.linkedIn.io;

import org.junit.Before;
import org.junit.Test;
import sites.linkedIn.model.Person;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PersonRepositoryTest {

    @Test
    public void getAll() throws Exception {
        PersonRepository personRepository = new ExcelPersonRepository(new File(getClass().getResource("/testRepositoryGetAll.xlsx").toURI().getPath()));
        List<Person> result = personRepository.getAll();
        assertThat(result.size(), is(4));
        assertThat(result.get(0).getName(), is("name1"));
        assertThat(result.get(0).getDescription(), is("desc1"));
        assertThat(result.get(1).getName(), is("name2"));
        assertThat(result.get(1).getDescription(), is("desc2"));
        assertThat(result.get(2).getName(), is("name3"));
        assertThat(result.get(2).getDescription(), is("desc3"));
        assertThat(result.get(3).getName(), is("name4"));
        assertThat(result.get(3).getDescription(), is("desc4"));
    }
    @Test
    public void addAll() throws URISyntaxException, InvalidInputData {
        PersonRepository personRepository = new ExcelPersonRepository(new File(getClass().getResource("/testRepositoryAddAll.xlsx").toURI().getPath()));
        Person person1 = new Person("testName1");
        person1.setDescription("testDescription1");
        Person person2 = new Person("testName2");
        person2.setDescription("testDescription2");
        Person person3 = new Person("testName3");
        person3.setDescription("testDescription3");
        personRepository.addAll(Arrays.asList(new Person[]{person1,person2, person3}));
        List<Person> result = personRepository.getAll();
        assertThat(result.size(), is(3));
        assertThat(result.get(0).getName(), is("testName1"));
        assertThat(result.get(0).getDescription(), is("testDescription1"));
        assertThat(result.get(1).getName(), is("testName2"));
        assertThat(result.get(1).getDescription(), is("testDescription2"));
        assertThat(result.get(2).getName(), is("testName3"));
        assertThat(result.get(2).getDescription(), is("testDescription3"));

    }


}