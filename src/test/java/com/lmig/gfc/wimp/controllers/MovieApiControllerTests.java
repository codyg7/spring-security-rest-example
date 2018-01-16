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

import com.lmig.gfc.wimp.api.MovieApiController;
import com.lmig.gfc.wimp.models.Movie;
import com.lmig.gfc.wimp.models.MovieView;
import com.lmig.gfc.wimp.repositories.MovieRepository;

public class MovieApiControllerTests {
	
	private MovieApiController controller;
	@Mock
	private MovieRepository movieRepo;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		controller = new MovieApiController(movieRepo);
		 }
		 @Test
		 public void getAll_with_not_null_returns_list_of_movies() {
		 // Arrange
		 ArrayList<Movie> movies = new ArrayList<Movie>();
		 when(movieRepo.findAll()).thenReturn(movies);
		 // when (the method call used in the code being tested)
		 // .thenReturn(a value that you created)
		 // Act
		 List<Movie> actual = controller.getAll(null);
		 // Assert
		 assertThat(actual).hasSize(movies.size());
		 verify(movieRepo).findAll();
		 }
	

	@Test
	public void getOne_returns_an_actor_for_a_valid_id() {
		// Arrange
		Movie movie = new Movie();
		when(movieRepo.findOne(1L)).thenReturn(movie);
		// Act
		Movie actual = controller.getOne(1L);
		// Assert
		assertThat(actual).isSameAs(movie);
		verify(movieRepo).findOne(1L);
	}

	@Test
	public void create_saves_the_actor_and_returns_it() {
		// Arrange
		Movie movie = new Movie();
		when(movieRepo.save(movie)).thenReturn(movie);
		// Act
		Movie actual = controller.create(movie);
		// Assert
		assertThat(actual).isSameAs(movie);
		verify(movieRepo).save(movie);
	}
	
	@Test
	public void getOne_returns_an_actor_for_an_invalid_id() {
		 when(movieRepo.findOne(0L)).thenReturn(null);
		// Act
		 Movie actual = controller.getOne(0L);
		// Assert
		assertThat(actual).isNull();
		verify(movieRepo).findOne(0L);
	}
	@Test
	public void update_sets_id_of_actor_and_calls_saves_and_returns_actor() {
		//Arrange
		Movie actor = new Movie();
		when(movieRepo.save(actor)).thenReturn(actor);
		//Act
		Movie actual = controller.update(actor, 2233423L);
		//Assert
		assertThat(actual).isSameAs(actor);
		verify(movieRepo).save(actor);
		assertThat(actor.getId()).isEqualTo(2233423L);		
	}
	@Test
	public void delete_gets_the_actor_and_deletes_it_from_the_repo_and_returns_it() {
		//Arrange
		Movie actor = new Movie();
		when(movieRepo.findOne(8787L)).thenReturn(actor);
		//Act
		Movie actual = controller.delete(8787L);
		//Assert
		assertThat(actual).isSameAs(actor);
		verify(movieRepo).findOne(8787L);
		verify(movieRepo).delete(8787L);
	}
}


