package com.lmig.gfc.wimp.models;

public class MovieView {
	private Movie movie;

	public MovieView(Movie movie) {
		this.movie = movie;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public String getTitle() {
		return movie.getTitle();
	}
	public String getDistributor() {
		return movie.getDistributor();
	}
}
