package org.t1ago.minecraft.riotlevels.RiotLevels;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.economy.Economy;

public class RiotLevels extends JavaPlugin{
	
	private static Economy eco=null;
	
	@Override
	public void onEnable() 
	{
		if (getServer().getPluginManager().getPlugin("Vault") != null) {
            setupEconomy();
        }
		else 
		{
			this.getLogger().info("Vault no esta instalado en el servidor");
		}
		
		getConfig().options().copyDefaults(true);
		saveConfig();
		ManageUsers mu=new ManageUsers(this);
		getServer().getPluginManager().registerEvents(mu,this);
		getCommand("stats").setExecutor(new RiotCM(this,mu));
		getCommand("riot").setExecutor(new RiotCM(this,new ManageUsers(this)));
		getServer().getPluginManager().registerEvents(new DeathEvent(this,mu),this);
	}
	
	public ManageUsers getManageUsers() 
	{
		return this.mu;
	}
	
	private boolean setupEconomy() {
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        eco = rsp.getProvider();
        return eco != null;
    }
	
	public Economy getEco() 
	{
		return this.eco;
	}
	
	private static ManageUsers mu;
	
}
