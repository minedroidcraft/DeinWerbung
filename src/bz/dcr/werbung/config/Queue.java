package bz.dcr.werbung.config;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import bz.dcr.werbung.bossbar.BossbarMessage;

public class Queue {

	public void setStandard() {
		FileConfiguration cfg = getFileConfiguration();

		cfg.options().copyDefaults(true);

		cfg.options().header("Warteschlange der Werbeschaltungen");
		cfg.options().copyHeader(true);
		cfg.addDefault("queue", new HashMap<String, String>());
		try {
			cfg.save(getFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static File getFile() {
		return new File("plugins/DeinWerbung", "queue.yml");
	}

	private static FileConfiguration getFileConfiguration() {
		return YamlConfiguration.loadConfiguration(getFile());
	}

	public static void addAdvertising(BossbarMessage bbm) {
		FileConfiguration cfg = getFileConfiguration();
		Map<String, Object> bbmMap = new HashMap<String, Object>();
		
		try {
			bbmMap = cfg.getConfigurationSection("queue").getValues(false);
		} catch (NullPointerException e) {
		}
		
		bbmMap.put(bbm.getUUID().toString(), bbm.getMessage());
		cfg.createSection("queue", bbmMap);

		try {
			cfg.save(getFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void setAdvertising(BossbarMessage...bbms){
		FileConfiguration cfg = getFileConfiguration();
		Map<String, Object> bbmMap = new HashMap<String, Object>();
		
		for(BossbarMessage bbm : bbms){
			bbmMap.put(bbm.getUUID().toString(), bbm.getMessage());
		}
		
		cfg.createSection("queue", bbmMap);
		try {
			cfg.save(getFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void setAdvertising(Map<String, Object> bbmMap){
		FileConfiguration cfg = getFileConfiguration();		
		cfg.createSection("queue", bbmMap);
		try {
			cfg.save(getFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean isPlayerInQueue(Player p){
		FileConfiguration cfg = getFileConfiguration();
		Map<String, Object> bbmMap = new HashMap<String, Object>();
		
		try {
			bbmMap = cfg.getConfigurationSection("queue").getValues(false);
		} catch (NullPointerException e) {
			return false;
		}
		
		if(bbmMap.containsKey(p.getUniqueId().toString())){
			return true;
		}
		return false;		
	}
	
	public static boolean isEmpty(){
		FileConfiguration cfg = getFileConfiguration();
		Map<String, Object> bbmMap = new HashMap<String, Object>();
		
		try {
			bbmMap = cfg.getConfigurationSection("queue").getValues(false);
		} catch (NullPointerException e) {
			return true;
		}		
		return bbmMap.isEmpty();
	}
	
	public static void remove(UUID uuid){
		if(!isEmpty()){
			FileConfiguration cfg = getFileConfiguration();
			Map<String, Object> bbmMap = cfg.getConfigurationSection("queue").getValues(false);
			bbmMap.remove(uuid.toString());
			setAdvertising(bbmMap);
		}
	}
	
	public static BossbarMessage getNext(){
		if(isEmpty()){
			return null;
		}
		else{
			FileConfiguration cfg = getFileConfiguration();
			Map<String, Object> bbmMap = cfg.getConfigurationSection("queue").getValues(false);
			Entry<String, Object> entry = bbmMap.entrySet().iterator().next();
			System.out.println(UUID.fromString(entry.getKey()));
			System.out.println(String.valueOf(entry.getValue()));
			return new BossbarMessage(UUID.fromString(entry.getKey()), String.valueOf(entry.getValue()));
		}	
		
	}

}