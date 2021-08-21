package com.tg.marketing.readmarketingpreference.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tg.marketing.readmarketingpreference.dto.MarketingPreferenceDTO;
import com.tg.marketing.readmarketingpreference.entity.MarketingPreferenceEntity;
import com.tg.marketing.readmarketingpreference.repo.PreferenceRepository;

@Service
public class PreferenceServiceImpl implements Preference {

	@Autowired
	PreferenceRepository repository;

	@Autowired(required = false)
	MarketingPreferenceEntity entity;

	@Override
	public MarketingPreferenceDTO getPreferences(String cif) {
		entity = new MarketingPreferenceEntity();
		List<MarketingPreferenceEntity> prefObj  =  repository.findByCif(cif);
		MarketingPreferenceDTO marketingPreferenceDTO= new MarketingPreferenceDTO();
		
		if (!prefObj.isEmpty()) {
			BeanUtils.copyProperties(prefObj.get(0), marketingPreferenceDTO);
			return marketingPreferenceDTO;
		}
		return null;
	}

}