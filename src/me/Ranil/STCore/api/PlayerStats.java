package me.Ranil.STCore.api;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import lombok.Getter;
import me.Ranil.STCore.ShatteredTears;
import me.Ranil.STCore.enums.ClassType;
import me.Ranil.STCore.enums.RaceType;

public class PlayerStats {

	@Getter
	ShatteredTears plugin;

	public PlayerStats(ShatteredTears instance) {
		this.plugin = instance;
	}

	public void applyBonuses(Player player) {
		double bonusHp = getVit(player) * 0.023D;
		player.setMaxHealth(6D + bonusHp);
	}

	public int getInt(Player player) {
		int helmInt = getIntFromItem(player.getInventory().getHelmet());
		int chestInt = getIntFromItem(player.getInventory().getChestplate());
		int legInt = getIntFromItem(player.getInventory().getLeggings());
		int bootInt = getIntFromItem(player.getInventory().getBoots());

		int half = helmInt + chestInt;
		int other = legInt + bootInt;

		return half + other;
	}

	public int getStr(Player player) {
		int helmInt = getStrFromItem(player.getInventory().getHelmet());
		int chestInt = getStrFromItem(player.getInventory().getChestplate());
		int legInt = getStrFromItem(player.getInventory().getLeggings());
		int bootInt = getStrFromItem(player.getInventory().getBoots());

		int half = helmInt + chestInt;
		int other = legInt + bootInt;

		return half + other;
	}

	public int getDex(Player player) {
		int helmInt = getDexFromItem(player.getInventory().getHelmet());
		int chestInt = getDexFromItem(player.getInventory().getChestplate());
		int legInt = getDexFromItem(player.getInventory().getLeggings());
		int bootInt = getDexFromItem(player.getInventory().getBoots());

		int half = helmInt + chestInt;
		int other = legInt + bootInt;

		return half + other;
	}

	public int getVit(Player player) {
		int helmInt = getVitFromItem(player.getInventory().getHelmet());
		int chestInt = getVitFromItem(player.getInventory().getChestplate());
		int legInt = getVitFromItem(player.getInventory().getLeggings());
		int bootInt = getVitFromItem(player.getInventory().getBoots());

		int half = helmInt + chestInt;
		int other = legInt + bootInt;
		int vit = half + other;
		if (vit > 600) {
			return 600;
		} else {
			return vit;
		}
	}
	public int getArmor(Player player){
		int helmInt = getArmorValue(player.getInventory().getHelmet());
		int chestInt = getArmorValue(player.getInventory().getChestplate());
		int legInt = getArmorValue(player.getInventory().getLeggings());
		int bootInt = getArmorValue(player.getInventory().getBoots());
		
		int half = helmInt + chestInt;
		int other = legInt + bootInt;
		int armor = half + other;
		return armor;
	}

	public int getIntFromItem(ItemStack item) {
		int amount = 0;
		if (item != null) {
			if (item.getItemMeta().getLore() != null) {
				for (String line : item.getItemMeta().getLore()) {
					line = ChatColor.stripColor(line);
					if (line.startsWith("Int: ")) {
						String count = line.substring("Int: ".length()).trim();
						try {
							amount = Integer.valueOf(count);
							break;
						} catch (NumberFormatException e) {
							Bukkit.getLogger().info(
									"Invalid Intelligence Integer on item!!");
							break;
						}
					}
				}
			}
		}
		return amount;
	}

	public int getDexFromItem(ItemStack item) {
		int amount = 0;
		if (item != null) {
			if (item.getItemMeta().getLore() != null) {
				for (String line : item.getItemMeta().getLore()) {
					line = ChatColor.stripColor(line);
					if (line.startsWith("Dex: ")) {
						String count = line.substring("Dex: ".length()).trim();
						try {
							amount = Integer.valueOf(count);
							break;
						} catch (NumberFormatException e) {
							Bukkit.getLogger().info(
									"Invalid Intelligence Integer on item!!");
							break;
						}
					}
				}
			}
		}
		return amount;
	}

	public int getStrFromItem(ItemStack item) {
		int amount = 0;
		if (item != null) {
			if (item.getItemMeta().getLore() != null) {
				for (String line : item.getItemMeta().getLore()) {
					line = ChatColor.stripColor(line);
					if (line.startsWith("Str: ")) {
						String count = line.substring("Str: ".length()).trim();
						try {
							amount = Integer.valueOf(count);
							break;
						} catch (NumberFormatException e) {
							Bukkit.getLogger().info(
									"Invalid Intelligence Integer on item!!");
							break;
						}
					}
				}
			}
		}
		return amount;
	}

	public int getVitFromItem(ItemStack item) {
		int amount = 0;
		if (item != null) {
			if (item.getItemMeta().getLore() != null) {

				for (String line : item.getItemMeta().getLore()) {
					line = ChatColor.stripColor(line);
					if (line.startsWith("Vit: ")) {
						String count = line.substring("Vit: ".length()).trim();
						try {
							amount = Integer.valueOf(count);
							break;
						} catch (NumberFormatException e) {
							Bukkit.getLogger().info(
									"Invalid Intelligence Integer on item!!");
							break;
						}
					}
				}
			}
		}
		return amount;
	}

	public int getArmorValue(ItemStack item) {
		int amount = 0;
		if (item != null) {
			if (item.getItemMeta().getLore() != null) {

				for (String line : item.getItemMeta().getLore()) {
					line = ChatColor.stripColor(line);
					if (line.startsWith("Armor: ")) {
						String count = line.substring("Armor: ".length())
								.trim();
						try {
							amount = Integer.valueOf(count);
							break;
						} catch (NumberFormatException e) {
							Bukkit.getLogger().info(
									"Invalid armor value on item!");
							break;
						}
					}
				}
			}
		}
		return amount;
	}
}
