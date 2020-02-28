package br.com.baloonsproject.monolithdkp.services.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.baloonsproject.monolithdkp.api.entities.Dkp;
import br.com.baloonsproject.monolithdkp.api.entities.Event;
import br.com.baloonsproject.monolithdkp.api.entities.Loot;
import br.com.baloonsproject.monolithdkp.api.entities.Player;
import br.com.baloonsproject.monolithdkp.services.MonolithDkpParser;
import br.com.baloonsproject.monolithdkp.utils.DateUtils;
import br.com.baloonsproject.monolithdkp.utils.MonolithDkpFileParser;

@Service
public class MonolithDkpParserImpl implements MonolithDkpParser {

	private static final Logger LOGGER = LoggerFactory.getLogger(MonolithDkpParserImpl.class);

	public static void main(String[] args) {
		MonolithDkpParser parser = new MonolithDkpParserImpl();

		parser.parseMonolithDkpFile(new File(
				"/home/users/rogerio.nobrega/projects/learn/github/MonolithLogParser/data/DKP_CORE_10022020.HTML"));
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

			List<Loot> lootHistory = MonolithDkpFileParser.getLootHistory(currentLootHistory);

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
	public List<Dkp> parseDkpHistory(File file) throws IOException {
		LOGGER.info(("Processing Dkp history from file: " + file.getName()));
		Document doc = Jsoup.parse(file, "UTF-8", file.toString());
		Elements currentDkpHistory = doc.select("#DKPHistory > div.divTable > div.divTableBody > div.divTableRow");
		LOGGER.info("File {} successfully parsed.", file.getName());
		List<Dkp> dkps = MonolithDkpFileParser.getDkpHistory(currentDkpHistory);
		LOGGER.info("All dkps has been processed. File {} successfully parsed.", file.getName());
		return dkps;
	}

	@Override
	public List<Loot> parseLootHistory(File file) throws IOException {
		LOGGER.info(("Processing Loot history from file: " + file.getName()));
		Document doc = Jsoup.parse(file, "UTF-8", file.toString());
		Elements currentLootHistory = doc.select("#LootHistory > div.divTable > div.divTableBody > div.divTableRow");
		List<Loot> loots = MonolithDkpFileParser.getLootHistory(currentLootHistory);
		LOGGER.info("All loots has been processed. File {} successfully parsed.", file.getName());
		return loots;
	}

	@Override
	public List<Player> parsePlayers(File file) throws IOException {
		LOGGER.info(("Processing Players from file: " + file.getName()));
		Document doc = Jsoup.parse(file, "UTF-8", file.toString());
		Elements currentLootHistory = doc.select("#DKP > div.divTable > div.divTableBody > div.divTableRow");
		List<Player> players = MonolithDkpFileParser.getPlayers(currentLootHistory);
		LOGGER.info("All players has been processed. File {} successfully parsed.", file.getName());
		return players;
	}

	@Override
	public List<Dkp> parseUpdatedDkpCheck(File file, List<Dkp> dkpsFromHistoryTable) throws IOException {
		LOGGER.info(("Processing Dkp history from file: " + file.getName()));
		Document doc = Jsoup.parse(file, "UTF-8", file.toString());
		Elements updatedDkp = doc.select("#DKP > div.divTable > div.divTableBody > div.divTableRow");
		LOGGER.info("File {} successfully parsed.", file.getName());
		List<Dkp> dkps = MonolithDkpFileParser.getUpdatedDkp(updatedDkp);
		LOGGER.info("All dkps has been processed. File {} successfully parsed.", file.getName());
		return dkps;
	}

}
