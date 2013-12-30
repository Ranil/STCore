package me.Ranil.STCore.events;

import me.Ranil.STCore.ShatteredTears;
import me.Ranil.STCore.api.PlayerStats;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class EquipEvents implements Listener {

	private ShatteredTears plugin;
	private PlayerStats stats = new PlayerStats(plugin);

	public EquipEvents(ShatteredTears instance) {
		this.plugin = instance;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e){
		final Player player = e.getPlayer();
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable(){
			public void run(){
				stats.applyBonuses(player);
			}
		}, 1L);
	}
	
	@EventHandler
	public void onArmorEquipInventory(InventoryClickEvent e) {
		final Player player = (Player) e.getWhoClicked();
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			public void run() {
				stats.applyBonuses(player);
			}
		}, 1L);
	}

	@EventHandler
	public void onPlayerClickEquip(PlayerInteractEvent e) {
		final Player player = e.getPlayer();
		if (e.getAction() == Action.RIGHT_CLICK_AIR
				|| e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Bukkit.getScheduler().scheduleSyncDelayedTask(plugin,
					new Runnable() {
						public void run() {
							stats.applyBonuses(player);
						}
					}, 1L);
		}
	}
}
