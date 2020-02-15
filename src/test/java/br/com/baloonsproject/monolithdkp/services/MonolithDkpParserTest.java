package br.com.baloonsproject.monolithdkp.services;

import java.io.File;
import java.util.Calendar;

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
public class MonolithDkpParserTest {

	@Autowired
	private MonolithDkpParser parser;

	private File monolithFile;

	@Before
	public void setUp() {
		String resourceName = "monolith-files/DKP_CORE_10022020.HTML";

		ClassLoader classLoader = getClass().getClassLoader();
		monolithFile = new File(classLoader.getResource(resourceName).getFile());

	}

	@Test
	public void eventParseMonolithDkpFileTest() {
		Event e = parser.parseMonolithDkpFile(monolithFile);
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 10);
		cal.set(Calendar.MONTH, 01);
		cal.set(Calendar.YEAR, 2020);
		
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		
		Assert.assertEquals("DKP_CORE_10022020.HTML", e.getFileName());
		Assert.assertEquals(cal.getTime(), e.getDate());
	}
	
}
