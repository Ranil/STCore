package me.Ranil.STCore.events;

import me.Ranil.STCore.ShatteredTears;
import me.Ranil.STCore.enums.ClassType;
import me.Ranil.STCore.enums.RaceType;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerEvents implements Listener {

	private ShatteredTears plugin;

	public PlayerEvents(ShatteredTears instance) {
		this.plugin = instance;

	}

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e) {
		Player player = e.getPlayer();
		FileConfiguration playerConfig = plugin.getPlayerConfig(player);
		RaceType race = RaceType.getRaceFromString(playerConfig
				.getString("race"));
		ClassType classType = ClassType.getClassFromString(playerConfig
				.getString("class"));
		e.setFormat(ChatColor.GRAY + "[" + RaceType.getAbrev(race) + " "
				+ ClassType.classAbrev(classType) + ChatColor.GRAY + "]"
				+ player.getName() + ": " + ChatColor.WHITE + e.getMessage());
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		FileConfiguration playerConfig = plugin.getPlayerConfig(player);
		e.setJoinMessage("");
		if (!playerConfig.contains("race")) {
			playerConfig.set("race", "none");
			plugin.savePlayerConfig(player);
			player.sendMessage("Set race to default");
		}
		if (!playerConfig.contains("class")) {
			playerConfig.set("class", "Citizen");
			plugin.savePlayerConfig(player);
			player.sendMessage("Set class to default");
		}
	}

	@EventHandler
	public void onPlayerAttackPlayer(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player) {
			Player attacker = (Player) e.getDamager();
			if (e.getEntity() instanceof Player) {
				Player player = (Player) e.getEntity();
				if (RaceType.getFaction(plugin.getRace(player)) == RaceType
						.getFaction(plugin.getRace(attacker))) {
					e.setCancelled(true);
					attacker.sendMessage(ChatColor.RED + "You can't attack players in your faction!");
				}
			}
		}
	}
}
