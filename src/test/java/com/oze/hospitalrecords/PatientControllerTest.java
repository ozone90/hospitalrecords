package com.oze.hospitalrecords;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.oze.hospitalrecords.dto.GenericResponse;
import com.oze.hospitalrecords.dto.StaffDto;
import com.oze.hospitalrecords.dto.StaffResponse;
import com.oze.hospitalrecords.enums.ResponseCodes;
import com.oze.hospitalrecords.model.Staff;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientControllerTest {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @Before
    public void beforeEach() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .build();
    }

    @Test
    public void shouldCreateStaff() throws Exception {
        StaffResponse staffResponse = createStaff();
        assertThat(staffResponse.getId()).isNotNull();
        assertThat(staffResponse.getName()).isNotEmpty();
    }

    @Test
    public void shouldUpdateStaff() throws Exception {
        GenericResponse genericResponse = updateStaff();
        assertThat(genericResponse.getCode()).isNotNull();
        assertThat(genericResponse.getMessage()).isNotEmpty();
    }


    private StaffResponse createStaff() throws Exception {
        Faker faker =  new Faker();
        StaffDto staffDto = new StaffDto();
        staffDto.setName(faker.name().name());
        staffDto.setRegistrationDate(new Date());

        String postValue = objectMapper.writeValueAsString(staffDto);

        MvcResult storyResult = mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/staff")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(postValue))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        return objectMapper.readValue(storyResult.getResponse().getContentAsString(), StaffResponse.class);
    }

    private GenericResponse updateStaff() throws Exception {
        Staff staff = new Staff();


        String postValue = objectMapper.writeValueAsString(staff);

        MvcResult storyResult = mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/staff/"+1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(postValue))
                .andExpect(jsonPath("$.code", is(ResponseCodes.SUCCESSFUL.getCode())))
                .andExpect(jsonPath("$.message", is(ResponseCodes.SUCCESSFUL.getDescription())))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        return objectMapper.readValue(storyResult.getResponse().getContentAsString(), GenericResponse.class);
    }
}