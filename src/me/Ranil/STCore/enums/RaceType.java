package me.Ranil.STCore.enums;

public enum RaceType {
	HUMAN, DWARF, WOODELF, KAHJIIT, ORC, GOBLIN, DARKELF, ARGONIAN, NONE;

	public static String getFaction(RaceType race) {
		switch (race) {
		case HUMAN:
		case DWARF:
		case WOODELF:
		case KAHJIIT:
			return "TERIAN";
		case ORC:
		case GOBLIN:
		case DARKELF:
		case ARGONIAN:
			return "FEKKIX";
		default:
			return "NEUTRAL";
		}
	}

	public static RaceType getRaceFromString(String race) {
		switch (race) {
		case "human":
			return HUMAN;
		case "dwarf":
			return DWARF;
		case "woodelf":
			return WOODELF;
		case "kahjiit":
			return KAHJIIT;
		case "orc":
			return ORC;
		case "goblin":
			return GOBLIN;
		case "darkelf":
			return DARKELF;
		case "argonian":
			return ARGONIAN;
		default:
			return NONE;
		}
	}

	public static String getAbrev(RaceType race) {
		switch (race) {
		case HUMAN:
			return "§3HUM";
		case DWARF:
			return "§8DWA";
		case WOODELF:
			return "§2WDE";
		case KAHJIIT:
			return "§6KHA";
		case ORC:
			return "§4ORC";
		case GOBLIN:
			return "§aGOB";
		case DARKELF:
			return "§7DRE";
		case ARGONIAN:
			return "§cARG";
		default:
			return "NON";
		}
	}
}
