package com.cognificent.vandegraff;

import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerListener;

public class VanDeGraffPlayerListener extends PlayerListener{
	private final VanDeGraffPlugin plugin;
	
	public VanDeGraffPlayerListener(VanDeGraffPlugin plugin) {
		this.plugin = plugin;
	}
	
	public void onPlayerItemHeldChange(PlayerItemHeldEvent event) {
		if (plugin.isCharged()) {
			plugin.setCharged(false);
		}
	}

}
