package me.Ranil.STCore.enums;

public enum ClassType {
	WARRIOR, ASSASSIN, WITCHDOCTOR, PALADIN, CITIZEN, BOWMAN, TAMER, GADGETEER, MAGE;

	public static ClassType getClassFromString(String classname) {
		if (classname != null) {
			classname = classname.toLowerCase();
			switch (classname) {
			case "warrior":
				return WARRIOR;
			case "assassin":
				return ASSASSIN;
			case "witchdoctor":
				return WITCHDOCTOR;
			case "paladin":
				return PALADIN;
			case "bowman":
				return BOWMAN;
			case "tamer":
				return TAMER;
			case "gadgeteer":
				return GADGETEER;
			case "mage":
				return MAGE;
			default:
				return CITIZEN;
			}
		} else {
			return CITIZEN;
		}
	}

	public static String classAbrev(ClassType classType) {

		switch (classType) {
		case WARRIOR:
			return "§cWAR";
		case ASSASSIN:
			return "§dASN";
		case WITCHDOCTOR:
			return "§5WIT";
		case PALADIN:
			return "§6PAL";
		case BOWMAN:
			return "§2BOW";
		case TAMER:
			return "§eTAM";
		case GADGETEER:
			return "§9GAD";
		case MAGE:
			return "§1MAG";
		default:
			return "§bCIT";
		}
	}
}
