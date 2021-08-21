package com.tg.marketing.readmarketingpreference.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tg.marketing.readmarketingpreference.dto.MarketingPreferenceDTO;
import com.tg.marketing.readmarketingpreference.service.PreferenceServiceImpl;


@RestController
@RequestMapping(value = "/customers/{cif}/preferences", produces = { MediaType.APPLICATION_JSON_VALUE })
public class PreferenceController {

	@Autowired
	PreferenceServiceImpl preferenceServiceImpl;
	
	@GetMapping
	public ResponseEntity<?> getMarketingPreferences(@PathVariable("cif") String cif) {
		if (Objects.isNull(cif)) {
			return new ResponseEntity<String>("Customer Id should be given", HttpStatus.BAD_REQUEST);
		}

		MarketingPreferenceDTO marketingPreferenceDTO = new MarketingPreferenceDTO();

		marketingPreferenceDTO = preferenceServiceImpl.getPreferences(cif);
		
		if(Objects.nonNull(marketingPreferenceDTO)) {
			return new ResponseEntity<com.tg.marketing.readmarketingpreference.dto.MarketingPreferenceDTO>(marketingPreferenceDTO, HttpStatus.OK); 
		}
		return new ResponseEntity<String>("Unable to find Marketing Prference for the Customer", HttpStatus.NOT_FOUND);
		
		
	}

}
