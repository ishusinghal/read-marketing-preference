package com.tg.marketing.readmarketingpreference.repo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.tg.marketing.readmarketingpreference.entity.MarketingPreferenceEntity;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PreferenceRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private PreferenceRepository preferenceRepository;

//	@Test
//	@Sql("pref.sql")
//	void whenInitializedByDbUnit_thenFindsByCif() {
////		preferenceRepository.save(new MarketingPreferenceEntity(1L, "abcd@gmail.com", "danny Test",
////				"Marketing Campaign", "123", "EMAIL"));
////
//		List<MarketingPreferenceEntity> list = preferenceRepository.findByCif("123");
//		assertThat(list).isNotNull();
//
//	}

	
	@Test
	public void whenInitializedByDbUnit_thenFindsByCif() {
	    // given
		MarketingPreferenceEntity entity = new MarketingPreferenceEntity(1L, "abcd@gmail.com", "danny Test",
			"Marketing Campaign", "123", "EMAIL");
	    entityManager.persist(entity);
	    entityManager.flush();

	    // when
	    List<MarketingPreferenceEntity> found = preferenceRepository.findByCif(entity.getCif());

	    // then
	    assertThat(found.get(0).getCif())
	      .isEqualTo(entity.getCif());
	}
	
}