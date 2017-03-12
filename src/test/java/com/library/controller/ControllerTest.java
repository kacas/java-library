package com.library.controller;

import com.library.LibraryApplicationTests;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.boot.test.SpringApplicationConfiguration;

/**
 * Created by Katarina on 3/6/2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = LibraryApplicationTests.class)
@SpringApplicationConfiguration(classes = LibraryApplicationTests.class)
@WebAppConfiguration
@Transactional
@ActiveProfiles("test")
public class ControllerTest {

    @Autowired
    protected WebApplicationContext webApplicationContext;

    protected MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

}
