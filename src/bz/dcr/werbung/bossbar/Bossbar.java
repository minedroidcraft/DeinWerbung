package bz.dcr.werbung.bossbar;

import java.util.ArrayList;
import java.util.UUID;

public class Bossbar implements Runnable{

	private ArrayList<BossbarMessage> barList= new ArrayList<>();
	private BossbarMessage activeMessage = null;
	
	public ArrayList<BossbarMessage> getBarList() {
		return barList;
	}

	public void setBarMap(ArrayList<BossbarMessage> barMap) {
		this.barList = barMap;
	}
	
	public boolean isPlayerInBarMap(UUID uuid){
		for(BossbarMessage bbm : getBarList()){
			if(bbm.getUUID().equals(uuid)){
				return true;
			}
		}
		return false;
	}
	
	public boolean isEmpty(){
		return getBarList().isEmpty();
	}

	@Override
	public void run() {
		while(!isEmpty()){
			if(activeMessage == null){
				BossbarMessage bbm = getBarList().get(0);
				setActiveMessage(new BossbarMessage(bbm.getUUID(), bbm.getMessage()));
			}
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public BossbarMessage getActiveMessage() {
		return activeMessage;
	}

	public void setActiveMessage(BossbarMessage activeMessage) {
		this.activeMessage = activeMessage;
	}
	
}