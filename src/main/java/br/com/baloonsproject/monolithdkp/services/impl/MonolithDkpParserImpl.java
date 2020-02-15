package br.com.baloonsproject.monolithdkp.services.impl;

import java.io.File;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.baloonsproject.monolithdkp.api.entities.Event;
import br.com.baloonsproject.monolithdkp.api.entities.Loot;
import br.com.baloonsproject.monolithdkp.services.MonolithDkpParser;
import br.com.baloonsproject.monolithdkp.utils.DateUtils;
import br.com.baloonsproject.monolithdkp.utils.MonolithDkpFileParser;

@Service
public class MonolithDkpParserImpl implements MonolithDkpParser {

	private static final Logger LOGGER = LoggerFactory.getLogger(MonolithDkpParserImpl.class);

	public static void main(String[] args) {
		MonolithDkpParser parser = new MonolithDkpParserImpl();
		
		parser.parseMonolithDkpFile(new File("/home/users/rogerio.nobrega/projects/learn/github/MonolithLogParser/data/DKP_CORE_10022020.HTML"));
	}

	@Override
	public Event parseMonolithDkpFile(File file) {
		try {
			Event event = new Event();
			LOGGER.info(("Processing file: " + file.getName()));
			Document doc = Jsoup.parse(file, "UTF-8", file.toString());

			Elements currentDkpElements = doc.select("#DKP > div.divTable > div.divTableBody > div.divTableRow");

			Elements currentDkpHistory = doc.select("#DKPHistory > div.divTable > div.divTableBody > div.divTableRow");

			Elements currentLootHistory = doc
					.select("#LootHistory > div.divTable > div.divTableBody > div.divTableRow");

//			List<Dkp> dkpAtualizado = MonolithDkpFileParser.getUpdatedDkp(currentDkpElements);

//			List<Dkp> historicoDkp = MonolithDkpFileParser.getDkpHistory(currentDkpHistory);

			List<Loot> lootHistory = MonolithDkpFileParser.getLootHistory(currentLootHistory);
			LOGGER.info(String.format("Loot parsed: %s", lootHistory));
			
			event.setFileName(file.getName());
			event.setDate(DateUtils.getDateFromFileName(file.getName()));
			
			return event;
		} catch (Exception e) {
			LOGGER.error("Error while parsing monolith file", e);
		}

		Event event = new Event();

		return event;
	}

}
