package org.t1ago.minecraft.riotlevels.RiotLevels;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;

public class DatabaseManagement {

	public DatabaseManagement(RiotLevels plugin) 
	{
		this.plugin=plugin;
		eco=plugin.getEco();
	}
	
	public void registrarUsuario(String id,String nombre,int kills,int deaths,int killstreak,int level,double exp) 
	{
		String bbdd=plugin.getConfig().getString("DB-url");
		String user=plugin.getConfig().getString("User");
		String pass=plugin.getConfig().getString("Pass");
		String query="insert into usuarios(ID,NAME,KILLS,DEATHS,KILLSTREAK,LEVEL,EXP) values('"+id+"','"+nombre+"','"+kills+"','"+deaths+"','"+killstreak+"','"+level+"','"+exp+"')";
		
		try 
		{
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection con=DriverManager.getConnection(bbdd,user,pass);
		Statement st=con.createStatement();
		st.executeUpdate(query);
		
		}catch(SQLException e){ plugin.getLogger().info("An error has occurred when trying to access the database");
		}catch(ClassNotFoundException e) { plugin.getLogger().info("MySQL driver not found"); }
	}
	
	public void obtenUsuario(final Player p, Object[] estadisticaArray) 
	{
		String bbdd=plugin.getConfig().getString("DB-url");
		String user=plugin.getConfig().getString("User");
		String pass=plugin.getConfig().getString("Pass");
		final String query="select * from usuarios where ID=?";
		estadisticaArray=new Object[7];
		
		try 
		{
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection con=DriverManager.getConnection(bbdd,user,pass);
		PreparedStatement st=con.prepareStatement(query);
		st.setString(1,p.getUniqueId().toString());
		ResultSet rs=st.executeQuery();
		
		if(!rs.absolute(1))
		{
			registrarUsuario(p.getUniqueId().toString(),p.getName(),0,0,0,0,0.0);
		}	
		
		con.close();
		rs.close();
			/*while(rs.next())
			{
				/*estadisticaArray[0]=rs.getString(1);
				estadisticaArray[1]=rs.getString(2);
				estadisticaArray[2]=rs.getInt(3);
				estadisticaArray[3]=rs.getInt(4);
				estadisticaArray[4]=rs.getInt(5);
				estadisticaArray[5]=rs.getInt(6);
				estadisticaArray[6]=rs.getDouble(7);*/
				
				
				/*String tempID=rs.getString(1);
				finalID=UUID.fromString(tempID);
				nombre=rs.getString(2);
				kills=rs.getInt(3);
				deaths=rs.getInt(4);
				killstreak=rs.getInt(5);
				level=rs.getInt(6);
				exp=rs.getDouble(7);
			}*/
			
			/*String tempID=(String)estadisticaArray[0];
			UUID finalID=UUID.fromString(tempID);
			String nombre=(String)estadisticaArray[1];
			int kills=(Integer)estadisticaArray[2];
			int deaths=(Integer)estadisticaArray[3];
			int killstreak=(Integer)estadisticaArray[4];
			int level=(Integer)estadisticaArray[5];
			double exp=(Double)estadisticaArray[6];*/
		
		}catch(SQLException e){ plugin.getLogger().info("An error has occurred when trying to access the database");
		}catch(ClassNotFoundException e) { plugin.getLogger().info("MySQL driver not found");}
		
	}
	
	/*public void updateKillstreak(Player killer,Player dead) 
	{
		String ddbb=plugin.getConfig().getString("DB-url");
		String user=plugin.getConfig().getString("User");
		String pass=plugin.getConfig().getString("Pass");
		String query="update usuarios set KILLSTREAK=? where ID=?";
		String queryB="update usuarios set KILLSTREAK=0 where ID=?";
	}*/
	
