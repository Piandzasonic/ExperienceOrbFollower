package vexsmp.plugin;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import vexsmp.plugin.Listeners.EntityDeathListener;

public class Main extends JavaPlugin{
	
	private final String line = "==============================";
	
	private static Main instance;
	
	@Override
	public void onEnable() {
		instance = this;
		getServer().getPluginManager().registerEvents(new EntityDeathListener(), this);
		
		getLogger().info(line);
		getLogger().info("Exprience Orb Follower Plugin Enabled");
		getLogger().info(line);
	}
	
	@Override
	public void onDisable() {
		HandlerList.unregisterAll(this);
		
		getLogger().info(line);
		getLogger().info("Exprience Orb Follower Plugin Disabled");
		getLogger().info(line);
	}
	
	public static Main getInstance() {
		return instance;
	}

}
