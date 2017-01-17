package com.frostcraft.megacreepers;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;


public class MegaCreepers extends JavaPlugin {

	
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new EntityListener(),this);
		this.getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "[MegaCreepers] Plugin Enabled.");
		return;
			
	}

	public void onDisable() {
		this.getServer().getConsoleSender().sendMessage(ChatColor.RED + "[MegaCreepers] Plugin Disabled.");
	}
}