	/*public void updatePVPData(Player killer,Player dead) 
	{
		
		for(User e: UserList.values()) 
		{
			Player current=e.getPlayer();
			if(current.getUniqueId().equals(killer.getUniqueId())) 
			{
				for(String key: UserList.keySet()) 
				{
					if(UserList.get(key).equals(e))
					{
						UUID id=current.getUniqueId();
						String nombre=current.getName();
						int kills=e.getKills();
						int finalkills=kills++;
						int deaths=e.getDeaths();
						int killstreakin=e.getKillstreak();;
						int killstreak=killstreakin++;
						int level=e.getLevel();
						double exp=e.getExp();
						
						P1=(1.0f/(1.0f+(float)Math.pow(10,((level2-level)/400))));
						P2=(1.0f/(1.0f+(float)Math.pow(10,((level-level2)/400))));
						
						int k=150;
						
						expplus1=150*(1-P1);
						expminus2=150*(0-P1);
						
						if(level<1)
						{
							exp=exp+150;
							current.sendMessage(("["+ChatColor.DARK_PURPLE+""+ChatColor.MAGIC+"123"+ChatColor.BLACK+"|"+ChatColor.GOLD+""+ChatColor.BOLD+"Statistics Plugin"+ChatColor.DARK_PURPLE+ChatColor.MAGIC+"123"+ChatColor.BLACK+"|"+ChatColor.BLUE+ChatColor.RESET+"]"));
							current.sendMessage(ChatColor.GOLD+""+ChatColor.MAGIC+"1"+ChatColor.RESET+ChatColor.RED+"Enhorabuena! Teniendo un "+ChatColor.GRAY+(Math.round(P1)*100)+ChatColor.RED+"% de posibilidades de ganar basado en tu nivel has "+ChatColor.GREEN+"GANADO");
							current.sendMessage(ChatColor.GOLD+""+ChatColor.MAGIC+"1"+ChatColor.RESET+ChatColor.RED+"Has recibido "+ChatColor.GREEN+"+"+150.0+ChatColor.RED+" puntos!");	
						}
						else if(level==100)
						{
							exp=1000;
							current.sendMessage(("["+ChatColor.DARK_PURPLE+""+ChatColor.MAGIC+"123"+ChatColor.BLACK+"|"+ChatColor.GOLD+""+ChatColor.BOLD+"Statistics Plugin"+ChatColor.DARK_PURPLE+ChatColor.MAGIC+"123"+ChatColor.BLACK+"|"+ChatColor.BLUE+ChatColor.RESET+"]"));
							current.sendMessage(ChatColor.GOLD+""+ChatColor.MAGIC+"1"+ChatColor.RESET+ChatColor.RED+"Enhorabuena! Teniendo un "+ChatColor.GRAY+(Math.round(P1)*100)+ChatColor.RED+"% de posibilidades de ganar basado en tu nivel has "+ChatColor.GREEN+"GANADO");
						}
						else 
						{
							exp=exp+expplus1;
							
							current.sendMessage(("["+ChatColor.DARK_PURPLE+""+ChatColor.MAGIC+"123"+ChatColor.BLACK+"|"+ChatColor.GOLD+""+ChatColor.BOLD+"Statistics Plugin"+ChatColor.DARK_PURPLE+ChatColor.MAGIC+"123"+ChatColor.BLACK+"|"+ChatColor.BLUE+ChatColor.RESET+"]"));
							current.sendMessage(ChatColor.GOLD+""+ChatColor.MAGIC+"1"+ChatColor.RESET+ChatColor.RED+"Enhorabuena! Teniendo un "+ChatColor.GRAY+(Math.round(P1)*100)+ChatColor.RED+"% de posibilidades de ganar basado en tu nivel has "+ChatColor.GREEN+"GANADO");
							current.sendMessage(ChatColor.GOLD+""+ChatColor.MAGIC+"1"+ChatColor.RESET+ChatColor.RED+"Has recibido "+ChatColor.GREEN+"+"+Math.round(expplus1)+ChatColor.RED+" puntos!");	
						}
						
						if(level>0)
						{
							if(level<=50)
							{
								if(exp>=500)
								{
									exp=0;
									level++;
									killer.sendMessage("["+ChatColor.BOLD+ChatColor.GOLD+"RiotLevels"+ChatColor.RESET+"] "+ChatColor.RED+"Felicidades, has subido al nivel "+ChatColor.GREEN+level);
								}
							}
							else if(level>50 && level<100)
							{
								if(exp>=1000)
								{
									exp=0;
									level++;
									killer.sendMessage("["+ChatColor.BOLD+ChatColor.GOLD+"RiotLevels"+ChatColor.RESET+"] "+ChatColor.RED+"Felicidades, has subido al nivel "+ChatColor.GREEN+level);
								}
							}
							else if(level==100)
							{
								exp=1000;
								level=100;
								
							}
						}
						else 
						{
							if(exp>=500)
							{
								exp=0;
								level++;
								killer.sendMessage("["+ChatColor.BOLD+ChatColor.GOLD+"RiotLevels"+ChatColor.RESET+"] "+ChatColor.RED+"Felicidades, has subido al nivel "+ChatColor.GREEN+level);
								killer.sendMessage("["+ChatColor.BOLD+ChatColor.GOLD+"RiotLevels"+ChatColor.RESET+"] "+ChatColor.RED+"Eso significa que ahora ganar vas a ganar puntos basados en un sistema de ELO, pero a su vez significa que a partir de ahora puedes perder puntos!");
							}
						}
						
						UserList.replace(key,new User(id,nombre,finalkills,deaths,killstreak,level,exp));
					}
				}
			}
			
			if(current.getUniqueId().equals(dead.getUniqueId())) 
			{
				for(String key2: UserList.keySet()) 
				{
					if(UserList.get(key2).equals(e))
					{
						UUID id2=current.getUniqueId();
						String nombre2=current.getName();
						int kills2=e.getKills();
						int deaths2=e.getDeaths();
						int deathsfinal=deaths2++;
						int killstreak2=0;
						level2=e.getLevel();
						exp2=e.getExp();
						
						if(level2<0)
						{
							current.sendMessage(("["+ChatColor.DARK_PURPLE+""+ChatColor.MAGIC+"123"+ChatColor.BLACK+"|"+ChatColor.GOLD+""+ChatColor.BOLD+"Statistics Plugin"+ChatColor.DARK_PURPLE+ChatColor.MAGIC+"123"+ChatColor.BLACK+"|"+ChatColor.BLUE+ChatColor.RESET+"]"));
							current.sendMessage(ChatColor.GOLD+""+ChatColor.MAGIC+"1"+ChatColor.RESET+ChatColor.RED+"Lo sentimos! Teniendo un "+ChatColor.GRAY+(Math.round(P1)*100)+ChatColor.RED+"% de posibilidades de ganar basado en tu nivel has "+ChatColor.DARK_RED+"PERDIDO");
							current.sendMessage(ChatColor.GOLD+""+ChatColor.MAGIC+"1"+ChatColor.RESET+ChatColor.RED+"Has perdido "+ChatColor.DARK_RED+"-"+0.0+ChatColor.RED+" puntos!");	
						}
						else 
						{
							exp2=exp2+expminus2;
							
							current.sendMessage(("["+ChatColor.DARK_PURPLE+""+ChatColor.MAGIC+"123"+ChatColor.BLACK+"|"+ChatColor.GOLD+""+ChatColor.BOLD+"Statistics Plugin"+ChatColor.DARK_PURPLE+ChatColor.MAGIC+"123"+ChatColor.BLACK+"|"+ChatColor.BLUE+ChatColor.RESET+"]"));
							current.sendMessage(ChatColor.GOLD+""+ChatColor.MAGIC+"1"+ChatColor.RESET+ChatColor.RED+"Mejor suerte la siguiente vez! Teniendo un "+ChatColor.GRAY+(Math.round(P1)*100)+ChatColor.RED+"% de posibilidades de ganar basado en tu nivel has "+ChatColor.DARK_RED+"PERDIDO");
							current.sendMessage(ChatColor.GOLD+""+ChatColor.MAGIC+"1"+ChatColor.RESET+ChatColor.RED+"Has perdido "+ChatColor.DARK_RED+expminus2+ChatColor.RED+" puntos!");
						}
						
						
						if(level2>0)
						{
							if(exp2<0)
							{
								
								if(level2<=50)
								{
									level2--;
									exp2=500+expminus2;
									dead.sendMessage("["+ChatColor.BOLD+ChatColor.GOLD+"RiotLevels"+ChatColor.RESET+"] "+ChatColor.RED+"Lo sentimos, has bajado a nivel "+ChatColor.DARK_RED+level2);
								}
								else
								{
									level2--;
									exp2=1000+expminus2;
									dead.sendMessage("["+ChatColor.BOLD+ChatColor.GOLD+"RiotLevels"+ChatColor.RESET+"] "+ChatColor.RED+"Lo sentimos, has bajado a nivel "+ChatColor.DARK_RED+level2);
								}
							}
						}
						
						UserList.replace(key2,new User(id2,nombre2,kills2,deathsfinal,killstreak2,level2,exp2));
						
					}
				}
			}
		}
	}*/
	
