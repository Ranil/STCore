package me.Ranil.STCore;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;

import lombok.Getter;
import me.Ranil.STCore.commands.AdminCommands;
import me.Ranil.STCore.enums.ClassType;
import me.Ranil.STCore.enums.RaceType;
import me.Ranil.STCore.events.DamageEvents;
import me.Ranil.STCore.events.EquipEvents;
import me.Ranil.STCore.events.PlayerEvents;
import me.Ranil.STCore.events.TutorialEvents;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ShatteredTears extends JavaPlugin {

	@Getter
	public static ShatteredTears instance;

	private File playerDir = new File(getDataFolder(), "Players");
	private File playerFile = null;
	private FileConfiguration playerConfig = null;

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

		this.config = getConfig();

		for (Player p : Bukkit.getOnlinePlayers()) {
			reloadPlayerConfig(p);
		}
	}

	public void onDisable() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			savePlayerConfig(p);
		}
	}

	public void registerAdminCommand(String cmd) {
		this.getCommand(cmd).setExecutor(new AdminCommands(this));
	}

	public void reloadPlayerConfig(Player player) {
		if (playerFile == null) {
			playerFile = new File(playerDir, player.getName() + ".yml");
		}
		playerConfig = YamlConfiguration.loadConfiguration(playerFile);
	}

	public FileConfiguration getPlayerConfig(Player player) {
		if (playerConfig == null) {
			reloadPlayerConfig(player);
		}
		return playerConfig;
	}

	public void savePlayerConfig(Player player) {
		if (playerConfig == null || playerFile == null) {
			return;
		}
		try {
			getPlayerConfig(player).save(playerFile);
		} catch (IOException ex) {
			getLogger().log(Level.SEVERE, "Couldn't save file " + playerFile,
					ex);
		}
	}

	public RaceType getRace(Player player) {
		FileConfiguration playerConfig = getPlayerConfig(player);
		if (RaceType.getRaceFromString(playerConfig.getString("race")) != null) {
			return RaceType.getRaceFromString(playerConfig.getString("race"));
		}
		return RaceType.NONE;
	}

	public ClassType getClass(Player player) {
		FileConfiguration playerConfig = getPlayerConfig(player);
		if (RaceType.getRaceFromString(playerConfig.getString("class")) != null) {
			return ClassType
					.getClassFromString(playerConfig.getString("class"));
		}
		return ClassType.CITIZEN;
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
