package org.t1ago.minecraft.riotlevels.RiotLevels;

import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ManageUsers implements Listener{

	public ManageUsers(RiotLevels plugin)
	{
		this.plugin=plugin;
	}
	public ManageUsers() {}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e)
	{
		dm=new DatabaseManagement(plugin);
		Player p=e.getPlayer();
		if(!p.hasPlayedBefore())
		{
			dm.registrarUsuario(p.getUniqueId().toString(),p.getName(),0,0,0,0,0.0);
		}
		else 
		{
			dm.obtenUsuario(p,estadisticaArray);
		}
	}
	
	public User getUser(Player p) 
	{
		return user=new User(p);
	}
	
	public User getUser(String id,String nombre,int kills,int deaths,int killstreak,int level,double exp)
	{
		UUID idfinal=UUID.fromString(id);
		return user=new User(idfinal,nombre,kills,deaths,killstreak,level,exp);
	}
	
	/*public HashMap<String,User> getUserList() {
		return UserList;
	}*/
	
	public DatabaseManagement getDM() 
	{
		return this.dm;
	}
	
	public User getUserStats(Player p) 
	{
		try 
		{
		return new DatabaseManagement(plugin).getStats(p);
		}catch(Exception e) { p.sendMessage("["+ChatColor.GOLD+""+ChatColor.BOLD+"RiotLevels"+ChatColor.RESET+"]"+ChatColor.RED+" Error encontrando tus estadisticas"); }
		return null;
	}
	
	public void updateStats(String action,int kills,Player p,double exp,String nombre)
	{
		try 
		{
			new DatabaseManagement(plugin).updateUserStats(action,kills,exp,nombre);
		}catch(Exception e) { p.sendMessage("["+ChatColor.GOLD+""+ChatColor.BOLD+"RiotLevels"+ChatColor.RESET+"]"+ChatColor.RED+" Error actualizando los datos"); }
	}
	
	public void getRanking(Player p,String action,int max)
	{
		try 
		{
			new DatabaseManagement(plugin).getRanking(action,p,max);
		}catch(Exception e) {p.sendMessage("["+ChatColor.GOLD+""+ChatColor.BOLD+"RiotLevels"+ChatColor.RESET+"]"+ChatColor.RED+" No se ha encontrado el ranking especificado");  }
	}
	
	private static RiotLevels plugin;
	private static User user;
	//private HashMap<String,User> UserList=new HashMap<String,User>();
	private Object[] estadisticaArray;
	private DatabaseManagement dm;
}
