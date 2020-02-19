package br.com.baloonsproject.monolithdkp.services;

import java.io.File;
import java.io.IOException;
import java.util.List;

import br.com.baloonsproject.monolithdkp.api.entities.Dkp;
import br.com.baloonsproject.monolithdkp.api.entities.Event;
import br.com.baloonsproject.monolithdkp.api.entities.Loot;
import br.com.baloonsproject.monolithdkp.api.entities.Player;

public interface MonolithDkpParser {

	Event parseMonolithDkpFile(File file);

	List<Dkp> parseDkpHistory(File file) throws IOException;

	List<Loot> parseLootHistory(File file) throws IOException;

	List<Player> parsePlayers(File file) throws IOException;
}
