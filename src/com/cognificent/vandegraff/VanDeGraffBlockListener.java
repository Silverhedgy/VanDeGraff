package com.cognificent.vandegraff;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockListener;

public class VanDeGraffBlockListener extends BlockListener{
	private final VanDeGraffPlugin plugin;
	
	public VanDeGraffBlockListener(final VanDeGraffPlugin plugin) {
		this.plugin = plugin;
	}
	
	public void onBlockDamage(BlockDamageEvent event) {
		if(plugin.config.getBoolean("lightning.manual", false)){
			Player player = event.getPlayer();
			Block block = event.getBlock();
			
			if(block.getType() == Material.GLASS
					&& player.getItemInHand().getType() == Material.WOOL) {
				plugin.setCharged(player, true);
			}
			else if(plugin.isCharged(player)) {
				block.getWorld().strikeLightning(block.getLocation());
				plugin.setCharged(player, false);
			}
		}
	}

}
