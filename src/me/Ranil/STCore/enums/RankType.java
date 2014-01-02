package me.Ranil.STCore.enums;

import org.bukkit.ChatColor;

public enum RankType {
	ADMIN, DEVELOPER, MOD, BUILDER, MEMBER;

	public static RankType getRankFromString(String rank) {
		if (rank != null) {
			rank = rank.toLowerCase();
			switch (rank) {
			case "admin":
				return ADMIN;
			case "developer":
				return DEVELOPER;
			case "mod":
				return MOD;
			case "builder":
				return BUILDER;
			case "member":
				return MEMBER;
			default:
				return MEMBER;
			}
		} else {
			return MEMBER;
		}
	}

	public static String getPrefix(RankType rank) {
		switch (rank) {
		case ADMIN:
			return ChatColor.AQUA + "❅ ";
		case DEVELOPER:
			return ChatColor.GOLD + "❅ ";
		case MOD:
			return ChatColor.DARK_GRAY + "❅ ";
		case BUILDER:
			return ChatColor.LIGHT_PURPLE + "❆";
		case MEMBER:
			return "";
		default:
			return "";
		}
	}
}
