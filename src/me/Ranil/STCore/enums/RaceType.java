package me.Ranil.STCore.enums;

public enum RaceType {
	HUMAN, DWARF, WOODELF, KHAJIIT, ORC, GOBLIN, DARKELF, ARGONIAN, NONE;

	public static String getFaction(RaceType race) {
		switch (race) {
		case HUMAN:
			return "TERIAN";
		case DWARF:
			return "TERIAN";
		case WOODELF:
			return "TERIAN";
		case KHAJIIT:
			return "TERIAN";
		case ORC:
			return "FEKKIX";
		case GOBLIN:
			return "FEKKIX";
		case DARKELF:
			return "FEKKIX";
		case ARGONIAN:
			return "FEKKIX";
		default:
			return "NEUTRAL";
		}
	}

	public static RaceType getRaceFromString(String race) {
		switch (race) {
		case "Human":
			return HUMAN;
		case "Dwarf":
			return DWARF;
		case "Woodelf":
			return WOODELF;
		case "Khajiit":
			return KHAJIIT;
		case "Orc":
			return ORC;
		case "Goblin":
			return GOBLIN;
		case "Darkelf":
			return DARKELF;
		case "Argonian":
			return ARGONIAN;
		default:
			return NONE;
		}
	}
}
