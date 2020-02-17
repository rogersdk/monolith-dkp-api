package br.com.baloonsproject.monolithdkp.services.impl;

import java.io.File;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.baloonsproject.monolithdkp.api.entities.Dkp;
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
		Event event = new Event();
		try {
			LOGGER.info(("Processing file: " + file.getName()));
			Document doc = Jsoup.parse(file, "UTF-8", file.toString());

			Elements currentDkpHistory = doc.select("#DKPHistory > div.divTable > div.divTableBody > div.divTableRow");

			Elements currentLootHistory = doc
					.select("#LootHistory > div.divTable > div.divTableBody > div.divTableRow");

			List<Dkp> historicoDkp = MonolithDkpFileParser.getDkpHistory(currentDkpHistory);
//			LOGGER.info("Dkp History Parsed: {}", historicoDkp);
			
			List<Loot> lootHistory = MonolithDkpFileParser.getLootHistory(currentLootHistory);
//			LOGGER.info("Loot parsed: {}", lootHistory);
			
			event.setFileName(file.getName());
			event.setDate(DateUtils.getDateFromFileName(file.getName()));
			
			LOGGER.info("File {} successfully parsed.", file.getName());
			return event;
		} catch (Exception e) {
			LOGGER.error("Error while parsing monolith file", e);
		}


		return event;
	}

	@Override
	public List<Dkp> parseDkpHistory(File file) {
		// TODO Auto-generated method stub
		return null;
	}

}
