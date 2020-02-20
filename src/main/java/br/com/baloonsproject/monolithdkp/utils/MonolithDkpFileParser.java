package br.com.baloonsproject.monolithdkp.utils;

import java.util.ArrayList;
import java.util.Arrays;
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

	public static List<Loot> getLootHistory(Elements currentLootHistory) {

		if (currentLootHistory == null) {
			throw new IllegalArgumentException("Html element can't be null");
		}

		List<Loot> list = new ArrayList<>();

		LOGGER.info("Parsing loot History...");
		for (Element element : currentLootHistory) {
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
					playerLoot.setWowheadItemId(Integer.valueOf(wowHeadItemId));
				}

				// Setup mob values
				mobLoot.setName(dropMobName);
				
				// Setup dkp values
				dkp.setDate(dropDate);
				dkp.setDescription(String.format("Loot %d from %s", playerLoot.getWowheadItemId(),
						playerLoot.getFrom().getName()));
				dkp.setPlayer(player);
				dkp.setValue(Integer.valueOf(dkpSpentLoot));
				
				// Setup player values
				player.setNickname(lootPlayer.text());
				player.setClassType(ClassTypeEnum.NONE);

				// Setup setters
				playerLoot.setDkp(dkp);

				LOGGER.info("Found a loot registry as {}", playerLoot);

				list.add(playerLoot);
			}

		}

		LOGGER.info("Parse of Loot History ended found {} entries.", list.size());

		return list;
	}

	public static List<Dkp> getDkpHistory(Elements currentDkpHistory) {
		List<Dkp> dkpList = new ArrayList<>();
		LOGGER.info("Parsing DKP History...");
		for (Element element : currentDkpHistory) {
			List<String> dkpValues = new ArrayList<>();
			String reasonStr = "";
			Date dataHistorico = null;

			Elements playerNameElement = element.getElementsByClass("divPlayer");

			if (!playerNameElement.text().isEmpty()) {
				Elements dkpValueReason = element.getElementsByClass("divClass");
				Elements dkpReason = element.getElementsByClass("divDKP");

				String dkpReasonReplaced = dkpReason.html();

				List<String> dkpReasonValores = Arrays.asList(dkpReasonReplaced.split("<br>"));

				if (dkpReasonValores.size() > 1) {
					reasonStr = dkpReasonValores.get(0).trim();
					String dateStr = dkpReasonValores.get(1);

					dateStr = dateStr.trim().replaceAll("[\\(\\)]", "");

					dataHistorico = DateUtils.parseDateStringToDate(dateStr);
				}

				if (!dkpValueReason.text().isEmpty()) {
					List<String> dkpValuesString = Arrays.asList(dkpValueReason.text().split(","));

					if (!dkpValuesString.isEmpty()) {
						for (String dvr : dkpValuesString) {
							dkpValues.add(dvr);
						}
					} else {
						dkpValues.add(dkpValueReason.text());
					}
				}

				String playerNamesHtml = playerNameElement.text();
				if (!playerNamesHtml.isEmpty()) {
					List<String> playerNames = Arrays.asList(playerNamesHtml.split(","));

					int dkpValueIndex = 0;
					for (String playerName : playerNames) {
						Player p = new Player();
						p.setNickname(playerName.trim());
						p.setClassType(ClassTypeEnum.getByString(""));

						Dkp dkp = new Dkp();
						dkp.setDate(dataHistorico);
						dkp.setDescription(reasonStr);

						dkp.setPlayer(p);
						dkp.setValue(Integer.valueOf(dkpValues.get(dkpValueIndex)));

						dkpList.add(dkp);

						if (dkpValues.size() > 1) {
							dkpValueIndex++;
						}

						LOGGER.info("Parse of DKP History found {}", dkp);
					}
				}
			}

		}
		LOGGER.info("Parse of DKP History ended found {} entries.", dkpList.size());
		return dkpList;
	}

	public static List<Player> getPlayers(Elements currentDkpElements) {
		List<Player> players = new ArrayList<>();

		for (Element dkpPlayerElement : currentDkpElements) {
			Elements playerNameElement = dkpPlayerElement.getElementsByClass("divPlayer");
			Elements playerClassType = dkpPlayerElement.getElementsByClass("divClass");
//			Elements playerDKP = dkpPlayerElement.getElementsByClass("divDKP");

			if (!playerNameElement.text().isEmpty() && !playerClassType.select("img").attr("src").isEmpty()) {

				MonolithWowZamimgClassTypeEnum monolithWowZamimgClassTypeEnum = MonolithWowZamimgClassTypeEnum
						.getByIcon(playerClassType.select("img").attr("src"));

				Player p = new Player();
				p.setNickname(playerNameElement.text().trim());
				p.setClassType(ClassTypeEnum.getByString(monolithWowZamimgClassTypeEnum.getClassType()));

				players.add(p);
			}
		}

		return players;
	}

}
