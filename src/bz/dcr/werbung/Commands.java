package bz.dcr.werbung;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import bz.dcr.werbung.bossbar.Bossbar;
import bz.dcr.werbung.bossbar.BossbarMessage;
import bz.dcr.werbung.config.Config;
import bz.dcr.werbung.config.Queue;

public class Commands implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(cmd.getName().equalsIgnoreCase("werbung")){
			if(sender instanceof Player){
				Player p = (Player)sender;
				if(p.hasPermission("werbung.start")){
					if(args.length >= 1){
						
						String msg = "";
						
						for(String arg : args){
							msg += arg + " ";
							if(msg.length() > 64){
								p.sendMessage(Main.PREFIX + "Die Werbung darf maximal 64 Zeichen lang sein.");
								return true;
							}
						}
						msg = msg.substring(0, msg.length()-1);
						
						if(Queue.isPlayerInQueue(p)){
							p.sendMessage(Main.PREFIX + "Du bist bereits in der Warteschlange.");
							return true;
						}
						else{
							if(Queue.isEmpty()){
								Queue.addAdvertising(new BossbarMessage(p, msg));
								new Bossbar(Config.getQueueLength()).start();
							}
							else{
								Queue.addAdvertising(new BossbarMessage(p, msg));
							}						
						}
						
						
//						if(!Main.bossbar.isEmpty()){
//							
//						}
						
						
//						BarAPI.removeBar(p);
//						BarAPI.setMessage(p, msg, 20);
						return true;
					}
					else{
						return false;
					}
				}
				else{
					p.sendMessage(Main.NO_PERMISSION);
					return true;
				}
			}
			else{
				System.out.println("Dieser Befehl ist derzeit nicht für die Konsole geeignet");
			}
			
			
			return true;
		}	
		
		return false;
	}
}