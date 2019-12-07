package io.zipcoder.crudapp;

import Controller.PersonController;
import Model.Person;
import Services.PersonServices;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CRUDApplication.class})
public class PersonControllerTest {
    private MockMvc mockMvc;

    @Mock
    private PersonServices personServices;

    @InjectMocks
    private PersonController personController;
    List<Person> personList;

    @Before
    public void init(){
        personList = Arrays.asList(
                new Person(1L, "Zan", "Cheemers"),
                new Person(2L,"John", "Tokhi"));
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(personController)
                .build();
    }

    @Test
    public void getAllTest() throws Exception {
//        List<Person> personList = Arrays.asList(
//                new Person(1L, "Zan", "Cheemers"),
//                new Person(2L,"John", "Tokhi"));

        when(personController.create(new Person(1L, "Zan", "Cheemers"))).thenReturn(personList);

        mockMvc.perform(get("/personList"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect((ResultMatcher) jsonPath("$[0].id", is(1)))
                .andExpect((ResultMatcher) jsonPath("$[0].firstName", is("Zan")))
                .andExpect((ResultMatcher) jsonPath("$[0].lastName", is("Cheemers")))
                .andExpect((ResultMatcher) jsonPath("$[1].id", is(2)))
                .andExpect((ResultMatcher) jsonPath("$[1].firstName", is("John")))
                .andExpect((ResultMatcher) jsonPath("$[1].lastName", is("Tokhi")));

        verify(personController, times(1)).getAll();
        verifyNoMoreInteractions(personServices);
    }





}
