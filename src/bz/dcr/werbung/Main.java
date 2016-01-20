package bz.dcr.werbung;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import bz.dcr.werbung.config.Config;
import bz.dcr.werbung.config.Queue;
import net.md_5.bungee.api.ChatColor;
import net.milkbowl.vault.economy.Economy;

public class Main extends JavaPlugin {

	public final static String PREFIX = "[DeinWerbung] ";
	public final static String NO_PERMISSION = PREFIX + ChatColor.RED + "Dazu hast Du keine Berechtigung.";

	public static Economy econ = null;

	@Override
	public void onEnable() {
		// Configs
		new Config().setStandard();
		new Queue().setStandard();

		// Commands
		this.getCommand("werbung").setExecutor(new Commands());

		if (!setupEconomy()) {
			System.err.println(PREFIX + "Bitte installiere Vault um dieses Plugin zu nutzen");
			getServer().getPluginManager().disablePlugin(this);
			return;
		}
		System.out.println(PREFIX + "Erfolgreich aktiviert.");
	}

	@Override
	public void onDisable() {
		System.out.println(PREFIX + "Erfolgreich deaktiviert.");
	}

	private boolean setupEconomy() {
		if (getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}
		RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			return false;
		}
		econ = rsp.getProvider();
		return econ != null;
	}

}