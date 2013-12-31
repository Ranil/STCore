package me.Ranil.STCore.events;

import me.Ranil.STCore.ShatteredTears;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerEvents implements Listener {

	private ShatteredTears plugin;

	public PlayerEvents(ShatteredTears instance) {
		this.plugin = instance;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e){
		Player player = e.getPlayer();
		if(!plugin.config.contains(player.getName()+".race")){
			plugin.config.set(player.getName()+".race", "None");
			plugin.saveConfig();
			player.sendMessage("No race found, setting to default.");
		}
		if(!plugin.config.contains(player.getName()+".class")){
			plugin.config.set(player.getName()+".class", "Citizen");
			plugin.saveConfig();
			player.sendMessage("No class found, set to Citizen.");
		}
	}
}
