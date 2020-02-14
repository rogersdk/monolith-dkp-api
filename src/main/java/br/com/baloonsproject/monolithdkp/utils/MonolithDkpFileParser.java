package br.com.baloonsproject.monolithdkp.utils;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.baloonsproject.monolithdkp.api.entities.Dkp;
import br.com.baloonsproject.monolithdkp.api.entities.Loot;
import br.com.baloonsproject.monolithdkp.api.entities.Mob;
import br.com.baloonsproject.monolithdkp.api.entities.Player;
import br.com.baloonsproject.monolithdkp.api.enums.ClassTypeEnum;

public class MonolithDkpFileParser {

	private static final Logger LOGGER = LoggerFactory.getLogger(MonolithDkpFileParser.class);
	/*
	 * private static void parseFiles() throws IOException { try (Stream<Path> paths
	 * = Files.walk(Paths.get("./data"))) {
	 * paths.filter(Files::isRegularFile).filter( f ->
	 * f.getFileName().toString().substring(f.getFileName().toString().length() -
	 * 4).equals("HTML")) .forEach(f -> { File input = new File(f.toString()); try {
	 * LOGGER.info(("Processing file: " + f.getFileName().toString())); Document doc
	 * = Jsoup.parse(input, "UTF-8", f.getFileName().toString()); Elements
	 * currentDkpElements = doc
	 * .select("#DKP > div.divTable > div.divTableBody > div.divTableRow");
	 * 
	 * Elements currentDkpHistory = doc
	 * .select("#DKPHistory > div.divTable > div.divTableBody > div.divTableRow");
	 * 
	 * Elements currentLootHistory = doc
	 * .select("#LootHistory > div.divTable > div.divTableBody > div.divTableRow");
	 * 
	 * Date fileDate = getDateFromFilePath(f);
	 * 
	 * List<Dkp> dkpAtualizado = getUpdatedDkp(currentDkpElements, fileDate);
	 * 
	 * List<Dkp> historicoDkp = getDkpHistory(currentDkpHistory, fileDate);
	 * 
	 * List<Loot> lootHistory = getLootHistory(currentLootHistory, fileDate);
	 * 
	 * String filePath = "./data/json/" +
	 * f.getFileName().toString().replaceAll("[\\.HTML]", "") + ".json";
	 * 
	 * Gson gson = new Gson();
	 * 
	 * String toJsonString = gson.toJson(dkpAtualizado);
	 * 
	 * } catch (IOException e) { e.printStackTrace(); } }); } }
	 */

