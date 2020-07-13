package org.t1ago.minecraft.riotlevels.RiotLevels;

import java.util.HashMap;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.t1ago.minecraft.riotlevels.RiotLevels.*;

public class DeathEvent implements Listener{

	public DeathEvent(RiotLevels plugin,ManageUsers mu) 
	{
		this.plugin=plugin;
		this.mu=mu;
	}
	
	@EventHandler
	public void onKill(EntityDeathEvent e)
	{
		
		if(e.getEntityType().equals(EntityType.PLAYER))
		{
			Player killer=e.getEntity().getKiller();
			Player dead=(Player)e.getEntity();
			
			if(killer.getWorld().getName().equals(plugin.getConfig().getString("world")) && dead.getWorld().getName().contentEquals(plugin.getConfig().getString("world")))
			{
				new DatabaseManagement(plugin).updatePVPData(killer,dead);
			}
			else 
			{
			
			
			
			}
		}
	}
	
	private static RiotLevels plugin;
	private ManageUsers mu;
	
}
