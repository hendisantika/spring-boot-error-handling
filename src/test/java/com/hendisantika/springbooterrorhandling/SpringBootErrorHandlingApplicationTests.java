package com.hendisantika.springbooterrorhandling;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootErrorHandlingApplicationTests {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testLoadUser() throws Exception {
        // Loads the user information for the user admin and expects a http status 200.
        mockMvc.perform(get("/user/admin"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is("admin")));

        // Fails while loading the user information for the user 'foo' and expects a http status 400.
        mockMvc.perform(get("/user/foo"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Username not found.")));
    }


}

