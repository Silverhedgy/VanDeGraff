package com.cognificent.vandegraff;

import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;

public class VanDeGraffPlayerListener extends PlayerListener {
	private final VanDeGraffPlugin plugin;
	
	public VanDeGraffPlayerListener(final VanDeGraffPlugin plugin) {
		this.plugin = plugin;
	}

}
