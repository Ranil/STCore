package me.Ranil.STCore.commands;

import java.util.ArrayList;
import java.util.List;

import me.Ranil.STCore.ShatteredTears;
import me.Ranil.STCore.enums.ClassType;
import me.Ranil.STCore.enums.RaceType;
import me.Ranil.STCore.enums.RankType;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AdminCommands implements CommandExecutor {

	private ShatteredTears plugin;

	public AdminCommands(ShatteredTears instance) {
		this.plugin = instance;
	}

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

			if (cmd.getName().equalsIgnoreCase("forcerace")) {
				if (args.length == 2) {
					if (Bukkit.getServer().getPlayerExact(args[0]) != null) {
						Player targetPlayer = Bukkit.getServer()
								.getPlayerExact(args[0]);
						if (RaceType.getRaceFromString(args[1].toLowerCase()) != null) {
							FileConfiguration playerConfig = plugin
									.getPlayerConfig(targetPlayer);
							playerConfig.set("race", args[1].toLowerCase());
							player.sendMessage(ChatColor.AQUA
									+ "Set "
									+ ChatColor.GOLD
									+ targetPlayer.getName()
									+ ChatColor.AQUA
									+ " to race "
									+ RaceType.getAbrev(RaceType
											.getRaceFromString(args[1])));
							plugin.savePlayerConfig(targetPlayer);
						} else {
							player.sendMessage(ChatColor.RED + "Invalid race!");
						}
					} else {
						player.sendMessage(ChatColor.RED
								+ "Couldn't find a player by that name.");
					}
				} else {
					player.sendMessage(ChatColor.RED
							+ "Usage: /forcerace <player> <race>");
				}
			}

			if (cmd.getName().equalsIgnoreCase("forceclass")) {
				if (args.length == 2) {
					if (Bukkit.getServer().getPlayerExact(args[0]) != null) {
						Player targetPlayer = Bukkit.getServer()
								.getPlayerExact(args[0]);
						if (ClassType.getClassFromString(args[1].toLowerCase()) != null) {
							FileConfiguration playerConfig = plugin
									.getPlayerConfig(targetPlayer);
							playerConfig.set("class", args[1].toLowerCase());
							player.sendMessage(ChatColor.AQUA
									+ "Set "
									+ ChatColor.GOLD
									+ targetPlayer.getName()
									+ ChatColor.AQUA
									+ " to class "
									+ ClassType.classAbrev(ClassType
											.getClassFromString(args[1]
													.toLowerCase())));
							plugin.savePlayerConfig(targetPlayer);
						} else {
							player.sendMessage(ChatColor.RED + "Invalid Class!");
						}
					} else {
						player.sendMessage(ChatColor.RED
								+ "Couldn't find a player by that name.");
					}
				} else {
					player.sendMessage(ChatColor.RED
							+ "Usage: /forceclass <player> <class>");
				}
			}

			if (cmd.getName().equalsIgnoreCase("setrank")) {
				if (args.length == 2) {
					if (Bukkit.getServer().getPlayerExact(args[0]) != null) {
						Player targetPlayer = Bukkit.getServer()
								.getPlayerExact(args[0]);
						if (RankType.getRankFromString(args[1].toLowerCase()) != null) {
							FileConfiguration playerConfig = plugin
									.getPlayerConfig(targetPlayer);
							playerConfig.set("rank", args[1].toLowerCase());
							player.sendMessage(ChatColor.AQUA
									+ "Set "
									+ ChatColor.GOLD
									+ targetPlayer.getName()
									+ ChatColor.AQUA
									+ " to rank "
									+ RankType.getPrefix(RankType
											.getRankFromString(args[1]
													.toLowerCase())));
							plugin.savePlayerConfig(targetPlayer);
						} else {
							player.sendMessage(ChatColor.RED + "Invalid rank!");
						}
					} else {
						player.sendMessage(ChatColor.RED
								+ "Couldn't find a player by that name!");
					}

				} else {
					player.sendMessage(ChatColor.RED
							+ "Usage: /setrank <player> <rank>");
				}
			}

			if (cmd.getName().equalsIgnoreCase("stname")) {
				if (args.length != 1) {
					player.sendMessage(ChatColor.RED
							+ "Usage: /stname <display_name>");
				}
				ItemStack item = player.getItemInHand();
				if (item != null) {
					String line = args[0].toString();
					line.replace("_", " ");
					item.getItemMeta().setDisplayName(line);
				}
			}
		}

		return false;
	}

}
