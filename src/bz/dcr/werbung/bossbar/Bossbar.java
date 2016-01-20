package bz.dcr.werbung.bossbar;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import bz.dcr.werbung.config.Queue;
import me.confuser.barapi.BarAPI;

public class Bossbar implements Runnable {

	private Thread thread;
	private boolean running;
	private int duration;
	
	public Bossbar(int duration) {
		this.thread = new Thread(this);
		this.duration = duration;
	}

	public void start() {
		this.running = true;
		this.thread.start();
	}

	@SuppressWarnings("deprecation")
	public void stop() {
		this.running = false;
		this.thread.stop();
	}

	@Override
	public void run() {
		while (running) {
			//Information
			BossbarMessage bbm = Queue.getNext();
			OfflinePlayer p = Bukkit.getOfflinePlayer(bbm.getUUID());
			String message = bbm.getMessage();
			Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();
			//---------//			
			for(Player player: onlinePlayers){
				BarAPI.removeBar(player);
				BarAPI.setMessage(player, "Werbung von " + p.getName());
			}
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
			for(Player player: onlinePlayers){
				BarAPI.removeBar(player);
				BarAPI.setMessage(player, message, duration);
			}		
			
			try {
				Thread.sleep(1000*this.duration);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Queue.remove(bbm.getUUID());
			if(Queue.isEmpty()){
				this.stop();
			}
		}
	}

}