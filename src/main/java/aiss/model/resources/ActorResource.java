package aiss.model.resources;

import java.util.Arrays;
import java.util.Collection;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import aiss.model.Song;

public class ActorResource {
	private String uri = "http://localhost:8090/api/songs";

	
	public Collection<Song> getAll() {
		ClientResource cr = null;
		Song [] songs = null;
		try {
			cr = new ClientResource(uri);
			songs = cr.get(Song[].class);
			
		} catch (ResourceException re) {
			System.err.println("Error when retrieving all songs: " + cr.getResponse().getStatus());
			throw re;
		}
		
		return Arrays.asList(songs);
	}
	

	public Song getSong(String songId) {
		ClientResource cr = null;
		Song song = null;
		try {
			cr = new ClientResource(uri + "/" + songId);
			song = cr.get(Song.class);
			
		} catch (ResourceException re) {
			System.err.println("Error when retrieving the song: " + cr.getResponse().getStatus());
		}
		
		return song;

	}
	

	public Song addSong(Song song) {
		ClientResource cr = null;
		Song resultSong = null;
		try {
			cr = new ClientResource(uri);
			cr.setEntityBuffering(true);		// Needed for using RESTlet from JUnit tests
			resultSong = cr.post(song,Song.class);
			
		} catch (ResourceException re) {
			System.err.println("Error when adding the song: " + cr.getResponse().getStatus());
		}
		
		return resultSong;
	}
	
	public boolean updateSong(Song song) {
		ClientResource cr = null;
		boolean success = true;
		try {
			cr = new ClientResource(uri);
			cr.setEntityBuffering(true);		// Needed for using RESTlet from JUnit tests
			cr.put(song);
			
			
		} catch (ResourceException re) {
			System.err.println("Error when updating the song: " + cr.getResponse().getStatus());
			success = false;
		}
		
		return success;
	}
	

	public boolean deleteSong(String songId) {
		ClientResource cr = null;
		boolean success = true;
		try {
			cr = new ClientResource(uri + "/" + songId);
			cr.setEntityBuffering(true);		// Needed for using RESTlet from JUnit tests
			cr.delete();
			
		} catch (ResourceException re) {
			System.err.println("Error when deleting the song: " + cr.getResponse().getStatus());
			success = false;
			throw re;
		}
		
		return success;
		
	}
}
