package com.blog.springbootdynamodb;

import com.amazonaws.services.dynamodbv2.xspec.S;
import com.blog.springbootdynamodb.controller.SignupController;
import com.blog.springbootdynamodb.entity.Signup;
import com.blog.springbootdynamodb.repository.SignupRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Tag;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@WebMvcTest(SignupController.class)
@Slf4j
public class SignupControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SignupRepository signupRepository;

//    @Before
//    public void setUp() throws Exception {
//        mockMvc = MockMvcBuilders.standaloneSetup(signup).build();
//    }

    @Test
    public void testSignUp() throws Exception {
        Signup temp = new Signup();
        temp.setSignupId("12342354");
        temp.setEmail("jb199815@gmail.com");
        temp.setFirstName("wef");
        temp.setLastName("erg");
        temp.setPassword("qwerty");
        temp.setConfirmPassword("qwerty");

        when(signupRepository.getAllUsers()).thenReturn(Arrays.asList(temp));
        MvcResult mvcResult =  this.mockMvc.perform(MockMvcRequestBuilders.get("/user/getAllUsers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName").value("wef"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].signupId").value("12342354"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName").value("erg"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("jb199815@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].password").value("qwerty"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].confirmPassword").value("qwerty"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        String responseBody = mvcResult.getResponse().getContentAsString();

        log.error("ResponseBody is",responseBody);

    }

}