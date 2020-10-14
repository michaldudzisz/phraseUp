package com.phraseup.phupserver.initialization;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class LanguagesStorage {

    private static List<String> languages;

    private LanguagesStorage() {}

    public static List<String> getLanguages() {
        return languages;
    }

    public static void loadLanguages(String filePath) throws IOException, ParseException {
        JSONObject jsonObj = (JSONObject) new JSONParser().parse(new FileReader(new ClassPathResource(filePath).getFile()));
        languages = (List<String>) jsonObj.get("languages");
    }

}