	public static List<Loot> getLootHistory(Elements currentLootHistory) {
		List<Loot> list = new ArrayList<>();

		for (Element element : currentLootHistory) {
			List<String> dkpValues = new ArrayList<>();
			String dropMobName = "";
			Date dropDate = null;
			String wowHeadItemId = "";

			Elements lootDkpSpentElement = element.getElementsByClass("divPlayer");
			Elements lootPlayer = element.getElementsByClass("divClass");
			Elements itemUrlElements = element.select("a");

			if (!itemUrlElements.isEmpty()) {
				wowHeadItemId = itemUrlElements.attr("data-wowhead").replaceAll("item=", "");
			}

			if (!lootDkpSpentElement.isEmpty()) {
				Loot playerLoot = new Loot();
				Player player = new Player();
				Dkp dkp = new Dkp();
				Mob mobLoot = new Mob();

				String dkpSpentLoot = lootDkpSpentElement.text().trim().replaceAll("[\\(\\)(DKP)\\s]", "");

				Elements dkpReason = element.getElementsByClass("divDKP");

				String dkpReasonReplaced = dkpReason.html();

				List<String> dkpReasonValores = Arrays.asList(dkpReasonReplaced.split("<br>"));

				if (dkpReasonValores.size() > 1) {
					dropMobName = dkpReasonValores.get(0).trim();
					String dateStr = dkpReasonValores.get(1);

					dateStr = dateStr.trim().replaceAll("[\\(\\)]", "");

					dropDate = DateUtils.parseDateStringToDate(dateStr);
					playerLoot.setFrom(mobLoot);
					playerLoot.setDate(dropDate);
					playerLoot.setWowHeadItemId(Integer.valueOf(wowHeadItemId));
				}

				// Setup dkp values
				dkp.setDate(dropDate);
				dkp.setDescription(String.format("Loot %d from %s", playerLoot.getWowHeadItemId(),
						playerLoot.getFrom().getName()));
//				dkp.setEvent(event);
				dkp.setPlayer(player);
				dkp.setValue(Integer.valueOf(dkpSpentLoot));

				// Setup mob values
				mobLoot.setName(dropMobName);

				// Setup player values
				player.setNickname(lootPlayer.text());
				player.setClassType(ClassTypeEnum.NONE);

				// Setup setters
				playerLoot.setDkp(dkp);
				
				list.add(playerLoot);
			}

		}

		return list;
	}
	/*
	 * public static List<Dkp> getDkpHistory(Elements currentDkpHistory) { List<Dkp>
	 * dkpList = new ArrayList<>(); for (Element element : currentDkpHistory) {
	 * List<String> dkpValues = new ArrayList<>(); String reasonStr = ""; Date
	 * dataHistorico = null;
	 * 
	 * Elements playerNameElement = element.getElementsByClass("divPlayer");
	 * 
	 * if (!playerNameElement.text().isEmpty()) { Elements dkpValueReason =
	 * element.getElementsByClass("divClass"); Elements dkpReason =
	 * element.getElementsByClass("divDKP");
	 * 
	 * String dkpReasonReplaced = dkpReason.html();
	 * 
	 * List<String> dkpReasonValores =
	 * Arrays.asList(dkpReasonReplaced.split("<br>"));
	 * 
	 * if (dkpReasonValores.size() > 1) { reasonStr =
	 * dkpReasonValores.get(0).trim(); String dateStr = dkpReasonValores.get(1);
	 * 
	 * dateStr = dateStr.trim().replaceAll("[\\(\\)]", "");
	 * 
	 * dataHistorico = parseDateStringToDate(dateStr); }
	 * 
	 * if (!dkpValueReason.text().isEmpty()) { List<String> dkpValuesString =
	 * Arrays.asList(dkpValueReason.text().split(","));
	 * 
	 * if (!dkpValuesString.isEmpty() && dkpValuesString.size() > 0) { for (String
	 * dvr : dkpValuesString) { dkpValues.add(dvr); } } else {
	 * dkpValues.add(dkpValueReason.text()); } }
	 * 
	 * String playerNamesHtml = playerNameElement.text(); if
	 * (!playerNamesHtml.isEmpty()) { List<String> playerNames =
	 * Arrays.asList(playerNamesHtml.split(","));
	 * 
	 * int dkpValueIndex = 0; for (String playerName : playerNames) { Player p = new
	 * Player(playerName, "");
	 * 
	 * Dkp dkp = new Dkp(p, dkpValues.get(dkpValueIndex)); dkp.setReason(reasonStr);
	 * dkp.setData(dataHistorico); dkpList.add(dkp); if (dkpValues.size() > 1) {
	 * dkpValueIndex++; }
	 * 
	 * } } }
	 * 
	 * } return dkpList; }
	 */

	/*
	 * public static List<Dkp> getUpdatedDkp(Elements currentDkpElements) {
	 * List<Dkp> dkpAtualizado = new ArrayList<>();
	 * 
	 * for (Element dkpPlayerElement : currentDkpElements) { Elements
	 * playerNameElement = dkpPlayerElement.getElementsByClass("divPlayer");
	 * Elements playerClassType = dkpPlayerElement.getElementsByClass("divClass");
	 * Elements playerDKP = dkpPlayerElement.getElementsByClass("divDKP");
	 * 
	 * if (!playerNameElement.text().isEmpty() &&
	 * !playerClassType.select("img").attr("src").isEmpty()) {
	 * 
	 * PlayerClassType classType =
	 * PlayerClassType.obterPorIcon(playerClassType.select("img").attr("src"));
	 * 
	 * Player p = new Player(playerNameElement.text(), classType.getClassType());
	 * 
	 * Dkp dkp = new Dkp(p, playerDKP.text()); dkp.setReason("DKP Atualizado");
	 * dkp.setData(fileDate);
	 * 
	 * dkpAtualizado.add(dkp); } }
	 * 
	 * return dkpAtualizado; }
	 */

}
