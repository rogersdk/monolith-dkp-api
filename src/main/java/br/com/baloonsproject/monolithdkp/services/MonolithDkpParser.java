package br.com.baloonsproject.monolithdkp.services;

import java.io.File;
import java.util.List;

import br.com.baloonsproject.monolithdkp.api.entities.Dkp;
import br.com.baloonsproject.monolithdkp.api.entities.Event;

public interface MonolithDkpParser {

	Event parseMonolithDkpFile(File file);
	List<Dkp> parseDkpHistory(File file);
}
