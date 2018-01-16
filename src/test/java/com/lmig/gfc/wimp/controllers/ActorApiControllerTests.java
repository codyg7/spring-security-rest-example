package com.lmig.gfc.wimp.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.lmig.gfc.wimp.api.ActorApiController;
import com.lmig.gfc.wimp.models.Actor;
import com.lmig.gfc.wimp.models.ActorView;
import com.lmig.gfc.wimp.repositories.ActorRepository;

public class ActorApiControllerTests {

	private ActorApiController controller;
	@Mock
	private ActorRepository actorRepo;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		controller = new ActorApiController(actorRepo);
		 }
		 @Test
		 public void getAll_with_not_null_returns_list_of_actors() {
		 // Arrange
		 ArrayList<Actor> actors = new ArrayList<Actor>();
//		 ArrayList<ActorView> actorView = new ArrayList<ActorView>();
		when(actorRepo.findAll()).thenReturn(actors);
		 // when (the method call used in the code being tested)
		 // .thenReturn(a value that you created)
		 // Act
		 List<ActorView> actual = controller.getAll();
		 // Assert
		 assertThat(actual).hasSize(actors.size());
		 verify(actorRepo).findAll();
		 }
	

	@Test
	public void getOne_returns_an_actor_for_a_valid_id() {
		// Arrange
		Actor actor = new Actor();
		when(actorRepo.findOne(1L)).thenReturn(actor);
		// Act
		Actor actual = controller.getOne(1L);
		// Assert
		assertThat(actual).isSameAs(actor);
		verify(actorRepo).findOne(1L);
	}

	@Test
	public void create_saves_the_actor_and_returns_it() {
		// Arrange
		Actor actor = new Actor();
		when(actorRepo.save(actor)).thenReturn(actor);
		// Act
		Actor actual = controller.create(actor);
		// Assert
		assertThat(actual).isSameAs(actor);
		verify(actorRepo).save(actor);
	}
	
	@Test
	public void getOne_returns_an_actor_for_an_invalid_id() {
		 when(actorRepo.findOne(0L)).thenReturn(null);
		// Act
		Actor actual = controller.getOne(0L);
		// Assert
		assertThat(actual).isNull();
		verify(actorRepo).findOne(0L);
	}
	@Test
	public void update_sets_id_of_actor_and_calls_saves_and_returns_actor() {
		//Arrange
		Actor actor = new Actor();
		when(actorRepo.save(actor)).thenReturn(actor);
		//Act
		Actor actual = controller.update(actor, 6055L);
		//Assert
		assertThat(actual).isSameAs(actor);
		verify(actorRepo).save(actor);
		assertThat(actor.getId()).isEqualTo(6055L);		
	}
	@Test
	public void delete_gets_the_actor_and_deletes_it_from_the_repo_and_returns_it() {
		//Arrange
		Actor actor = new Actor();
		when(actorRepo.findOne(88L)).thenReturn(actor);
		//Act
		Actor actual = controller.delete(88L);
		//Assert
		assertThat(actual).isSameAs(actor);
		verify(actorRepo).findOne(88L);
		verify(actorRepo).delete(88L);
	}
}
