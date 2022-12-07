package canard.intern.post.following.backend.controller;

import canard.intern.post.following.backend.controller.fixture.TraineeJsonProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = TraineeController.class)
class TraineeControllerTest {

    final static String BASE_URL= "/api/trainees";
    final static String URL_TEMPLATE_ID = BASE_URL + "/{id}";

    @Autowired
    MockMvc mockMvc;

    @Test
    void getAll() {
    }

    @Test
    void getById() throws Exception {
        int id = 2;
        mockMvc.perform(get(URL_TEMPLATE_ID +"/aa/aa", id)
                        .accept(MediaType.APPLICATION_JSON)
                )

                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                //.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id").value(id));

        //TraineeController traineeController = new TraineeController();
        //var trainee = traineeController.getById(3);
    }

    @Test
    void getById_KO_idNotFound(){
        //TODO
        fail("Test not defined yet");
    }

    @Test
    void getById_KO_xmlNotAcceptable() throws Exception {
    int id = 2;
    mockMvc.perform(get(URL_TEMPLATE_ID, id)
                    .accept(MediaType.APPLICATION_XML)
            )
            .andDo(print())
            .andExpect(status().isNotAcceptable());
}

    @Test
    void create_ok_allFieldsValid() throws Exception {
        var traineeJson = TraineeJsonProvider.traineeJsonAllFieldsValid();
        //System.out.println(traineeJson);
        mockMvc.perform(post(BASE_URL )
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"lastname\":\"Bond\"}")
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$id").exists());
        //NB: check all fields in response are equals to request fields
    }

    @ParameterizedTest
    @MethodSource("canard.intern.post.following.backend.controller.fixture#traineeJsonMissingNonRequiredField")
    void create_ok_missingNonRequiredFields(String traineeJson) throws Exception {
        mockMvc.perform(post(BASE_URL )
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(traineeJson)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$id").exists());
    }

    @Test
    void create_ko_missingRequiredFields(){
        //lastname,firstname,email,birthdate
        fail("Test not defined yet");

    }

    //TODO: all invalid fields
    @Test
    void create_KO_invalidBirthdate() throws Exception {
        //email with pattern,birthdate <18 years, phone number
        var traineeJson = TraineeJsonProvider.traineeJasonInvalidBirthdate();
        mockMvc.perform(post(BASE_URL )
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(traineeJson)
                )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void update_OK() {
        //id found +
        // -all fields valid + no id in body
        //- all fields valid + id in body equals to id in path
        fail("Test not defined yet");
    }

    @Test
    void update_KO() {
        //- id not found
        // -id found +
        // .invalid field(s)
        // .all fields valid + id in body different from id in path
        fail("Test not defined yet");
    }

    @Test
    void delete_OK() {
        fail("Test not defined yet");
    }

    @Test
    void delete_KO() {
        fail("Test not defined yet");
    }
}