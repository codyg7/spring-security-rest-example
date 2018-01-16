package com.lmig.gfc.wimp.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.lmig.gfc.wimp.repositories.ActorRepository;
import com.lmig.gfc.wimp.models.Actor;
import com.lmig.gfc.wimp.models.ActorView;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/actors")
public class ActorApiController {

	private ActorRepository actorRepository;

	public ActorApiController(ActorRepository actorRepository) {
		this.actorRepository = actorRepository;
	}

	@CrossOrigin(origins = "*")
	@GetMapping("")
//	public List<Actor> getAll(@RequestParam(required = false) String lastName) {
	public List<ActorView> getAll(){
		List<Actor> actors = actorRepository.findAll();
		ArrayList<ActorView> actorView = new ArrayList<ActorView>();
		for (Actor actor : actors) {
			actorView.add(new ActorView(actor));
		}
//		if (lastName != null) {
//			return actorRepository.findByLastNameIgnoringCase(lastName);
//		}
//		return actorRepository.findAll();
		return actorView;
	}

	@PostMapping("")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Actor create(@RequestBody Actor actor) {
		return actorRepository.save(actor);

	}

	@GetMapping("{id}")
	public Actor getOne(@PathVariable Long id) {
		return actorRepository.findOne(id);

	}

	@PutMapping("{id}")
	public Actor update(@RequestBody Actor actor, @PathVariable Long id) {
		actor.setId(id);
		return actorRepository.save(actor);

	}

	@DeleteMapping("{id}")
	public Actor delete(@PathVariable Long id) {
		// Get the actor from the "database" so I can return later
		Actor actor = actorRepository.findOne(id);
		// Delete the actor from the database (in this case just set the
		// value in the array list to null).
		actorRepository.delete(id);
		// Return the actor I just deleted.
		return actor;
	}
}
