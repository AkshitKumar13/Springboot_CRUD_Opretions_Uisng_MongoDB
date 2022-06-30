package com.service;

import com.entity.Person;
import com.repositary.PersonRepositary;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

/**
 * The type Person service test.
 */
@SpringBootTest
public class PersonServiceTest {
    @Mock
    private PersonService personService;

    @Mock
    private PersonRepositary personRepositary;


    private Person getPerson() {
        Person person = new Person();
        person.setId(1);
        person.setFirstName("Akshit");
        person.setLastName("Kumar");
        person.setAddress("UP");
        person.setGender("male");
        return person;
    }

    /**
     * Test get all employees.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetAllEmployees() throws Exception {
        Person personDetail= getPerson();
        List<Person> personList = new ArrayList<>();
        personList.add(personDetail);

        when(personRepositary.findAll()).thenReturn(personList);
        List<Person> result = personRepositary.findAll();
         assertEquals(result.size(), 1);
    }

    /**
     * Test get employee.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetEmployee() throws Exception {
        Person personDetail= getPerson();

        when(personRepositary.findById(1)).thenReturn(Optional.of(personDetail));
        Optional<Person> result = personRepositary.findById(1);
         assertEquals(result.get().getId(), 1);
    }

}