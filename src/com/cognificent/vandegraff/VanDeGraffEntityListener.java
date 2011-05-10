package com.cognificent.vandegraff;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Minecart;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityListener;

public class VanDeGraffEntityListener extends EntityListener{
	private final VanDeGraffPlugin plugin;

	public VanDeGraffEntityListener(final VanDeGraffPlugin plugin) {
		this.plugin = plugin;
	}
	
	public void onEntityDamage(EntityDamageEvent event) {
		if(event.getCause() == DamageCause.LIGHTNING) {
			if(plugin.config.getBoolean("lightning.carts", false)) {
				
				if(event.getEntity() instanceof Minecart) {
					event.setCancelled(true);
				}
				
				if (event.getEntity() instanceof LivingEntity
						&& ((LivingEntity)event.getEntity()).isInsideVehicle()) {
					event.setCancelled(true);
				}
			}
			
			event.setDamage(plugin.config.getInt("lightning.damage", 5));
		}
	}
}

