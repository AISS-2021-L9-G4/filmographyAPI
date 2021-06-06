package aiss.model.resources;

import static org.junit.Assert.*;
import java.util.Collection;

import org.jboss.resteasy.spi.BadRequestException;
import org.jboss.resteasy.spi.NotFoundException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.restlet.resource.ResourceException;

import aiss.model.Film;
import aiss.model.Actor;

public class ActorResourceTest {

	static Actor actor1, actor2, actor3;
	static ActorResource sr = new ActorResource();
	
	@BeforeClass
	public static void setup() throws Exception {
		
		// Test actor 1
		actor1 = sr.addActor(new Actor("Test name","Test nationality","0"));
		
		// Test actor 2
		actor2 = sr.addActor(new Actor("Test name 2","Test nationality 2","0"));
		
	}

	@AfterClass
	public static void tearDown() throws Exception {
		sr.deleteActor(actor1.getId());
		sr.deleteActor(actor3.getId());
	}
	
	@Test
	public void testGetAll() {
		Collection<Actor> actors = sr.getAll();
		
		assertNotNull("The collection of actors is null", actors);
		
		// Show result
		System.out.println("Listing all actors:");
		int i=1;
		for (Actor s : actors) {
			System.out.println("Actor " + i++ + " : " + s.getName() + " (ID=" + s.getId() + ")");
		}
	}

	@Test
	public void testGetActor() {
		Actor s = sr.getActor(actor1.getId());
		
		assertEquals("The id of the playlists do not match", actor1.getId(), s.getId());
		assertEquals("The name of the playlists do not match",actor1.getName(), s.getName());
		
		// Show result
		System.out.println("Actor id: " +  s.getId());
		System.out.println("Actor name: " +  s.getName());
	}

	@Test
	public void testAddActor() {
		
		String actorName = "Anthony Hopkings";
		String actorNationality = "United Kingdom";
		String actorAge = "83";
		
		actor3 = sr.addActor(new Actor(actorName,actorNationality,actorAge));
		
		assertNotNull("Error when adding the playlist", actor3);
		assertEquals("The actor's name has not been setted correctly", actorName,actor3.getName());
		assertEquals("The playlist's nationality has not been setted correctly", actorNationality, actor3.getNationality());
		assertEquals("The playlist's age has not been setted correctly", actorAge, actor3.getAge());

	}

	@Test
	public void testUpdateActor() {
		
		String actorName = "Jeff Bridges";
		String actorNationality = "United States";
		String actorAge = "71";
		
		// Update actor
		actor1.setName(actorName);
		actor1.setNationality(actorNationality);
		actor1.setAge(actorAge);
		
		boolean success = sr.updateActor(actor1);
		
		assertTrue("Error when updating the actor", success);
		
		Actor actor  = sr.getActor(actor1.getId());
		assertEquals("The actor's name has not been updated correctly", actorName, actor.getName());
		assertEquals("The actor's nationality has not been updated correctly", actorNationality, actor.getNationality());
		assertEquals("The actor's age has not been updated correctly", actorAge, actor.getAge());
	}

	@Test
	public void testDeleteActor() {
		
		sr.deleteActor(actor2.getId());
		
		Actor actor = sr.getActor(actor2.getId());
		assertNull("The actor has not been deleted correctly", actor);
	}
	
	@Test
	public void testDeleteActorErrorCode() {
		try {
			sr.deleteActor(actor2.getId());
		} catch (ResourceException e) {
			assertTrue(e.getStatus().toString().equals("Not Found (404) - The server has not found anything matching the request URI"));
		}
	}
	
	@Test
	public void testGetActorErrorCode() {
		try {
	    	sr.getActor(actor2.getId());
		} catch (ResourceException e) {
			assertTrue(e.getStatus().toString().equals("Not Found (404) - The server has not found anything matching the request URI"));
		}
	}
}
