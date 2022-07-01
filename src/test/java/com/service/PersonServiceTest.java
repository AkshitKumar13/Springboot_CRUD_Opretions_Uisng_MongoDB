package com.service;

import com.entity.Person;
import com.repositary.PersonRepositary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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
@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepositary personRepositary;
    @BeforeEach
public void setUp(){
    this.personService=new PersonService(this.personRepositary);
}

    private Person getPerson() {
        Person person = new Person();
        person.setId(1);
        person.setFirstName("Akshit");
        person.setLastName("chaudhary");
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
    public void should_Get_All_Person() throws Exception {
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
    public void should_Get_One_Person_By_Id() throws Exception {
        Person personDetail= getPerson();

        when(personRepositary.findById(1)).thenReturn(Optional.of(personDetail));
        Optional<Person> result = personRepositary.findById(1);
         assertEquals(result.get().getId(), 1);
    }
    @Test
    public void should_Save_The_Person() throws Exception {
        Person personDetail= getPerson();

        when(personRepositary.save(personDetail)).thenReturn(personDetail);
         Person result = personRepositary.save(personDetail);
        assertEquals(result.getFirstName(), "Akshit");
    }



}