package com.tg.marketing.readmarketingpreference.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tg.marketing.readmarketingpreference.entity.MarketingPreferenceEntity;

@Repository
public interface PreferenceRepository extends JpaRepository<MarketingPreferenceEntity, Long> {

	List<MarketingPreferenceEntity> findByCif(@Param("cif") String cif);

}
