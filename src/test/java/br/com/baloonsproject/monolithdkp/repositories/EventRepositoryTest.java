package br.com.baloonsproject.monolithdkp.repositories;

import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.baloonsproject.monolithdkp.api.entities.Event;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class EventRepositoryTest {
	
	@Autowired
	private EventRepository repository;
	
	@Before
	public void setUp() throws Exception {
		Event e = new Event();
		e.setDate(new Date());
		e.setFileName("FileName");
		e.setName("Name");
		e.setPlayers(new ArrayList<>());
		
		repository.save(e);
	}
	
	@After
	public final void tearDown() {
		repository.deleteAll();
	}
	
	@Test
	public void findByFileNameTest() {
		String fileName = "FileName";
		Event e = repository.findByFileName(fileName);
		Assert.assertEquals(fileName, e.getFileName());
	}
}
