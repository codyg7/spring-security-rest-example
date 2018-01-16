package com.lmig.gfc.wimp.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.lmig.gfc.wimp.api.MovieActorApiController;
import com.lmig.gfc.wimp.models.Actor;
import com.lmig.gfc.wimp.models.Movie;
import com.lmig.gfc.wimp.repositories.ActorRepository;
import com.lmig.gfc.wimp.repositories.MovieRepository;

public class MovieActorApiControllerTests {

	private MovieActorApiController controller;
	@Mock
	private MovieRepository movieRepo;
	@Mock
	private ActorRepository actorRepo;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		controller = new MovieActorApiController(actorRepo, movieRepo);
	}

	@Test
	public void create_saves_movie_is_associated_and_saved_with_actor_when_encountered() {

		// Arrange
		Movie movie = new Movie();
		movie.setActors(new ArrayList<Actor>());
		Actor actor = new Actor();
		when(movieRepo.findOne(888L)).thenReturn(movie);
		when(actorRepo.findOne(567L)).thenReturn(actor);
		// Act
		Movie actual = controller.create(888L, 567L);
		// Assert
		assertThat(actual).isSameAs(movie);
		verify(movieRepo).save(movie);
		assertThat(movie.getActors()).contains(actor);
		verify(movieRepo).findOne(888L);
		verify(actorRepo).findOne(567L);
	}
}
