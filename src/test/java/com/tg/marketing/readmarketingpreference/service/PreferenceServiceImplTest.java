package com.tg.marketing.readmarketingpreference.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.tg.marketing.readmarketingpreference.dto.MarketingPreferenceDTO;
import com.tg.marketing.readmarketingpreference.entity.MarketingPreferenceEntity;
import com.tg.marketing.readmarketingpreference.repo.PreferenceRepository;

@RunWith(SpringRunner.class)
//@WebMvcTest(value = PreferenceServiceImpl.class)
//@WithMockUser
public class PreferenceServiceImplTest {

	@TestConfiguration
	static class PreferenceServiceImplTestContextConfiguration {

		@Bean
		public PreferenceServiceImpl preferenceServiceImpl() {
			return new PreferenceServiceImpl();
		}
	}

	@Autowired
	private PreferenceServiceImpl preferenceServiceImpl;

	@MockBean
	private PreferenceRepository preferenceRepository;

	@Before
	public void setUp() {
		MarketingPreferenceEntity mockMarketingPreferenceEntity = new MarketingPreferenceEntity(1L, "abcd@gmail.com",
				"danny Test", "Marketing Campaign", "123", "EMAIL");

		List<MarketingPreferenceEntity> list = List.of(mockMarketingPreferenceEntity);

		Mockito.when(preferenceRepository.findByCif("123")).thenReturn(list);
	}

	@Test
	public void getMarketingPreferences() throws Exception {

		MarketingPreferenceDTO mockMarketingPreference = new MarketingPreferenceDTO(1L, "abcd@gmail.com", "danny Test",
				"Marketing Campaign", "123", "EMAIL");
		
		MarketingPreferenceDTO found = preferenceServiceImpl.getPreferences("123");
		
		assertThat(found).isEqualTo(mockMarketingPreference);
	}
	
	
	@Test
	public void getMarketingPreferencesWhenCifNull() throws Exception {

		MarketingPreferenceDTO mockMarketingPreference = new MarketingPreferenceDTO(1L, "abcd@gmail.com", "danny Test",
				"Marketing Campaign", "123", "EMAIL");
		
		MarketingPreferenceDTO found = preferenceServiceImpl.getPreferences(null);
		
		assertThat(found).isEqualTo(null);
	}

}