	public void updatePVPData(Player killer,Player dead) 
	{
		String ddbb=plugin.getConfig().getString("DB-url");
		String user=plugin.getConfig().getString("User");
		String pass=plugin.getConfig().getString("Pass");
		String queryget="select * from usuarios where ID=?";
		String queryset="update usuarios set KILLS=?,DEATHS=?,KILLSTREAK=?,LEVEL=?,EXP=? where ID=?";
		
		try 
		{
			Connection con=DriverManager.getConnection(ddbb,user,pass);
			PreparedStatement ps=con.prepareStatement(queryget);
			
			ps.setString(1,killer.getUniqueId().toString());
			
			ResultSet rs=ps.executeQuery();
			
			rs.next();
			
			id=killer.getUniqueId();
			nombre=killer.getName();
			kills=rs.getInt("KILLS");
			deaths=rs.getInt("DEATHS");
			killstreak=rs.getInt("KILLSTREAK");
			level=rs.getInt("LEVEL");
			exp=rs.getDouble("EXP");
			
			ps.setString(1,dead.getUniqueId().toString());
			
			rs=ps.executeQuery();
			
			rs.next();
			
			id2=dead.getUniqueId();
			nombre2=dead.getName();
			kills2=rs.getInt("KILLS");
			deaths2=rs.getInt("DEATHS");
			killstreak2=rs.getInt("KILLSTREAK");
			level2=rs.getInt("LEVEL");
			exp2=rs.getDouble("EXP");
			
			int v=plugin.getConfig().getInt("factor_v");
			
			P1=1.0f * 1.0f / (1 + 1.0f *  
	                (float)(Math.pow(10, 1.0f *  
	                        (level2*v - level*v) / 400)));
			
			P2=1.0f * 1.0f / (1 + 1.0f *  
	                (float)(Math.pow(10, 1.0f *  
	                        (level*v - level2*v) / 400)));
			
			int k=plugin.getConfig().getInt("factor_k");
			
			expplus1=round(k*(1-round(P1,2)),2);
			expminus2=round(k*(0-round(P2,2)),2);
			
			if(level<1)
			{
				exp=exp+150;
				killer.sendMessage(("["+ChatColor.DARK_PURPLE+""+ChatColor.MAGIC+"123"+ChatColor.BLACK+"|"+ChatColor.GOLD+""+ChatColor.BOLD+"RiotLevels"+ChatColor.DARK_PURPLE+ChatColor.MAGIC+"123"+ChatColor.BLACK+"|"+ChatColor.BLUE+ChatColor.RESET+"]"));
				killer.sendMessage(ChatColor.GOLD+""+ChatColor.MAGIC+"1"+ChatColor.RESET+ChatColor.RED+"You've received "+ChatColor.GREEN+"+"+150.0+ChatColor.RED+" points!");	
			}
			else if(level==100)
			{
				exp+=expplus1;
				killer.sendMessage(("["+ChatColor.DARK_PURPLE+""+ChatColor.MAGIC+"123"+ChatColor.BLACK+"|"+ChatColor.GOLD+""+ChatColor.BOLD+"RiotLevels"+ChatColor.DARK_PURPLE+ChatColor.MAGIC+"123"+ChatColor.BLACK+"|"+ChatColor.BLUE+ChatColor.RESET+"]"));
				killer.sendMessage(ChatColor.GOLD+""+ChatColor.MAGIC+"1"+ChatColor.RESET+ChatColor.RED+"Congrats! Having a chance of "+ChatColor.GRAY+round(P1*100,2)+ChatColor.RED+"% of winning");
				killer.sendMessage(ChatColor.GOLD+""+ChatColor.MAGIC+"1"+ChatColor.RESET+ChatColor.RED+"based on your level, you've "+ChatColor.GREEN+"WON");
			}
			else 
			{
				exp=exp+expplus1;
				
				killer.sendMessage(("["+ChatColor.DARK_PURPLE+""+ChatColor.MAGIC+"123"+ChatColor.BLACK+"|"+ChatColor.GOLD+""+ChatColor.BOLD+"RiotLevels"+ChatColor.DARK_PURPLE+ChatColor.MAGIC+"123"+ChatColor.BLACK+"|"+ChatColor.BLUE+ChatColor.RESET+"]"));
				killer.sendMessage(ChatColor.GOLD+""+ChatColor.MAGIC+"1"+ChatColor.RESET+ChatColor.RED+"Congrats! Having a chance of "+ChatColor.GRAY+round(P1*100,2)+ChatColor.RED+"% of winning");
				killer.sendMessage(ChatColor.GOLD+""+ChatColor.MAGIC+"1"+ChatColor.RESET+ChatColor.RED+"based on your level, you've "+ChatColor.GREEN+"WON");
				killer.sendMessage(ChatColor.GOLD+""+ChatColor.MAGIC+"1"+ChatColor.RESET+ChatColor.RED+"You've received "+ChatColor.GREEN+"+"+round(expplus1,2)+ChatColor.RED+" points!");	
			}
			
			if(level>0)
			{
				if(level<=50)
				{
					if(exp>=500)
					{
						exp=exp-500;
						level++;
						killer.sendMessage("["+ChatColor.GOLD+ChatColor.BOLD+"RiotLevels"+ChatColor.RESET+"] "+ChatColor.RED+"Felicidades, has subido al nivel "+ChatColor.GREEN+level);
						cantidad=level*Integer.parseInt(plugin.getConfig().getString("factor_p"));
						EconomyResponse er=eco.depositPlayer(killer,cantidad);
						
						if(er.transactionSuccess())
						{
							killer.sendMessage("["+ChatColor.GOLD+ChatColor.BOLD+"RiotLevels"+ChatColor.RESET+"] "+ChatColor.GREEN+"Has recibido "+ChatColor.GRAY+cantidad+"$"+ChatColor.GREEN+" debido a tu reciente subida de nivel");
						}
						else 
						{
							killer.sendMessage("["+ChatColor.GOLD+ChatColor.BOLD+"RiotLevels"+ChatColor.RESET+"] "+ChatColor.RED+"Ha ocurrido un error intentando transferirte "+ChatColor.GRAY+cantidad+"$");
						}
						
					}
				}
				else if(level>50 && level<100)
				{
					if(exp>=1000)
					{
						exp=exp-1000;
						level++;
						killer.sendMessage("["+ChatColor.GOLD+ChatColor.BOLD+"RiotLevels"+ChatColor.RESET+"] "+ChatColor.RED+"Felicidades, has subido al nivel "+ChatColor.GREEN+level);
						cantidad=level*Integer.parseInt(plugin.getConfig().getString("factor_p"));
						EconomyResponse er=eco.depositPlayer(killer,cantidad);
						
						if(er.transactionSuccess())
						{
							killer.sendMessage("["+ChatColor.GOLD+ChatColor.BOLD+"RiotLevels"+ChatColor.RESET+"] "+ChatColor.GREEN+"Has recibido "+ChatColor.GRAY+cantidad+"$"+ChatColor.GREEN+" debido a tu reciente subida de nivel");
						}
						else 
						{
							killer.sendMessage("["+ChatColor.GOLD+ChatColor.BOLD+"RiotLevels"+ChatColor.RESET+"] "+ChatColor.RED+"Ha ocurrido un error intentando transferirte "+ChatColor.GRAY+cantidad+"$");
						}
					}
				}
				else if(level>=100)
				{
					level=100;
					if(exp>=1000)
					{
						exp=1000;
					}
				}
			}
			else 
			{
				if(exp>=500)
				{
					exp=exp-500;
					level++;
					killer.sendMessage("["+ChatColor.GOLD+ChatColor.BOLD+"RiotLevels"+ChatColor.RESET+"] "+ChatColor.RED+"Felicidades, has subido al nivel "+ChatColor.GREEN+level);
					killer.sendMessage("["+ChatColor.GOLD+ChatColor.BOLD+"RiotLevels"+ChatColor.RESET+"] "+ChatColor.RED+"Eso significa que ahora vas a ganar puntos basados en un sistema de ELO, pero a su vez significa que a partir de ahora puedes perder puntos!");
					cantidad=level*Integer.parseInt(plugin.getConfig().getString("factor_p"));
					EconomyResponse er=eco.depositPlayer(killer,cantidad);
					
					if(er.transactionSuccess())
					{
						killer.sendMessage("["+ChatColor.GOLD+ChatColor.BOLD+"RiotLevels"+ChatColor.RESET+"] "+ChatColor.GREEN+"Has recibido "+ChatColor.GRAY+cantidad+"$"+ChatColor.GREEN+" debido a tu reciente subida de nivel");
					}
					else 
					{
						killer.sendMessage("["+ChatColor.GOLD+ChatColor.BOLD+"RiotLevels"+ChatColor.RESET+"] "+ChatColor.RED+"Ha ocurrido un error intentando transferirte "+ChatColor.GRAY+cantidad+"$");
					}
				}
			}
			
			if(level2<1)
			{
				dead.sendMessage(("["+ChatColor.DARK_PURPLE+""+ChatColor.MAGIC+"123"+ChatColor.BLACK+"|"+ChatColor.GOLD+""+ChatColor.BOLD+"RiotLevels"+ChatColor.DARK_PURPLE+ChatColor.MAGIC+"123"+ChatColor.BLACK+"|"+ChatColor.BLUE+ChatColor.RESET+"]"));
				dead.sendMessage(ChatColor.GOLD+""+ChatColor.MAGIC+"1"+ChatColor.RESET+ChatColor.RED+"Lo sentimos! has "+ChatColor.DARK_RED+"PERDIDO");
				dead.sendMessage(ChatColor.GOLD+""+ChatColor.MAGIC+"1"+ChatColor.RESET+ChatColor.RED+"Has perdido "+ChatColor.DARK_RED+"-"+0.0+ChatColor.RED+" puntos!");	
			}
			else 
			{
				exp2=exp2+expminus2;
				
				dead.sendMessage(("["+ChatColor.DARK_PURPLE+""+ChatColor.MAGIC+"123"+ChatColor.BLACK+"|"+ChatColor.GOLD+""+ChatColor.BOLD+"RiotLevels"+ChatColor.DARK_PURPLE+ChatColor.MAGIC+"123"+ChatColor.BLACK+"|"+ChatColor.BLUE+ChatColor.RESET+"]"));
				dead.sendMessage(ChatColor.GOLD+""+ChatColor.MAGIC+"1"+ChatColor.RESET+ChatColor.RED+"Mejor suerte la siguiente vez! Teniendo un "+ChatColor.GRAY+round(P2*100,2)+ChatColor.RED+"% de posibilidades de ganar basado en tu nivel has "+ChatColor.DARK_RED+"PERDIDO");
				dead.sendMessage(ChatColor.GOLD+""+ChatColor.MAGIC+"1"+ChatColor.RESET+ChatColor.RED+"Has perdido "+ChatColor.DARK_RED+expminus2+ChatColor.RED+" puntos!");
			}
			
			
			if(level2>0)
			{
				if(exp2<0)
				{
					
					if(level2<=50)
					{
						level2--;
						exp2=500+expminus2;
						dead.sendMessage("["+ChatColor.GOLD+ChatColor.BOLD+"RiotLevels"+ChatColor.RESET+"] "+ChatColor.RED+"Lo sentimos, has bajado a nivel "+ChatColor.DARK_RED+level2);
					}
					else
					{
						level2--;
						exp2=1000+expminus2;
						dead.sendMessage("["+ChatColor.GOLD+ChatColor.BOLD+"RiotLevels"+ChatColor.RESET+"] "+ChatColor.RED+"Lo sentimos, has bajado a nivel "+ChatColor.DARK_RED+level2);
					}
				}
			}
			
			
			kills=kills+=1;
			deaths2=deaths2+=1;
			killstreak2=0;
			killstreak=killstreak+=1;
			
			ps=con.prepareStatement(queryset);
			
			ps.setInt(1,kills);
			ps.setInt(2,deaths);
			ps.setInt(3,killstreak);
			ps.setInt(4,level);
			ps.setDouble(5,exp);
			ps.setString(6,id.toString());
			
			ps.executeUpdate();
			
			ps.setInt(1,kills2);
			ps.setInt(2,deaths2);
			ps.setInt(3,killstreak2);
			ps.setInt(4,level2);
			ps.setDouble(5,exp2);
			ps.setString(6,id2.toString());
			
			ps.executeUpdate();
			
			rs.close();
			con.close();
			
		}catch(SQLException e) { plugin.getLogger().info(e.getMessage()); }
	}
	
