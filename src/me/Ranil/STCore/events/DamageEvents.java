package me.Ranil.STCore.events;

import java.util.Random;

import me.Ranil.STCore.ShatteredTears;
import me.Ranil.STCore.api.PlayerStats;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DamageEvents implements Listener{

	private ShatteredTears plugin;
	private PlayerStats stats = new PlayerStats(plugin);
	
	public DamageEvents(ShatteredTears instance){
		this.plugin = instance;
	}
	
	@EventHandler
	public void onPlayerDamage(EntityDamageByEntityEvent e){
		if(e.getDamager() instanceof Player){
			if(e.getEntity() instanceof Player){
				Player player = (Player) e.getEntity();
				Player attacker = (Player)e.getDamager();
				double bonus = stats.getStr(attacker) * 0.026D;
				Random rand = new Random();
				double base = rand.nextDouble();
				if(base > 0.5D){
					base = base - 0.45D;
				}
				double dmg = base + bonus;
				double armorDeduction = stats.getArmor(player) * 0.4D;
				double finalDmg = dmg - armorDeduction;
				e.setDamage(finalDmg);
				player.sendMessage("Hit!");
				player.sendMessage(player.getHealth() + "");
				
			}
		}
	}
}
