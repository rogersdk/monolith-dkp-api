package br.com.baloonsproject.monolithdkp.services;

import java.io.File;

import br.com.baloonsproject.monolithdkp.api.entities.Event;

public interface MonolithDkpParser {

	Event parseMonolithDkpFile(File file);
}
