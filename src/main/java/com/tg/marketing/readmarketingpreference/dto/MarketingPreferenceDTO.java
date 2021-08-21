package com.tg.marketing.readmarketingpreference.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MarketingPreferenceDTO {
	
	private Long prefId;
	
	private String to;

	private String message;

	private String subject;
	
	private String cif;

	private String marketingPreferenceType;
	
	public MarketingPreferenceDTO(Long prefId, String to, String message, String subject, String cif,
			String marketingPreferenceType) {
		super();
		this.prefId = prefId;
		this.to = to;
		this.message = message;
		this.subject = subject;
		this.cif = cif;
		this.marketingPreferenceType = marketingPreferenceType;
	}
}
