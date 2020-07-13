package org.t1ago.minecraft.riotlevels.RiotLevels;

import java.util.UUID;

import org.bukkit.Statistic;
import org.bukkit.entity.Player;

public class User {

	public User(Player p)
	{
		player=p;
		kills=p.getStatistic(Statistic.PLAYER_KILLS);
		deaths=p.getStatistic(Statistic.DEATHS);
	}
	
	public User(UUID id,String nombre,int kills,int deaths,int killstreak,int level,double exp)
	{
		this.id=id;
		this.nombre=nombre;
		this.kills=kills;
		this.deaths=deaths;
		this.killstreak=killstreak;
		this.level=level;
		this.exp=exp;
	}
	
	public Player getPlayer() 
	{
		return this.player;
	}
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public double getExp() {
		return exp;
	}

	public void setExp(double exp) {
		this.exp = exp;
	}

	public int getKills() {
		return kills;
	}

	public void setKills(int kills) {
		this.kills = kills;
	}

	public int getDeaths() {
		return deaths;
	}

	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}

	public int getKillstreak() {
		return killstreak;
	}

	public void setKillstreak(int killstreak) {
		this.killstreak = killstreak;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}



	private Player player;	
	private int level=0,killstreak=0;
	private int kills;
	private double exp=0;
	private int deaths;
	private UUID id;
	private String nombre;
}
