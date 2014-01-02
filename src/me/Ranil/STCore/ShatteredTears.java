package me.Ranil.STCore;

import java.io.File;
import java.util.Random;

import lombok.Getter;
import me.Ranil.STCore.api.PlayerFile;
import me.Ranil.STCore.commands.AdminCommands;
import me.Ranil.STCore.enums.ClassType;
import me.Ranil.STCore.enums.RaceType;
import me.Ranil.STCore.enums.RankType;
import me.Ranil.STCore.events.DamageEvents;
import me.Ranil.STCore.events.EquipEvents;
import me.Ranil.STCore.events.PlayerEvents;
import me.Ranil.STCore.events.TutorialEvents;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.yaml.snakeyaml.Yaml;

public class ShatteredTears extends JavaPlugin {

	@Getter
	public static ShatteredTears instance;

	public FileConfiguration config;

	public TutorialEvents te = new TutorialEvents(this);
	public EquipEvents ee = new EquipEvents(this);
	public DamageEvents de = new DamageEvents(this);
	public PlayerEvents pe = new PlayerEvents(this);

	public void onEnable() {
		PluginManager pm = Bukkit.getServer().getPluginManager();

		pm.registerEvents(te, this);
		pm.registerEvents(ee, this);
		pm.registerEvents(de, this);
		pm.registerEvents(pe, this);

		registerAdminCommand("ststats");
		registerAdminCommand("stname");
		registerAdminCommand("forcerace");
		registerAdminCommand("forceclass");
		registerAdminCommand("setrank");

		this.config = getConfig();

	}

	public void onDisable() {

	}

	public void registerAdminCommand(String cmd) {
		this.getCommand(cmd).setExecutor(new AdminCommands(this));
	}

	public PlayerFile getPlayerYaml(Player player) {
		return new PlayerFile(getDataFolder().getAbsolutePath()
				+ File.separator + "stdata" + File.separator + "players"
				+ File.separator + player.getName() + ".yml");
	}

	public PlayerFile getOfflinePlayerYaml(String string) {

		return new PlayerFile(getDataFolder().getAbsolutePath()
				+ File.separator + "stdata" + File.separator + "players"
				+ File.separator + string + ".yml");

	}

	public RaceType getRace(Player player) {
		PlayerFile playerConfig = getPlayerYaml(player);
		if (RaceType.getRaceFromString(playerConfig.getString("race")) != null) {
			return RaceType.getRaceFromString(playerConfig.getString("race"));
		}
		return RaceType.NONE;
	}

	public ClassType getClass(Player player) {
		PlayerFile playerConfig = getPlayerYaml(player);
		if (ClassType.getClassFromString(playerConfig.getString("class")) != null) {
			return ClassType
					.getClassFromString(playerConfig.getString("class"));
		}
		return ClassType.CITIZEN;
	}

	public RankType getRank(Player player) {
		PlayerFile playerConfig = getPlayerYaml(player);
		if (RankType.getRankFromString(playerConfig.getString("rank")) != null) {
			return RankType.getRankFromString(playerConfig.getString("rank"));
		}
		return RankType.MEMBER;
	}

	public int getRandFromLevel(int level) {
		Random rand = new Random();
		if (level < 10) {
			return 15;
		} else if (level > 10 && level < 20) {
			return rand.nextInt(10) + 20;
		} else if (level > 20 && level < 30) {
			return rand.nextInt(20) + 25;
		} else if (level > 30 && level < 40) {
			return rand.nextInt(30) + 30;
		} else if (level > 40 && level < 50) {
			return rand.nextInt(45) + 40;
		} else if (level == 50) {
			return rand.nextInt(60) + 40;
		}
		return 1;
	}
}
