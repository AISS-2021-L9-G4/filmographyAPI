package aiss.model.resources;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import aiss.model.Actor;
import aiss.model.Film;

public class FilmResourceTest {
	static Film film, film2, film3, film4;
	static Actor actor;
	static FilmResource fr = new FilmResource();
	static ActorResource ar = new ActorResource();
	
	@BeforeClass
	public static void setUp() throws Exception {
		
		film = fr.addFilm(new Film("Test list 1"));
		film2 = fr.addFilm(new Film("Test list 2"));
		film3 = fr.addFilm(new Film("Test list 3"));
		
	
		actor = ar.addActor(new Actor("Test title","Test artist","Test album","2016"));
		if(actor!=null)
			fr.addActor(film.getId(), actor.getId());
	}

	@AfterClass
	public static void tearDown() throws Exception {
		fr.deleteFilm(film.getId());
		fr.deleteFilm(film3.getId());
		fr.deleteFilm(film4.getId());
		if(actor!=null)
			ar.deleteActor(actor.getId());
	}

	@Test
	public void testGetAll() {
		Collection<Film> films = fr.getAll(); 
		
		assertNotNull("The collection of films is null", films);
		
		// Show result
		System.out.println("Listing all films:");
		int i=1;
		for (Film f : films) {
			System.out.println("Film " + i++ + " : " + f.getName() + " (ID=" + f.getId() + ")");
		}
		
	}

	@Test
	public void testGetFilm() {
		Film p = fr.getFilm(film.getId());
		
		assertEquals("The id of the films do not match", film.getId(), p.getId());
		assertEquals("The name of the films do not match", film.getName(), p.getName());
		
		// Show result
		System.out.println("Film id: " +  p.getId());
		System.out.println("Film name: " +  p.getName());

	}

	@Test
	public void testAddFilm() {
		String filmName = "Add film test title";
		String filmCategory = "Add film test category";
		Integer filmYear = 2021;
		
		film4 = fr.addFilm(new Film(filmName,filmCategory,filmYear));
		
		assertNotNull("Error when adding the film", film4);
		assertEquals("The film's name has not been setted correctly", filmName, film4.getName());
		assertEquals("The film's description has not been setted correctly", filmCategory, film4.getCategory());
		assertEquals("The film's year has not been setted correctly", filmYear, film4.getYear());
	}

	@Test
	public void testUpdateFilm() {
		String filmName = "Updated film name";

		// Update film
		film.setName(filmName);

		boolean success = fr.updateFilm(film);
		
		assertTrue("Error when updating the film", success);
		
		Film f  = fr.getFilm(film.getId());
		assertEquals("The film's name has not been updated correctly", filmName, f.getName());

	}

	@Test
	public void testDeleteFilm() {
		boolean success = fr.deleteFilm(film2.getId());
		assertTrue("Error when deleting the film", success);
		
		Film f = fr.getFilm(film2.getId());
		assertNull("The film has not been deleted correctly", f);
	}

	@Test
	public void testAddActor() {
		if(actor!=null) {
			boolean success = fr.addActor(film3.getId(), actor.getId());
			assertTrue("Error when adding the actor", success);
		}
	}

	@Test
	public void testRemoveActor() {
		boolean success = fr.removeActor(film.getId(), actor.getId());
		assertTrue("Error when removing an actor from a film", success);
		
	}
}


