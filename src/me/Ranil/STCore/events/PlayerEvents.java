package me.Ranil.STCore.events;

import java.util.List;

import me.Ranil.STCore.ShatteredTears;
import me.Ranil.STCore.api.PlayerFile;
import me.Ranil.STCore.enums.ClassType;
import me.Ranil.STCore.enums.RaceType;
import me.Ranil.STCore.enums.RankType;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerEvents implements Listener {

	private ShatteredTears plugin;

	public PlayerEvents(ShatteredTears instance) {
		this.plugin = instance;

	}

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e) {
		Player player = e.getPlayer();
		PlayerFile playerConfig = plugin.getPlayerYaml(player);
		RankType rank = RankType.getRankFromString(playerConfig
				.getString("rank"));
		RaceType race = RaceType.getRaceFromString(playerConfig
				.getString("race"));
		ClassType classType = ClassType.getClassFromString(playerConfig
				.getString("class"));
		e.setFormat(RankType.getPrefix(rank) + ChatColor.GRAY + "["
				+ RaceType.getAbrev(race) + " "
				+ ClassType.classAbrev(classType) + ChatColor.GRAY + "]"
				+ player.getName() + ": " + ChatColor.WHITE + e.getMessage());
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		PlayerFile playerConfig = plugin.getPlayerYaml(player);
		
		if(!playerConfig.contains("rank")){
			playerConfig.add("rank", "member");
			playerConfig.save();
		}
		if(!playerConfig.contains("race")){
			playerConfig.add("race", "none");
			playerConfig.save();
		}
		if(!playerConfig.contains("class")){
			playerConfig.add("class", "citizen");
			playerConfig.save();
		}
		e.setJoinMessage("");
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
					attacker.sendMessage(ChatColor.RED
							+ "You can't attack players in your faction!");
				}
			}
		}
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e){
		Player p = e.getPlayer();
		List<Entity> ent = p.getNearbyEntities(10, 10, 10);
		for(Entity entity : ent){
			if(p.hasLineOfSight(entity)){
				//do stuff
			}
		}
	}
}
