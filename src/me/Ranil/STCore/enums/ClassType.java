package me.Ranil.STCore.enums;

public enum ClassType {
	WARRIOR, ASSASSIN, WITCHDOCTOR, PALADIN, CITIZEN, BOWMAN, TAMER, GADGETEER, MAGE;

	public static ClassType getClassFromString(String classname) {
		switch (classname) {
		case "Warrior":
			return WARRIOR;
		case "Assassin":
			return ASSASSIN;
		case "Witchdoctor":
			return WITCHDOCTOR;
		case "Paladin":
			return PALADIN;
		case "Bowman":
			return BOWMAN;
		case "Tamer":
			return TAMER;
		case "Gadgeteer":
			return GADGETEER;
		case "Mage":
			return MAGE;
		default:
			return CITIZEN;
		}
	}
}
