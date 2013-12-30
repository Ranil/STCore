package me.Ranil.STCore.api;

import lombok.Getter;
import me.Ranil.STCore.ShatteredTears;

import org.bukkit.entity.Player;


public class Gamer {
	private ShatteredTears plugin;
	
	@Getter Player player;
	
	public Gamer(Player player, ShatteredTears instance){
		this.player = player;
		this.plugin = instance;
		
	}
	
	
}
