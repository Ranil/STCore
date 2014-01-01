package me.Ranil.STCore;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import lombok.Getter;
import me.Ranil.STCore.commands.AdminCommands;
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

		registerCommand("ststats");
		registerCommand("stname");

		this.config = getConfig();
	}

	public void onDisable() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			/*
			 * playerFile = new File(playerDir, p.getName() + ".yml");
			 * playerConfig = YamlConfiguration.loadConfiguration(playerFile);
			 * try {
			 * 
			 * playerConfig.save(playerFile); } catch (IOException e) {
			 * 
			 * Bukkit.getLogger().log(Level.SEVERE, "Couldn't save player file",
			 * e); }
			 */
		}
	}

	public void registerCommand(String cmd) {
		this.getCommand(cmd).setExecutor(new AdminCommands());
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
	
	public void savePlayerConfig(Player player){
		if(playerConfig==null || playerFile == null){
			return;
		}
		try{
			getPlayerConfig(player).save(playerFile);
		} catch(IOException ex){
			getLogger().log(Level.SEVERE, "Couldn't save file " + playerFile, ex);
		}
	}
}
