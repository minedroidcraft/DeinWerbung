package bz.dcr.werbung;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import bz.dcr.werbung.bossbar.Bossbar;
import bz.dcr.werbung.config.Config;
import bz.dcr.werbung.config.Queue;
import bz.dcr.werbung.util.Messages;
import bz.dcr.werbung.util.Util;
import net.milkbowl.vault.economy.Economy;

public class Main extends JavaPlugin {

	public static Economy econ = null;

	@Override
	public void onEnable() {
		// Configs
		new Config().setStandard();
		new Queue().setStandard();

		// Commands
		this.getCommand("werbung").setExecutor(new Commands());

		if (!setupEconomy()) {
			System.err.println(Messages.PREFIX + "Bitte installiere Vault um dieses Plugin zu nutzen");
			Util.disablePlugin(this);
			return;
		}
		if (getServer().getPluginManager().getPlugin("BarAPI") == null) {
			System.err.println(Messages.PREFIX + "Bitte installiere BarAPI um dieses Plugin zu nutzen");
			Util.disablePlugin(this);
		}
		System.out.println(Messages.PREFIX + "Erfolgreich aktiviert.");
		
		if(!Queue.isEmpty()){
			System.out.println("Neue Bossbar");
			new Bossbar(Config.getQueueLength()).start();
		}
	}

	@Override
	public void onDisable() {
		System.out.println(Messages.PREFIX + "Erfolgreich deaktiviert.");
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