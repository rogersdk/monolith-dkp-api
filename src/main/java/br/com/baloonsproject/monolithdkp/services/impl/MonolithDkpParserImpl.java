package br.com.baloonsproject.monolithdkp.services.impl;

import java.io.File;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.baloonsproject.monolithdkp.api.entities.Event;
import br.com.baloonsproject.monolithdkp.api.entities.Loot;
import br.com.baloonsproject.monolithdkp.services.MonolithDkpParser;
import br.com.baloonsproject.monolithdkp.utils.MonolithDkpFileParser;

public class MonolithDkpParserImpl implements MonolithDkpParser {

	private static final Logger LOGGER = LoggerFactory.getLogger(MonolithDkpParserImpl.class);

	public static void main(String[] args) {
		MonolithDkpParser parser = new MonolithDkpParserImpl();
		
		parser.parseMonolithDkpFile(new File("/home/users/rogerio.nobrega/projects/learn/github/MonolithLogParser/data/DKP_CORE_10022020.HTML"));
	}

	@Override
	public Event parseMonolithDkpFile(File file) {
		File input = new File(file.toString());
		try {
			LOGGER.info(("Processing file: " + file.getName()));
			Document doc = Jsoup.parse(input, "UTF-8", file.toString());

			Elements currentDkpElements = doc.select("#DKP > div.divTable > div.divTableBody > div.divTableRow");

			Elements currentDkpHistory = doc.select("#DKPHistory > div.divTable > div.divTableBody > div.divTableRow");

			Elements currentLootHistory = doc
					.select("#LootHistory > div.divTable > div.divTableBody > div.divTableRow");

//			List<Dkp> dkpAtualizado = MonolithDkpFileParser.getUpdatedDkp(currentDkpElements);

//			List<Dkp> historicoDkp = MonolithDkpFileParser.getDkpHistory(currentDkpHistory);

			List<Loot> lootHistory = MonolithDkpFileParser.getLootHistory(currentLootHistory);
			LOGGER.info(String.format("Loot parsed: %s", lootHistory));
		} catch (Exception e) {
			LOGGER.error("Error while parsing monolith file", e);
		}

		Event event = new Event();

		return event;
	}

}
