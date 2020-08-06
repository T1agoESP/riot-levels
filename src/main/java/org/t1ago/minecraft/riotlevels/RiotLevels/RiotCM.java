package org.t1ago.minecraft.riotlevels.RiotLevels;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.milkbowl.vault.economy.Economy;

public class RiotCM implements CommandExecutor{

	public RiotCM(RiotLevels plugin,ManageUsers mu) 
	{
		
		this.plugin=plugin;
		this.mu=mu;
	}
	
	public boolean onCommand(CommandSender sender,Command cmd,String label,String[] args)
	{
		if(sender instanceof Player)
		{
			User u=mu.getUser((Player)sender);
			
			if(label.equals("stats"))
			{
				if(args.length<1)
				{
					u.getPlayer().sendMessage("["+ChatColor.DARK_PURPLE+""+ChatColor.MAGIC+"123"+ChatColor.BLACK+"|"+ChatColor.GOLD+""+ChatColor.BOLD+"RiotLevels"+ChatColor.DARK_PURPLE+ChatColor.MAGIC+"123"+ChatColor.BLACK+"|"+ChatColor.BLUE+""+ChatColor.BOLD+"==>"+ChatColor.RED+u.getPlayer().getName()+ChatColor.RESET+"]");
					User usuario=mu.getUserStats(u.getPlayer());
				
					double puntosn=0;
					
					if(usuario.getLevel()<50)
					{
						puntosn=500-usuario.getExp();
					}
					else if(usuario.getLevel()>=50)
					{
						puntosn=1000-usuario.getExp();
					}
					
					if(usuario.getLevel()<100)
					{
					u.getPlayer().sendMessage(ChatColor.GOLD+""+ChatColor.MAGIC+"1"+ChatColor.BOLD+""+ChatColor.RED+"- Kills: "+ChatColor.GRAY+usuario.getKills());
					u.getPlayer().sendMessage(ChatColor.GOLD+""+ChatColor.MAGIC+"1"+ChatColor.BOLD+""+ChatColor.RED+"- Deaths: "+ChatColor.GRAY+usuario.getDeaths());
					u.getPlayer().sendMessage(ChatColor.GOLD+""+ChatColor.MAGIC+"1"+ChatColor.BOLD+""+ChatColor.RED+"- Killstreak: "+ChatColor.GRAY+usuario.getKillstreak());
					u.getPlayer().sendMessage(ChatColor.GOLD+""+ChatColor.MAGIC+"1"+ChatColor.BOLD+""+ChatColor.RED+"- Level: "+ChatColor.GRAY+usuario.getLevel());
					u.getPlayer().sendMessage(ChatColor.GOLD+""+ChatColor.MAGIC+"1"+ChatColor.BOLD+""+ChatColor.RED+"- Points: "+ChatColor.GRAY+usuario.getExp());
					u.getPlayer().sendMessage(ChatColor.GOLD+""+ChatColor.MAGIC+"1"+ChatColor.BOLD+""+ChatColor.RED+"- Points needed for next level: "+ChatColor.GRAY+puntosn);
					}
					else
					{	
					u.getPlayer().sendMessage(ChatColor.GOLD+""+ChatColor.MAGIC+"1"+ChatColor.BOLD+""+ChatColor.RED+"- Kills: "+ChatColor.GRAY+usuario.getKills());
					u.getPlayer().sendMessage(ChatColor.GOLD+""+ChatColor.MAGIC+"1"+ChatColor.BOLD+""+ChatColor.RED+"- Deaths: "+ChatColor.GRAY+usuario.getDeaths());
					u.getPlayer().sendMessage(ChatColor.GOLD+""+ChatColor.MAGIC+"1"+ChatColor.BOLD+""+ChatColor.RED+"- Killstreak: "+ChatColor.GRAY+usuario.getKillstreak());
					u.getPlayer().sendMessage(ChatColor.GOLD+""+ChatColor.MAGIC+"1"+ChatColor.BOLD+""+ChatColor.RED+"- Level: "+ChatColor.GRAY+usuario.getLevel());
					u.getPlayer().sendMessage(ChatColor.GOLD+""+ChatColor.MAGIC+"1"+ChatColor.BOLD+""+ChatColor.RED+"- Points: "+ChatColor.GRAY+usuario.getExp());
					}
				}
				else 
				{
					u.getPlayer().sendMessage("["+ChatColor.DARK_PURPLE+""+ChatColor.MAGIC+"123"+ChatColor.BLACK+"|"+ChatColor.GOLD+""+ChatColor.BOLD+"You have to pass some arguments"+ChatColor.DARK_PURPLE+ChatColor.MAGIC+"123"+ChatColor.BLACK+"|"+ChatColor.RESET+"]");
				}
			}
			else if(label.equals("riot"))
			{
				if(args.length>0)
				{
					if(args[0].equals("set") && u.getPlayer().hasPermission("riot.staff"))
					{
						if(args.length>1)
						{
							if(args[1].equals("world"))
							{
								if(args.length>2)
								{
									plugin.getConfig().set("world",args[2]);
									plugin.saveConfig();
									u.getPlayer().sendMessage("["+ChatColor.GOLD+ChatColor.BOLD+"RiotLevels"+ChatColor.RESET+"]"+ChatColor.GREEN+" The operation succeeded, now the plugin is active in the world: "+ChatColor.GRAY+args[2]);
								}
								else 
								{
									u.getPlayer().sendMessage("["+ChatColor.DARK_PURPLE+""+ChatColor.MAGIC+"123"+ChatColor.BLACK+"|"+ChatColor.GOLD+""+ChatColor.BOLD+"You have to pass some arguments"+ChatColor.DARK_PURPLE+ChatColor.MAGIC+"123"+ChatColor.BLACK+"|"+ChatColor.RESET+"]");
								}
							}
							else if(args[1].equals("kills"))
							{
								if(args.length>2)
								{
									String user=args[2];
									
									if(args.length>3)
									{
										int data=Integer.parseInt(args[3]);
										mu.updateStats("KILLS",data,u.getPlayer(),0,user);
										u.getPlayer().sendMessage("["+ChatColor.GOLD+ChatColor.BOLD+"RiotLevels"+ChatColor.RESET+"]"+ChatColor.GREEN+" The operation succeeded");
									}
									else 
									{
										u.getPlayer().sendMessage("["+ChatColor.DARK_PURPLE+""+ChatColor.MAGIC+"123"+ChatColor.BLACK+"|"+ChatColor.GOLD+""+ChatColor.BOLD+"You have to pass some arguments"+ChatColor.DARK_PURPLE+ChatColor.MAGIC+"123"+ChatColor.BLACK+"|"+ChatColor.RESET+"]");
									}
								}
								else 
								{
									u.getPlayer().sendMessage("["+ChatColor.DARK_PURPLE+""+ChatColor.MAGIC+"123"+ChatColor.BLACK+"|"+ChatColor.GOLD+""+ChatColor.BOLD+"You have to pass some arguments"+ChatColor.DARK_PURPLE+ChatColor.MAGIC+"123"+ChatColor.BLACK+"|"+ChatColor.RESET+"]");
								}
							}
							else if(args[1].equals("deaths"))
							{
								if(args.length>2)
								{
									String user=args[2];
									
									if(args.length>3)
									{
										int data=Integer.parseInt(args[3]);
										mu.updateStats("DEATHS",data,u.getPlayer(),0,user);
										u.getPlayer().sendMessage("["+ChatColor.GOLD+ChatColor.BOLD+"RiotLevels"+ChatColor.RESET+"]"+ChatColor.GREEN+" The operation succeeded");
									}
									else 
									{
										u.getPlayer().sendMessage("["+ChatColor.DARK_PURPLE+""+ChatColor.MAGIC+"123"+ChatColor.BLACK+"|"+ChatColor.GOLD+""+ChatColor.BOLD+"You have to pass some arguments"+ChatColor.DARK_PURPLE+ChatColor.MAGIC+"123"+ChatColor.BLACK+"|"+ChatColor.RESET+"]");
									}
								}
								else 
								{
									u.getPlayer().sendMessage("["+ChatColor.DARK_PURPLE+""+ChatColor.MAGIC+"123"+ChatColor.BLACK+"|"+ChatColor.GOLD+""+ChatColor.BOLD+"You have to pass some arguments"+ChatColor.DARK_PURPLE+ChatColor.MAGIC+"123"+ChatColor.BLACK+"|"+ChatColor.RESET+"]");
								}
							}
							else if(args[1].equals("killstreak"))
							{
								if(args.length>2)
								{
									String user=args[2];
									
									if(args.length>3)
									{
										int data=Integer.parseInt(args[3]);
										mu.updateStats("KILLSTREAK",data,u.getPlayer(),0,user);
										u.getPlayer().sendMessage("["+ChatColor.GOLD+ChatColor.BOLD+"RiotLevels"+ChatColor.RESET+"]"+ChatColor.GREEN+" The operation succeeded");
									}
									else 
									{
										u.getPlayer().sendMessage("["+ChatColor.DARK_PURPLE+""+ChatColor.MAGIC+"123"+ChatColor.BLACK+"|"+ChatColor.GOLD+""+ChatColor.BOLD+"You have to pass some arguments"+ChatColor.DARK_PURPLE+ChatColor.MAGIC+"123"+ChatColor.BLACK+"|"+ChatColor.RESET+"]");
									}
								}
								else 
								{
									u.getPlayer().sendMessage("["+ChatColor.DARK_PURPLE+""+ChatColor.MAGIC+"123"+ChatColor.BLACK+"|"+ChatColor.GOLD+""+ChatColor.BOLD+"You have to pass some arguments"+ChatColor.DARK_PURPLE+ChatColor.MAGIC+"123"+ChatColor.BLACK+"|"+ChatColor.RESET+"]");
								}
							}
							else if(args[1].equals("level"))
							{
								if(args.length>2)
								{
									String user=args[2];
									
									if(args.length>3)
									{
										int data=Integer.parseInt(args[3]);
										mu.updateStats("LEVEL",data,u.getPlayer(),0,user);
										u.getPlayer().sendMessage("["+ChatColor.GOLD+ChatColor.BOLD+"RiotLevels"+ChatColor.RESET+"]"+ChatColor.GREEN+" The operation succeeded");
									}
									else 
									{
										u.getPlayer().sendMessage("["+ChatColor.DARK_PURPLE+""+ChatColor.MAGIC+"123"+ChatColor.BLACK+"|"+ChatColor.GOLD+""+ChatColor.BOLD+"You have to pass some arguments"+ChatColor.DARK_PURPLE+ChatColor.MAGIC+"123"+ChatColor.BLACK+"|"+ChatColor.RESET+"]");
									}
								}
								else 
								{
									u.getPlayer().sendMessage("["+ChatColor.DARK_PURPLE+""+ChatColor.MAGIC+"123"+ChatColor.BLACK+"|"+ChatColor.GOLD+""+ChatColor.BOLD+"You have to pass some arguments"+ChatColor.DARK_PURPLE+ChatColor.MAGIC+"123"+ChatColor.BLACK+"|"+ChatColor.RESET+"]");
								}
							}
							else if(args[1].equals("exp"))
							{
								if(args.length>2)
								{
									String user=args[2];
									
									if(args.length>3)
									{
										double exp=Integer.parseInt(args[3]);
										int data=Integer.parseInt(args[3]);
										mu.updateStats("EXP",data,u.getPlayer(),exp,user);
										u.getPlayer().sendMessage("["+ChatColor.GOLD+ChatColor.BOLD+"RiotLevels"+ChatColor.RESET+"]"+ChatColor.GREEN+" The operation succeeded");
									}
									else 
									{
										u.getPlayer().sendMessage("["+ChatColor.DARK_PURPLE+""+ChatColor.MAGIC+"123"+ChatColor.BLACK+"|"+ChatColor.GOLD+""+ChatColor.BOLD+"You have to pass some arguments"+ChatColor.DARK_PURPLE+ChatColor.MAGIC+"123"+ChatColor.BLACK+"|"+ChatColor.RESET+"]");
									}
								}
								else 
								{
									u.getPlayer().sendMessage("["+ChatColor.DARK_PURPLE+""+ChatColor.MAGIC+"123"+ChatColor.BLACK+"|"+ChatColor.GOLD+""+ChatColor.BOLD+"You have to pass some arguments"+ChatColor.DARK_PURPLE+ChatColor.MAGIC+"123"+ChatColor.BLACK+"|"+ChatColor.RESET+"]");
								}
							}
						}
						else 
						{
							u.getPlayer().sendMessage("["+ChatColor.DARK_PURPLE+""+ChatColor.MAGIC+"123"+ChatColor.BLACK+"|"+ChatColor.GOLD+""+ChatColor.BOLD+"You have to pass some arguments"+ChatColor.DARK_PURPLE+ChatColor.MAGIC+"123"+ChatColor.BLACK+"|"+ChatColor.RESET+"]");
						}
					}
					else if(args[0].equals("ranking")) 
					{
						if(args.length>1)
						{
							try 
							{
							if(args[1].equalsIgnoreCase("exp") || args[1].equalsIgnoreCase("name") || args[1].equalsIgnoreCase("id"))
							{
							u.getPlayer().sendMessage("["+ChatColor.GOLD+ChatColor.BOLD+"RiotLevels"+ChatColor.RESET+"]"+ChatColor.RED+" The specified ranking could not be found");
							}
							else 
							{
								if(args.length<3)
								{
								mu.getRanking(u.getPlayer(),args[1].toUpperCase(),10);
								}
								else 
								{
									int nChosen=Integer.parseInt(args[2]);
									if(nChosen<10)
									{
										nChosen=10;
									}
									
									mu.getRanking(u.getPlayer(),args[1].toUpperCase(),nChosen);	
								}
							}
							}catch(Exception e) { u.getPlayer().sendMessage("["+ChatColor.GOLD+ChatColor.BOLD+"RiotLevels"+ChatColor.RESET+"]"+ChatColor.RED+" The search tag could not be found"); }
						}
						else 
						{
							u.getPlayer().sendMessage("["+ChatColor.GOLD+ChatColor.BOLD+"RiotLevels"+ChatColor.RESET+"]"+ChatColor.RED+" You have to pass a search tag, i.e 'kills'");
						}
					}
					else 
					{
						u.getPlayer().sendMessage("["+ChatColor.DARK_PURPLE+""+ChatColor.MAGIC+"123"+ChatColor.BLACK+"|"+ChatColor.GOLD+""+ChatColor.BOLD+"You don't have enough permissions to perform this command"+ChatColor.DARK_PURPLE+ChatColor.MAGIC+"123"+ChatColor.BLACK+"|"+ChatColor.RESET+"]");
					}
				}
				else 
				{
					u.getPlayer().sendMessage("["+ChatColor.DARK_PURPLE+""+ChatColor.MAGIC+"123"+ChatColor.BLACK+"|"+ChatColor.GOLD+""+ChatColor.BOLD+"You have to pass some arguments"+ChatColor.DARK_PURPLE+ChatColor.MAGIC+"123"+ChatColor.BLACK+"|"+ChatColor.RESET+"]");
				}
			}
			
		}
		return true;
	}
	
	private final RiotLevels plugin;
	private static ManageUsers mu;
}