	private static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();
	 
	    BigDecimal bd = new BigDecimal(Double.toString(value));
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
	public User getStats(Player p)
	{
		String ddbb=plugin.getConfig().getString("DB-url");
		String user=plugin.getConfig().getString("User");
		String pass=plugin.getConfig().getString("Pass");
		String query="select * from usuarios where ID=?";
		
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try 
		{
		con=DriverManager.getConnection(ddbb,user,pass);
		ps=con.prepareStatement(query);
		
		ps.setString(1,p.getUniqueId().toString());
		
		rs=ps.executeQuery();
		
		UUID id=null;
		
		
		while(rs.next()) return new User(id.fromString(rs.getString(1)),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getDouble(7));
		
		}catch(SQLException e) { p.sendMessage("["+ChatColor.GOLD+""+ChatColor.BOLD+"RiotLevels"+ChatColor.RESET+"]"+ChatColor.RED+" Error encontrando tus estadisticas *");}
		finally { try {
			con.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} }
		return null;

	}
	
	public void updateUserStats(String action,int data,double exp,String nombre)
	{
		String ddbb=plugin.getConfig().getString("DB-url");
		String user=plugin.getConfig().getString("User");
		String pass=plugin.getConfig().getString("Pass");
		String query="update usuarios set "+action+"=? where NOMBRE=?";
		
		Connection con=null;
		PreparedStatement ps=null;
		
		try
		{
			
		con=DriverManager.getConnection(ddbb,user,pass);
		
		ps=con.prepareStatement(query);
		
		if(!action.equalsIgnoreCase("EXP"))
		{
		ps.setInt(1,data);
		ps.setString(2,nombre);
		}
		else 
		{
		ps.setDouble(1,exp);
		ps.setString(2,nombre);
		}
		
		ps.executeUpdate();
		}catch(SQLException e) { plugin.getLogger().info("Error conectando con la base de datos"); }
	}
	
