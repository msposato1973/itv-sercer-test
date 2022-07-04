package com.itv.leedstech.techtest;

import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itv.leedstech.techtest.model.Programme;
import com.itv.leedstech.techtest.model.ProgrammeServiceResponse;
import com.itv.leedstech.techtest.model.ScheduleEntryRequest;

@SpringBootTest
@AutoConfigureMockMvc
class TechtestApplicationTests {

	private static final String URL_PROGRAM = "http://localhost:8080/api/itv/programme";
	private static final String URL_SCHEDULE_ENTRY = "http://localhost:8080/api/itv/programme/schedule/entry";
 

	private static final String ID = "CEN34";
	private static final String CONTENT_TYPE = "application/json";
	private static final String TITLE = "The bullet which turned around the corner";
	private static final Double PLAY_COST = Double.valueOf("100.0");

	@Autowired
	private MockMvc mockMvc;

	 
	@Test
	void testLoadProgramme() {
		try {
			// given - setup or precondition
			Programme programme = buildProgrammeRequest(ID);

			// when - action
			MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(URL_PROGRAM)
					.contentType(MediaType.APPLICATION_JSON)
					.content(asJsonString(programme));
			
			ResultActions resultActions = mockMvc.perform(builder);

			// then - verify the output
			ProgrammeServiceResponse response = new ProgrammeServiceResponse(true);
			String expectedJsonResponse = asJsonString(response);
			
			resultActions.andExpect(status().isCreated());
			resultActions.andExpect(content().contentType(CONTENT_TYPE));
			resultActions.andReturn().equals(expectedJsonResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	 
	@Test
	void testLoadScheduleEntryCaseSingle() {
		ScheduleEntryRequest request = null;
		try {
			// given - setup or precondition
			testLoadProgramme();
			request = buildScheduleEntryRequest(ID,"2021-06-13T12:00:00Z");

			// when - action
			ResultActions resultActions = this.loadScheduleEntry(request);

			// then - verify the output
			ProgrammeServiceResponse response = new ProgrammeServiceResponse(true);
			String expectedJsonResponse = asJsonString(response);
			resultActions.andExpect(status().isCreated());
			resultActions.andExpect(content().contentType(CONTENT_TYPE));
			resultActions.andReturn().equals(expectedJsonResponse);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	 
	@Test
	void testLoadScheduleEntryCaseFree() {
		ScheduleEntryRequest request = null;
		ResultActions resultActions = null;
		try {
			// given - setup or precondition
			testLoadProgramme();
			request = buildScheduleEntryRequest(ID,"2021-06-13T12:00:00Z");
			resultActions = this.loadScheduleEntry(request);

			request = buildScheduleEntryRequest(ID,"2021-06-14T12:00:00Z");

			// when - action
			resultActions = this.loadScheduleEntry(request);

			// then - verify the output
			ProgrammeServiceResponse response = new ProgrammeServiceResponse(true);
			String expectedJsonResponse = asJsonString(response);
			resultActions.andExpect(status().isCreated());
			resultActions.andExpect(content().contentType(CONTENT_TYPE));
			resultActions.andReturn().equals(expectedJsonResponse);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	 
	@Test
	void testLoadScheduleEntryCaseFifty() {
		ScheduleEntryRequest request = null;
		ResultActions resultActions = null;
		try {
			// given - setup or precondition
			testLoadProgramme();
			request = buildScheduleEntryRequest(ID,"2021-06-13T12:00:00Z");

			resultActions = loadScheduleEntry(request);
			request = buildScheduleEntryRequest(ID,"2021-06-13T12:00:00Z");

			resultActions = loadScheduleEntry(request);
			request = buildScheduleEntryRequest(ID,"2021-06-13T12:00:00Z");

			// when - action
			resultActions = this.loadScheduleEntry(request);

			// then - verify the output
			ProgrammeServiceResponse response = new ProgrammeServiceResponse(true);
			String expectedJsonResponse = asJsonString(response);
			resultActions.andExpect(status().isCreated());
			resultActions.andExpect(content().contentType(CONTENT_TYPE));
			resultActions.andReturn().equals(expectedJsonResponse);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/***
	 * 
	 * @param request
	 * @return
	 */
	private ResultActions loadScheduleEntry(ScheduleEntryRequest request) {
		ResultActions resultActions = null;
		try {
			// when - action
			MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(URL_SCHEDULE_ENTRY)
					.contentType(MediaType.APPLICATION_JSON).content(asJsonString(request));

			resultActions = mockMvc.perform(builder);
		} catch (Exception e) {
			fail(e.getMessage());
		}
		return resultActions;
	}

	/***
	 * 
	 * @param obj
	 * @return
	 */
	private static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/***
	 * 
	 * @return
	 */
	private Programme buildProgrammeRequest(String idProgram) {
		return Programme.builder().id(ID).title(TITLE).playoutCost(PLAY_COST).discountable(true).build();
	}

	/***
	 * 
	 * @param timezone
	 * @return
	 */
	private ScheduleEntryRequest buildScheduleEntryRequest(String idProgram, String timezone) {
		return  ScheduleEntryRequest.builder().programmeId(idProgram).timestamp(timezone).build();
	}

}
