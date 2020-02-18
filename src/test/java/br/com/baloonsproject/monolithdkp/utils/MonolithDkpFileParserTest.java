package br.com.baloonsproject.monolithdkp.utils;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.baloonsproject.monolithdkp.api.entities.Dkp;
import br.com.baloonsproject.monolithdkp.api.entities.Loot;

public class MonolithDkpFileParserTest {

	private Date defaultDate;

	private Elements lootHistoryElements;

	private Elements dkpHistoryElements;

	private File lootHistoryFile;

	private File dkpHistoryFile;

	@Before
	public void setUp() throws IOException {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.YEAR, 2020);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 0);
		defaultDate = cal.getTime();

		String lootHistoryResourceName = "html-source-data/loot_history.html";
		String dkpHistoryResourceName = "html-source-data/dkp_history.html";

		ClassLoader classLoader = getClass().getClassLoader();
		lootHistoryFile = new File(classLoader.getResource(lootHistoryResourceName).getFile());

		dkpHistoryFile = new File(classLoader.getResource(dkpHistoryResourceName).getFile());

		Document doc = Jsoup.parse(lootHistoryFile, "UTF-8", lootHistoryFile.toString());
		lootHistoryElements = doc.select("#LootHistory > div.divTable > div.divTableBody > div.divTableRow");

		doc = Jsoup.parse(dkpHistoryFile, "UTF-8", dkpHistoryFile.toString());
		dkpHistoryElements = doc.select("#DKPHistory > div.divTable > div.divTableBody > div.divTableRow");
	}

	@Test(expected = IllegalArgumentException.class)
	public void getLootHistoryElementNullTest() {
		MonolithDkpFileParser.getLootHistory(null);
	}

	@Test
	public void getLootHistoryTest() {
		List<Loot> lootHistory = MonolithDkpFileParser.getLootHistory(lootHistoryElements);

		Assert.assertEquals(1, lootHistory.size());

		Loot l = lootHistory.get(0);

		Assert.assertEquals(0, defaultDate.compareTo(l.getDate()));
		Assert.assertEquals("PlayerOne", l.getDkp().getPlayer().getNickname());
		Assert.assertEquals(-24, l.getDkp().getValue().intValue());
		Assert.assertEquals(99999, l.getWowheadItemId().intValue());
		Assert.assertEquals("Onyxia's Lair: Onyxia", l.getFrom().getName());
	}

	@Test
	public void getDkpHistoryTest() {
		List<Dkp> dkpHistory = MonolithDkpFileParser.getDkpHistory(dkpHistoryElements);

		Assert.assertEquals(6, dkpHistory.size());
		
		List<Dkp> playerOneDkpHistoryNormal = dkpHistory.stream()
				.filter(dkp -> dkp.getPlayer().getNickname().equals("PlayerOne"))
				.filter(dkp -> dkp.getDescription().equals("Molten Core: Majordomo Executus")).collect(Collectors.toList());
		
		List<Dkp> playerOneDkpHistoryWeeklyDecay = dkpHistory.stream()
				.filter(dkp -> dkp.getPlayer().getNickname().equals("PlayerOne"))
				.filter(dkp -> dkp.getDescription().equals("Weekly Decay")).collect(Collectors.toList());
		
		Assert.assertEquals(1,playerOneDkpHistoryNormal.size());
		Assert.assertEquals(1,playerOneDkpHistoryWeeklyDecay.size());
		
		Assert.assertEquals(5, playerOneDkpHistoryNormal.get(0).getValue().intValue());
		Assert.assertEquals(-57, playerOneDkpHistoryWeeklyDecay.get(0).getValue().intValue());
	}

}
