package com.controller;

import com.entity.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
 import com.service.PersonService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
 import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
 import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.Collections;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
 import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * The type Person controller test.
 */
@AutoConfigureMockMvc
@SpringBootTest
 public class PersonControllerTest {

@Autowired
    private MockMvc mockMvc;
    @Mock
    private PersonService personService;


    /**
     * The Object mapper.
     */
    ObjectMapper objectMapper = new ObjectMapper();
    /**
     * The Person.
     */
    Person person = new Person(1,"Akshit","kumar","UP","male");

    /**
     * Should add the new person.
     */
    @SneakyThrows
    @Test
    public void   should_add_the_new_person()  {
        when(personService.create(person))
                .thenReturn(person);
           mockMvc.perform(post("/person/create")
                        .content(objectMapper.writeValueAsString(person))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    /**
     * Should delete the employee with the given employee id.
     *
     * @throws Exception the exception
     */
    @Test
    public void should_delete_the_employee_with_the_given_employee_id() throws Exception {
        when(personService.deletePerson(1))
                .thenReturn(HttpStatus.MOVED_PERMANENTLY);
        mockMvc.perform(MockMvcRequestBuilders.delete("/person/delete/1")
                        .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

        }

    /**
     * Should get all person.
     *
     * @throws Exception the exception
     */
    @Test
    public void should_get_all_person() throws Exception {
       when(personService.getAllPerson())
                .thenReturn(Collections.singletonList(person));
        mockMvc.perform(MockMvcRequestBuilders.
                        get("/person/getAll")).
                andExpect(status().isOk());
    }

    /**
     * Should get person by id.
     *
     * @throws Exception the exception
     */
    @Test
    public void should_get_person_by_id() throws Exception {
        when(personService.getPersonByID(1))
                .thenReturn(person);
        mockMvc.perform(MockMvcRequestBuilders.
                        get("/person/1")).
                andExpect(status().isOk());
    }

    /**
     * Should update the employee with the given employee id.
     *
     * @throws Exception the exception
     */
    @Test
    public void should_update_the_employee_with_the_given_employee_id() throws Exception {
        Person updatedPerson = new Person(1,"Rahul","kumar","UP","male");

        when(personService.updatePerson(person, person.getId()))
                .thenReturn(person);
        when(personService.create(updatedPerson))
                .thenReturn(updatedPerson);
        mockMvc.perform(MockMvcRequestBuilders.delete("/person/delete/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

}

