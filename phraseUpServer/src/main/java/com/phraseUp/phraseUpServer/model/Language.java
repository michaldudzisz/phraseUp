package com.phraseUp.phraseUpServer.model;

public enum Language {
	SPANISH,
	GERMAN,
	POLISH;

	@Override
	public String toString() {
		return super.toString().toLowerCase();
	}

	public static Language fromString(String language) {
		Language correspondingEnum = null;
		for (Language langEnum : Language.values()) {
			if (langEnum.toString().equals(language)) {
				correspondingEnum = langEnum;
				break;
			}
		}
		return correspondingEnum;
	}
}
