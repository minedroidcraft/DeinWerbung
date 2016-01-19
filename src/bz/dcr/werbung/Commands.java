package bz.dcr.werbung;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.confuser.barapi.BarAPI;

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
						msg.substring(0, msg.length()-1);
						
						BarAPI.removeBar(p);
						BarAPI.setMessage(p, msg, 20);
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