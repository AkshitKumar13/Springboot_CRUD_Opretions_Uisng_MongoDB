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
     * Create.
     *
     * @param person the person
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
     * Update person person.
     *
     * @param person the person
     * @param id     the id
     * @return the person
     */
    public Person updatePerson(Person person, int id) {
        return personRepositary.findById(Math.toIntExact(id)).map(person1 -> {
            person1.setFirstName(person.getFirstName());
            person1.setLastName(person.getLastName());
            person1.setAddress(person.getAddress());
            person1.setGender(person.getGender());
            return (personRepositary.save(person1));
        }).orElseThrow(() -> new PersonNotFoundException("ID Not Found" + " " + id));
    }

    public HttpStatus deletePerson(int id) {
        try {
            personRepositary.deleteById(id);
        } catch (Exception e) {
            throw new PersonNotFoundException("ID Not Found" + " " + id);
        }
      return HttpStatus.MOVED_PERMANENTLY;

    }
}

