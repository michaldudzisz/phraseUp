package com.phraseup.phupserver;

import com.phraseup.phupserver.initialization.LanguagesStorage;
import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class PhupServerApplication {

	public static void main(String[] args) throws IOException, ParseException {
		SpringApplication.run(PhupServerApplication.class, args);
		LanguagesStorage.loadLanguages("languages.json");
	}

}
