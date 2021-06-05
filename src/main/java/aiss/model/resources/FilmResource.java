package aiss.model.resources;

import java.util.Arrays;
import java.util.Collection;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import aiss.model.Film;

public class FilmResource {
	private String uri = "https://proyecto-curso-313416.nw.r.appspot.com/";
	//private String uri = "http://localhost:8095/api/lists";
	

	public Collection<Film> getAll() {
		
		ClientResource cr = null;
		Film [] lists = null;
		try {
			cr = new ClientResource(uri);
			lists = cr.get(Film[].class);
			
		} catch (ResourceException re) {
			System.err.println("Error when retrieving the collections of films: " + cr.getResponse().getStatus());
		}
		
		return Arrays.asList(lists);
	}
	
	
	public Film getFilm(String filmId) {
		
		ClientResource cr = null;
		Film list = null;
		try {
			cr = new ClientResource(uri + "/" + filmId);
			list = cr.get(Film.class);
			
		} catch (ResourceException re) {
			System.err.println("Error when retrieving the film: " + cr.getResponse().getStatus());
		}
		
		return list;

	}
	

	public Film addFilm(Film f) {
		
		ClientResource cr = null;
		Film resultFilm = null;
		try {
			cr = new ClientResource(uri);
			cr.setEntityBuffering(true);		// Needed for using RESTlet from JUnit tests
			resultFilm = cr.post(f,Film.class);
			
		} catch (ResourceException re) {
			System.err.println("Error when adding the film: " + cr.getResponse().getStatus());
		}
		
		return resultFilm;
	}
	

	public boolean updateFilm(Film pl) {
		ClientResource cr = null;
		boolean success = true;
		try {
			cr = new ClientResource(uri);
			cr.setEntityBuffering(true);		// Needed for using RESTlet from JUnit tests
			cr.put(pl);
			
			
		} catch (ResourceException re) {
			System.err.println("Error when updating the film: " + cr.getResponse().getStatus());
			success = false;
		}
		
		return success;
	}
	
	
	public boolean deleteFilm(String filmId) {
		ClientResource cr = null;
		boolean success = true;
		try {
			cr = new ClientResource(uri + "/" + filmId);
			cr.setEntityBuffering(true);		// Needed for using RESTlet from JUnit tests
			cr.delete();
			
		} catch (ResourceException re) {
			System.err.println("Error when deleting the film: " + cr.getResponse().getStatus());
			success = false;
		}
		
		return success;
	}
	
	public boolean addActor(String filmId, String actorId) {
		ClientResource cr = null;
		boolean resultFilm = true;
		try {
			cr = new ClientResource(uri + "/" + filmId + "/" + actorId);
			cr.setEntityBuffering(true);		// Needed for using RESTlet from JUnit tests
			cr.post(" ");
			
		} catch (ResourceException re) {
			System.err.println("Error when adding the actor with id=" + actorId + "to the film with id=" + filmId + ": " + cr.getResponse().getStatus());
		}
		
		return resultFilm;
	}
	
	public boolean removeActor(String filmId, String actorId) {
		ClientResource cr = null;
		boolean success = true;
		try {
			cr = new ClientResource(uri + "/" + filmId + "/" + actorId);
			cr.setEntityBuffering(true);		// Needed for using RESTlet from JUnit tests
			cr.delete();
			
		} catch (ResourceException re) {
			System.err.println("Error when deleting the actor with id=" + actorId + "to the film with id=" + filmId + ": " + cr.getResponse().getStatus());
			success = false;
		}
		
		return success;
	}
}

