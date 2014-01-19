package me.Ranil.STCore.enums;

public enum SkillTrees {

	WARRIOR1, WARRIOR2, WARRIOR3, ASSASSIN1, ASSASSIN2, ASSASSIN3, WITCHDOCTOR1, WITCHDOCTOR2, WITCHDOCTOR3, PALADIN1, PALADIN2, PALADIN3, BOWMAN1, BOWMAN2, BOWMAN3, TAMER1, TAMER2, TAMER3, GADGETEER1, GADGETEER2, GADGETEER3, MAGE1, MAGE2, MAGE3;

	public ClassType getClassFromSkill(SkillTrees skill) {
		switch (skill) {
		case WARRIOR1:
		case WARRIOR2:
		case WARRIOR3:
			return ClassType.WARRIOR;
		case ASSASSIN1:
		case ASSASSIN2:
		case ASSASSIN3:
			return ClassType.ASSASSIN;
		case WITCHDOCTOR1:
		case WITCHDOCTOR2:
		case WITCHDOCTOR3:
			return ClassType.WITCHDOCTOR;
		case PALADIN1:
		case PALADIN2:
		case PALADIN3:
			return ClassType.PALADIN;
		case BOWMAN1:
		case BOWMAN2:
		case BOWMAN3:
			return ClassType.BOWMAN;
		case TAMER1:
		case TAMER2:
		case TAMER3:
			return ClassType.TAMER;
		}
		return ClassType.CITIZEN;
	}
}
