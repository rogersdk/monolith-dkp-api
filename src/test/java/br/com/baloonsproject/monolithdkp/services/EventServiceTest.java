package br.com.baloonsproject.monolithdkp.services;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.baloonsproject.monolithdkp.api.entities.Event;
import br.com.baloonsproject.monolithdkp.repositories.EventRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class EventServiceTest {

	@MockBean
	private EventRepository repository;

	@Autowired
	private EventService service;

	@Before
	public void setUp() throws Exception {
		Event e = new Event();
		e.setId(1l);
		e.setFileName("FileName");
		BDDMockito.given(repository.save(Mockito.any(Event.class))).willReturn(e);
		BDDMockito.given(repository.findByFileName(e.getFileName())).willReturn(e);
	}

	@Test
	public void saveTest() {
		Optional<Event> e = service.save(new Event());

		Assert.assertTrue("Event does not exists", e.isPresent());
		Assert.assertEquals(1, e.get().getId().intValue());
	}

	@Test
	public void findByFileNameTest() {
		Optional<Event> e = service.findByFileName("FileName");

		Assert.assertTrue("Event does not exists", e.isPresent());
		Assert.assertEquals(1, e.get().getId().intValue());
	}
}
