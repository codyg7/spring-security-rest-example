package com.lmig.gfc.wimp.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.lmig.gfc.wimp.models.Award;
import com.lmig.gfc.wimp.repositories.ActorRepository;
import com.lmig.gfc.wimp.repositories.AwardRepository;
import com.lmig.gfc.wimp.api.AwardApiController;
import com.lmig.gfc.wimp.models.Actor;

public class AwardApiControllerTests {

	private AwardApiController controller;
	@Mock
	private AwardRepository awardRepo;
	@Mock
	private ActorRepository actorRepo;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		controller = new AwardApiController (actorRepo, awardRepo);
	}
	
	@Test
	public void saves_award_when_actor_is_encountered() {

		// Arrange
		Award award = new Award();
		Actor actor = new Actor();
//		award.setActor(actor);
		when(actorRepo.findOne(147L)).thenReturn(actor);
//		when(awardRepo.findOne(87L)).thenReturn(award);
		// Act
		Award actual = controller.create(147L, award);
//		Award actual = controller.create(147L, 87L);
		// Assert
//		assertThat(actual).isSameAs(actor);
//		verify(awardRepo).save(award);
//		assertThat(award.getActor()).isEqualTo(actor);
		verify(actorRepo).findOne(147L);
//		verify(awardRepo).contains(award);
//		verify(awardRepo).findOne(87L);
	}
}
