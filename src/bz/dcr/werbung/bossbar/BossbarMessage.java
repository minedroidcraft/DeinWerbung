package bz.dcr.werbung.bossbar;

import java.util.UUID;

import org.bukkit.entity.Player;

public class BossbarMessage {

	private String message;
	private UUID uuid;
	private int duration = 40;
	
	public UUID getUUID() {
		return uuid;
	}
	
	public BossbarMessage(Player p, String message) {
		setUuid(p.getUniqueId());
		setMessage(message);
	}
	
	public BossbarMessage(UUID uuid, String message) {
		setUuid(uuid);
		setMessage(message);
	}
	
	public BossbarMessage(Player p, String message, int duration) {
		setUuid(p.getUniqueId());
		setMessage(message);
		setDuration(duration);
	}
	
	public BossbarMessage(UUID uuid, String message, int duration) {
		setUuid(uuid);
		setMessage(message);
		setDuration(duration);
	}
	
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	
	
}