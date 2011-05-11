package com.cognificent.vandegraff;

import java.io.File;
import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;

public class VanDeGraffPlugin extends JavaPlugin{
	Logger log = Logger.getLogger("Minecraft");
	static String mainDirectory = "plugins/VanDeGraff";
	File file = new File(mainDirectory + File.separator + "config.yml");
	Configuration config = new Configuration(file);
	
	private final VanDeGraffBlockListener blockListener = new VanDeGraffBlockListener(this);
	private final VanDeGraffVehicleListener vehicleListener = new VanDeGraffVehicleListener(this);
	private final VanDeGraffEntityListener entityListener = new VanDeGraffEntityListener(this);
	private final VanDeGraffPlayerListener playerListener = new VanDeGraffPlayerListener(this);
	private static HashMap<Player, Boolean> charged = new HashMap<Player, Boolean>();

	@Override
	public void onDisable() {
		log.info("VanDeGraff disabled.");
	}

	@Override
	public void onEnable() {
        // Register our events
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvent(Event.Type.BLOCK_DAMAGE, blockListener, Priority.Normal, this);
        pm.registerEvent(Event.Type.VEHICLE_MOVE, vehicleListener, Priority.Normal, this);
        pm.registerEvent(Event.Type.ENTITY_DAMAGE, entityListener, Priority.Normal, this);
        pm.registerEvent(Event.Type.PLAYER_ITEM_HELD, playerListener, Priority.Normal, this);
        
        configure();
        
        // EXAMPLE: Custom code, here we just output some info so we can check all is well
        PluginDescriptionFile pdfFile = this.getDescription();
        log.info( pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!" );
		
	}
	
	public void configure() {
		if (!new File(mainDirectory).exists()) {
			new File(mainDirectory).mkdir();
			config.load();
			config.setProperty("lightning.damage", 5);
			config.setProperty("lightning.carts", true);
			config.setProperty("lightning.manual", true);
			config.save();
		}
		else {
			config.load();
		}
	}

	public void setCharged(Player player, boolean value) {
		charged.put(player, value);
	}

	public boolean isCharged(Player player) {
		if (!charged.containsKey(player)) {
			charged.put(player, false);
		}
		return charged.get(player);
	}

}
