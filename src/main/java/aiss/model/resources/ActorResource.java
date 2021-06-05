package aiss.model.resources;

import java.util.Arrays;
import java.util.Collection;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import aiss.model.Actor;

public class ActorResource {
	private String uri = "https://proyecto-curso-313416.nw.r.appspot.com/v1/actors";

	
	public Collection<Actor> getAll() {
		ClientResource cr = null;
		Actor [] actors = null;
		try {
			cr = new ClientResource(uri);
			actors = cr.get(Actor[].class);
			
		} catch (ResourceException re) {
			System.err.println("Error when retrieving all actors: " + cr.getResponse().getStatus());
			throw re;
		}
		
		return Arrays.asList(actors);
	}
	

	public Actor getActor(String actorId) {
		ClientResource cr = null;
		Actor actor = null;
		try {
			cr = new ClientResource(uri + "/" + actorId);
			actor = cr.get(Actor.class);
			
		} catch (ResourceException re) {
			System.err.println("Error when retrieving the actor: " + cr.getResponse().getStatus());
		}
		
		return actor;

	}
	

	public Actor addActor(Actor actor) {
		ClientResource cr = null;
		Actor resultActor = null;
		try {
			cr = new ClientResource(uri);
			cr.setEntityBuffering(true);
			resultActor = cr.post(actor,Actor.class);
			
		} catch (ResourceException re) {
			System.err.println("Error when adding the actor: " + cr.getResponse().getStatus());
		}
		
		return resultActor;
	}
	
	public boolean updateActor(Actor actor) {
		ClientResource cr = null;
		boolean success = true;
		try {
			cr = new ClientResource(uri);
			cr.setEntityBuffering(true);
			cr.put(actor);
			
			
		} catch (ResourceException re) {
			System.err.println("Error when updating the actor: " + cr.getResponse().getStatus());
			success = false;
		}
		
		return success;
	}
	

	public boolean deleteActor(String actorId) {
		ClientResource cr = null;
		boolean success = true;
		try {
			cr = new ClientResource(uri + "/" + actorId);
			cr.setEntityBuffering(true);
			cr.delete();
			
		} catch (ResourceException re) {
			System.err.println("Error when deleting the actor: " + cr.getResponse().getStatus());
			success = false;
			throw re;
		}
		
		return success;
		
	}
}
