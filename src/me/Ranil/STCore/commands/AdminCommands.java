package me.Ranil.STCore.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AdminCommands implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {

		if (!(sender instanceof Player)) {
			Bukkit.getLogger().info("Only players can use this command!");
		}
		Player player = (Player) sender;
		if (player.isOp()) {
			if (cmd.getName().equalsIgnoreCase("ststats")) {
				if (args.length != 4) {
					player.sendMessage(ChatColor.RED
							+ "Usage: /ststats <int> <dex> <str> <vit>");
				} else {
					ItemStack item = player.getItemInHand();
					if (item != null) {
						List<String> loreLines = new ArrayList<String>();
						loreLines.add(ChatColor.AQUA + "Int: " + args[0]);
						loreLines.add(ChatColor.GREEN + "Dex: " + args[1]);
						loreLines.add(ChatColor.RED + "Str: " + args[2]);
						loreLines.add(ChatColor.LIGHT_PURPLE + "Vit: "
								+ args[3]);
						ItemMeta im = item.getItemMeta();
						im.setLore(loreLines);
						item.setItemMeta(im);
						player.sendMessage(ChatColor.AQUA + "Stats added.");
					}
				}
			}
			if(cmd.getName().equalsIgnoreCase("stname")){
				if(args.length!=1){
					player.sendMessage(ChatColor.RED + "Usage: /stname <display_name>");
				}
				ItemStack item = player.getItemInHand();
				if(item != null){
					String line = args[0].toString();
					line.replace("_", " ");
					item.getItemMeta().setDisplayName(line);
				}
			}
		}

		return false;
	}

}
