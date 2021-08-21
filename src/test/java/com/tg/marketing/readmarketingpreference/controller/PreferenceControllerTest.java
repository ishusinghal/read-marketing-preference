package com.tg.marketing.readmarketingpreference.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.tg.marketing.readmarketingpreference.dto.MarketingPreferenceDTO;
import com.tg.marketing.readmarketingpreference.service.PreferenceServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(value = PreferenceController.class)
@WithMockUser
public class PreferenceControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PreferenceServiceImpl preferenceServiceImpl;

	MarketingPreferenceDTO mockMarketingPreference = new MarketingPreferenceDTO(1L, "abcd@gmail.com", "danny Test",
			"Marketing Campaign", "123", "EMAIL");
	
	@Test
	public void getMarketingPreferences() throws Exception {

		Mockito.when(preferenceServiceImpl.getPreferences(Mockito.anyString())).thenReturn(mockMarketingPreference);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customers/123/preferences")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{\"prefId\":1,\"to\":\"abcd@gmail.com\",\"message\":\"danny Test\",\"subject\":\"Marketing Campaign\",\"cif\":\"123\",\"marketingPreferenceType\":\"EMAIL\"}"; 

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
	
	@Test
	public void getMarketingPreferencesWhenPrefNotFound() throws Exception {

		Mockito.when(preferenceServiceImpl.getPreferences(Mockito.anyString()))
		.thenReturn(null);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customers/567/preferences")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "Unable to find Marketing Prference for the Customer"; 

		assertEquals(expected, result.getResponse().getContentAsString());
	}


}
