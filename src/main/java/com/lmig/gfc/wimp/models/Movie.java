package com.lmig.gfc.wimp.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
//@Table(name = "movies")
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(length = 300, nullable = false)
	private String title;
	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	@JsonFormat(timezone="EST")
	private Date releaseDate;
	@Column(nullable = true)
	private Long budget;
	@Column(length = 500, nullable = false)
	private String distributor;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Actor> actors;

	public Movie() {
	}

	public Movie(String title, Date releaseDate, Long budget, String distributor) {

		this.title = title;
		this.releaseDate = releaseDate;
		this.budget = budget;
		this.distributor = distributor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Long getBudget() {
		return budget;
	}

	public void setBudget(Long budget) {
		this.budget = budget;
	}

	public String getDistributor() {
		return distributor;
	}

	public void setDistributor(String distributor) {
		this.distributor = distributor;
	}

}
