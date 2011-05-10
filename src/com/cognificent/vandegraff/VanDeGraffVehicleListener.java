package com.cognificent.vandegraff;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Minecart;
import org.bukkit.event.vehicle.VehicleListener;
import org.bukkit.event.vehicle.VehicleMoveEvent;

public class VanDeGraffVehicleListener extends VehicleListener{
	private final VanDeGraffPlugin plugin;

	public VanDeGraffVehicleListener(final VanDeGraffPlugin plugin) {
		this.plugin = plugin;
	}
	
	public void onVehicleMove(VehicleMoveEvent event) {
		if(plugin.config.getBoolean("lightning.carts", false)
				&& event.getVehicle() instanceof Minecart ) {
				
			Block b1 = event.getFrom().getBlock(),
				  b2 = event.getTo().getBlock();
			
			if(b1.getFace(b2) == BlockFace.EAST
					|| b1.getFace(b2) == BlockFace.WEST) {
				Block n1 = b2.getFace(BlockFace.NORTH);
				if(n1.getType() == Material.WOOL) n1.getWorld().strikeLightning(n1.getLocation());
				Block n2 = b2.getFace(BlockFace.SOUTH);
				if(n2.getType() == Material.WOOL) n2.getWorld().strikeLightning(n2.getLocation());
			}
			else if(b1.getFace(b2) == BlockFace.NORTH
					|| b1.getFace(b2) == BlockFace.SOUTH) {
				Block n1 = b2.getFace(BlockFace.EAST);
				if(n1.getType() == Material.WOOL) n1.getWorld().strikeLightning(n1.getLocation());
				Block n2 = b2.getFace(BlockFace.WEST);
				if(n2.getType() == Material.WOOL) n2.getWorld().strikeLightning(n2.getLocation());
			}
		}
	}
}