	public void getRanking(String action,Player p,int max) throws Exception
	{
		String ddbb=plugin.getConfig().getString("DB-url");
		String user=plugin.getConfig().getString("User");
		String pass=plugin.getConfig().getString("Pass");
		
		if(action.equalsIgnoreCase("level"))
		{
			action="nivel";
		}
		
		String query="select NOMBRE,"+action+" from usuarios order by "+action+" desc";
		String queryB="select NOMBRE,"+action+" from usuarios order by "+action+" asc";
		Connection con=null;
		Statement ps=null;
		ResultSet rs=null;
		try 
		{
		
		con=DriverManager.getConnection(ddbb,user,pass);
		ps=con.createStatement();
		
		if(!action.equalsIgnoreCase("deaths"))
		{
		rs=ps.executeQuery(query);
		}
		else 
		{
		rs=ps.executeQuery(queryB);	
		}
		
		if(!rs.absolute(1))
		{
			throw new Exception();
		}
		else 
		{
			rs.beforeFirst();
			int puesto=1;
			
			p.sendMessage("["+ChatColor.DARK_PURPLE+ChatColor.MAGIC+"123"+ChatColor.BLACK+":"+ChatColor.GOLD+ChatColor.BOLD+"Ranking de "+ChatColor.RED+ChatColor.BOLD+action.toUpperCase()+ChatColor.BLACK+":"+ChatColor.DARK_PURPLE+ChatColor.MAGIC+"123"+ChatColor.RESET+"]");
			while(rs.next()) 
			{
				if(puesto>max)
				{}
				else 
				{
				p.sendMessage(""+ChatColor.GOLD+ChatColor.MAGIC+"1"+ChatColor.BLUE+"=>"+ChatColor.RESET+ChatColor.GOLD+puesto+". "+ChatColor.RED+rs.getString("NOMBRE")+"  "+rs.getInt(action)); 
				puesto+=1;
				}
			}
		}
		
		}catch(SQLException e) { plugin.getLogger().info("Error conectandose con la base de datos :: getRanking"); }
		finally { con.close(); rs.close(); }
	}
	
	private static RiotLevels plugin;
	private UUID id,id2;
	private String nombre,nombre2;
	private int kills,kills2,deaths,deaths2,killstreak,killstreak2;
	private int level2,level;
	private double exp2,exp,cantidad;
	private float P1,P2;
	private double expplus1,expminus2,expminuspositiva;
	private static Economy eco;
}
