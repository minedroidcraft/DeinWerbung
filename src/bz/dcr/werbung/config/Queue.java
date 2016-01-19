package bz.dcr.werbung.config;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Queue {
	
	public void setStandard(){
		FileConfiguration cfg = getFileConfiguration();
		
		cfg.options().copyDefaults(true);
		
		cfg.options().header("Warteschlange der Werbeschaltungen");
		cfg.options().copyHeader(true);
		
		cfg.addDefault("Werbekosten", null);

		try {
			cfg.save(getFile());
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	private static File getFile(){
		return new File("plugins/DeinWerbung", "queue.yml");
	}
	
	private static FileConfiguration getFileConfiguration(){
		return YamlConfiguration.loadConfiguration(getFile());
	}
	
	public static double getPrice(){
		return getFileConfiguration().getDouble("Werbekosten");
	}
	
	public static int getQueueLength(){
		return getFileConfiguration().getInt("Warteschlangenlaenge");
	}
	
	public static void addAdvertising(Player p, String msg){
		getFileConfiguration().set(p.getUniqueId().toString(), msg);
	}
}