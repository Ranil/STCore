package me.Ranil.STCore;

import lombok.Getter;
import me.Ranil.STCore.commands.AdminCommands;
import me.Ranil.STCore.events.DamageEvents;
import me.Ranil.STCore.events.EquipEvents;
import me.Ranil.STCore.events.TutorialEvents;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ShatteredTears extends JavaPlugin {

	@Getter
	public static ShatteredTears instance;

	public FileConfiguration config;

	public TutorialEvents te = new TutorialEvents(this);
	public EquipEvents ee = new EquipEvents(this);
	public DamageEvents de = new DamageEvents(this);

	public void onEnable() {
		PluginManager pm = Bukkit.getServer().getPluginManager();

		pm.registerEvents(te, this);
		pm.registerEvents(ee, this);
		pm.registerEvents(de, this);
		
		registerCommand("ststats");
		registerCommand("stname");
		
		this.config = getConfig();

		for (Player p : Bukkit.getOnlinePlayers()) {
			if (!config.contains(p.getName() + "tut.complete")) {
				config.set(p.getName() + "tut.complete", false);
			}
		}
	}
	
	public void registerCommand(String cmd){
		this.getCommand(cmd).setExecutor(new AdminCommands());
	}
	

}
