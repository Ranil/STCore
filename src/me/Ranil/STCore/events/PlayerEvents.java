package me.Ranil.STCore.events;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import me.Ranil.STCore.ShatteredTears;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
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
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		FileConfiguration playerConfig = plugin.getPlayerConfig(player);

		if (!playerConfig.contains("race")) {
			playerConfig.set("race", "none");
			plugin.savePlayerConfig(player);

		}
		if (!playerConfig.contains("class")) {
			playerConfig.set("class", "Citizen");
			plugin.savePlayerConfig(player);
			player.sendMessage("Set class to default");
		}
	}
}
