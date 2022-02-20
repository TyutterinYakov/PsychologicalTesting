package psychology.api.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import psychology.PsychologyTestApplication;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, 
	classes = PsychologyTestApplication.class)
@AutoConfigureMockMvc
public class SchoolControllerTest {
	private static final Logger logger = LoggerFactory.getLogger(SchoolControllerTest.class);
	
	@Autowired
	private MockMvc mvc;
	
	
	@Test
	public void addSchoolTest() throws Exception {
//		String schoolName = "Школа №1 Город Сясьтрой";
//		MvcResult result = mvc.perform(MockMvcRequestBuilders.post(SchoolController
//				.CREATE_SCHOOL.replace("{school_name}", schoolName))
//		).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();
//		
//		logger.info(result.getResponse().toString());
	}
	
	@Test
	public void deleteSchoolTest() throws Exception {
//		Long schoolId = 7L;
//		MvcResult result = mvc.perform(MockMvcRequestBuilders.delete(SchoolController
//				.DELETE_SCHOOL.replace("{school_id}", schoolId.toString()))
//		).andExpect(MockMvcResultMatchers.status().isNoContent()).andReturn();
//		
//		logger.info(result.getResponse().toString());
		
	}
}
