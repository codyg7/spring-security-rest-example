package com.lmig.gfc.wimp.config;

import java.sql.Date;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.lmig.gfc.wimp.models.Movie;
import com.lmig.gfc.wimp.repositories.MovieRepository;
import com.lmig.gfc.wimp.models.Actor;
import com.lmig.gfc.wimp.models.Award;
import com.lmig.gfc.wimp.repositories.ActorRepository;
import com.lmig.gfc.wimp.repositories.AwardRepository;
import com.lmig.gfc.wimp.models.User;
import com.lmig.gfc.wimp.repositories.UserRepository;

@Configuration
public class SeedData {

	// Award award = awardRepository.save(new Award("Best Actor", "Academy Awards",
	// 2002));

	public SeedData(ActorRepository actorRepository, MovieRepository movieRepository, AwardRepository awardRepository, UserRepository userRepository, PasswordEncoder encoder) {
		actorRepository.save(new Actor("Michael", "Douglas", 1965L, new Date(1945, 02, 01)));
		actorRepository.save(new Actor("Marlon", "Brando", 1955L, new Date(1928, 07, 07)));
		actorRepository.save(new Actor("Robert", "DeNiro", 1971L, new Date(1947, 03, 21)));
		actorRepository.save(new Actor("Tom", "Siezmore", 1987L, new Date(1961, 01, 04)));
		actorRepository.save(new Actor("Sam", "Worthington", 2002L, new Date(1977, 03, 25)));
		movieRepository.save(new Movie("Godfather", new Date(1972, 04, 21), 3000000L, "MGM"));
		movieRepository.save(new Movie("Goodfellas", new Date(1990, 06, 06), 40000000L, "Universal"));
		movieRepository.save(new Movie("Ghostbusters", new Date(1984, 01, 10), 20000000L, "Columbia"));
		movieRepository.save(new Movie("Stipes", new Date(1981,03,02), 190000000L, "Universal"));
		movieRepository.save(new Movie("Tremors", new Date(1994,12,12), 1300000000L, "Fox 20th Century"));
		awardRepository.save(new Award("Best Actor", "Academy of Motion Picture Arts and Sciences", 1972));
		awardRepository.save(new Award("Best Actor - Motion Picture Drama", "Golden Globes", 1984));
		awardRepository.save(new Award("Best Actress", "Caanes Film Festival", 1990));
		awardRepository.save(new Award("Best Actor", "Academy of Motion Picture Arts and Sciences", 2002));
		awardRepository.save(new Award("Best Actor", "Academy of Motion Picture Arts and Sciences", 1993));

		//admin user
		User admin = userRepository.save(new User("admin", "user", "admin@admin.com", encoder.encode("password")));
	}
}
