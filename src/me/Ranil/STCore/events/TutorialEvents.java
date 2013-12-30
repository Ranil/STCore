package me.Ranil.STCore.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.Ranil.STCore.ShatteredTears;
import me.Ranil.STCore.api.Gamer;
import me.Ranil.STCore.api.PlayerStats;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TutorialEvents implements Listener {

	public ShatteredTears plugin;

	private PlayerStats playerStats = new PlayerStats(plugin);

	public TutorialEvents(ShatteredTears instance) {
		this.plugin = instance;
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		Gamer gamer = new Gamer(player, plugin);
		Block b = e.getClickedBlock();
		Action action = e.getAction();
		if (action == Action.RIGHT_CLICK_BLOCK
				&& b != null
				&& (b.getType() == Material.SIGN_POST || b.getType() == Material.WALL_SIGN)) {
			Sign sign = (Sign) b.getState();
			String line = sign.getLine(0);
			String line2 = sign.getLine(1);
			String line3 = sign.getLine(2);
			String line4 = sign.getLine(3);
			if (line.contains("Completed")) {
				if (plugin.config.getBoolean(player.getName() + "tut.complete")) {
					player.sendMessage("Tutorial Completed!");
				} else {
				}

			} else if (line.contains("Derp")) {
				player.sendMessage("Should be working.");
				int intamount = playerStats.getInt(player);
				int stramount = playerStats.getStr(player);
				int dexamount = playerStats.getDex(player);
				int vitamount = playerStats.getVit(player);
				player.sendMessage("Int: " + intamount + "");
				player.sendMessage("Vit: " + vitamount + "");
				player.sendMessage("Str: " + stramount + "");
				player.sendMessage("Dex: " + dexamount + "");
				playerStats.applyBonuses(player);
			}
		}

	}

	@SuppressWarnings("unused")
	private ItemStack createItem(String name, Material item, String lore,
			String lore2, String lore3, String lore4) {
		ItemStack i = new ItemStack(item);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(name);
		List<String> loreLines = new ArrayList<String>();
		loreLines.add(lore);
		loreLines.add(lore2);
		loreLines.add(lore3);
		loreLines.add(lore4);
		im.setLore(loreLines);
		i.setItemMeta(im);
		return i;
	}
}
