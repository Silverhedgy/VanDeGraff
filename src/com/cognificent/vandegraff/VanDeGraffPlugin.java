package com.cognificent.vandegraff;

import java.io.File;
import java.util.logging.Logger;

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
	
	private final VanDeGraffBlockListener blockListener = new VanDeGraffBlockListener(this);
	private final VanDeGraffVehicleListener vehicleListener = new VanDeGraffVehicleListener(this);
	private final VanDeGraffEntityListener entityListener = new VanDeGraffEntityListener(this);
	

	@Override
	public void onDisable() {
		log.info("ThunderArrow disabled.");
	}

	@Override
	public void onEnable() {
        // Register our events
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvent(Event.Type.BLOCK_DAMAGE, blockListener, Priority.Normal, this);
        pm.registerEvent(Event.Type.VEHICLE_MOVE, vehicleListener, Priority.Normal, this);
        pm.registerEvent(Event.Type.ENTITY_DAMAGE, entityListener, Priority.Normal, this);

        // EXAMPLE: Custom code, here we just output some info so we can check all is well
        PluginDescriptionFile pdfFile = this.getDescription();
        log.info( pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!" );
		
	}

}
