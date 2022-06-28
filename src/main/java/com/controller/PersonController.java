package com.controller;
import com.entity.Person;
import com.repositary.PersonRepositary;
import com.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The type Person controller.
 */
@RestController
@RequestMapping(value = "person")
public class PersonController {
    @Autowired
    private PersonService personService;
    @Autowired
    private PersonRepositary personRepositary;

    /**
     * Create person string.
     *
     * @param person the person
     * @return the string
     */
    @PostMapping("/create")
    public String createPerson(@RequestBody Person person) {
        personService.create(person);
        return HttpStatus.CREATED.toString();
    }

    /**
     * Get person response entity.
     *
     * @return the response entity
     */
    @GetMapping("/getAll")
    public ResponseEntity<List<Person>> getPerson() {
        return ResponseEntity.ok(personService.getAllPerson());
    }

    /**
     * Find person by id response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @GetMapping("/{id}")
    public ResponseEntity<Person> findPersonByID(@PathVariable("id") int id) {
        return ResponseEntity.ok(personService.getPersonByID(id));
    }


    /**
     * Delete product http status.
     *
     * @param id the id
     * @return the http status
     */
    @DeleteMapping("/delete/{id}")
    public HttpStatus deletePersonById(@PathVariable int id) {
         personService.deletePerson(id);
        return HttpStatus.MOVED_PERMANENTLY;
    }
}
