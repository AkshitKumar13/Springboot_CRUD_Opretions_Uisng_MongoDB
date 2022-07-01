package com.controller;

import com.entity.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.repositary.PersonRepositary;
import com.service.PersonService;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Collections;

import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
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

     @MockBean
    private PersonService personService;

     /**
     * The Object mapper.
     */
    ObjectMapper objectMapper = new ObjectMapper();

    /**
     * The Person.
     */

     Person  person=new Person(1, "rahul", "kumar", "UP", "male");

    /**
     * Should add the new person.
     */
    @SneakyThrows
    @Test
    public void should_add_the_new_person() {
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
    public void updateStudent_test() throws Exception {
        Person updatedPerson = new Person();
        updatedPerson.setId(1);
        updatedPerson.setFirstName("akshit");
        updatedPerson.setLastName("chaudhary");
        updatedPerson.setAddress("up");
        updatedPerson.setGender("m");


        Mockito.when(personService.updatePerson(updatedPerson,updatedPerson.getId()))
                .thenReturn(updatedPerson);

        mockMvc.perform(MockMvcRequestBuilders.put("/person/update/1")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(updatedPerson))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());



     }

}