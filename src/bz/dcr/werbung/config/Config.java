package bz.dcr.werbung.config;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Config {
	public void setStandard(){
		FileConfiguration cfg = getFileConfiguration();
		
		cfg.options().copyDefaults(true);
		
		cfg.options().header("DeinWerbung Config");
		cfg.options().copyHeader(true);
		
		cfg.addDefault("Werbekosten", 100.0);
		cfg.addDefault("Warteschlangenlaenge", 20);
		
		try {
			cfg.save(getFile());
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	private static File getFile(){
		return new File("plugins/DeinWerbung", "config.yml");
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
}