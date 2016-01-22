package bz.dcr.werbung.util;

import org.bukkit.Bukkit;

import bz.dcr.werbung.Main;

public class Util {

	public static void disablePlugin(Main plugin){
		Bukkit.getServer().getPluginManager().disablePlugin(plugin);
	}
	
}
