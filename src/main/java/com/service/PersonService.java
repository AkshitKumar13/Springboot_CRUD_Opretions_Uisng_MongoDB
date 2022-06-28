package com.service;
import com.customException.PersonNotFoundException;
import com.entity.Person;
import com.repositary.PersonRepositary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Person service.
 */
@Service
public class PersonService {
    @Autowired
    private PersonRepositary personRepositary;

    /**
     * Instantiates a new Person service.
     *
     * @param personRepositary the person repositary
     */
    public PersonService(PersonRepositary personRepositary) {
        this.personRepositary=personRepositary;
    }

    /**
     * Create.
     *
     * @param person the person
     * @return the person
     */
    public Person create(Person person) {
        return personRepositary.save(person);
    }

    /**
     * Gets all person.
     *
     * @return the all person
     */
    public List<Person> getAllPerson() {
        return personRepositary.findAll();
    }

    /**
     * Gets person by id.
     *
     * @param id the id
     * @return the person by id
     */
    public Person getPersonByID(int id) {
        return personRepositary.findById(id).orElseThrow(() -> new PersonNotFoundException("ID Not Found" + id));
    }


    /**
     * Delete person http status.
     *
     * @param id the id
     * @return the http status
     */
    public HttpStatus deletePerson(int id) {
        try {
            personRepositary.deleteById(id);
        } catch (Exception e) {
            throw new PersonNotFoundException("ID Not Found" + " " + id);
        }
      return HttpStatus.MOVED_PERMANENTLY;

    }
}

