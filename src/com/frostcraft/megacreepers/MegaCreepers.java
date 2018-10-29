package com.frostcraft.megacreepers;

import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.ChatColor;


public class MegaCreepers extends JavaPlugin {

	public static int creeperBlastPower;
	public static boolean creeperPowered;
	public static double creeperHealth;
	public static float creeperSpeed;
	
	public void onEnable() {
		this.saveDefaultConfig();
		creeperBlastPower = this.getConfig().getInt("creeper-blast-power");
		creeperPowered = this.getConfig().getBoolean("all-creepers-powered");
		creeperHealth = this.getConfig().getDouble("creeper-health");
		creeperSpeed = (this.getConfig().getInt("creeper-speed"));
		
		//Register mcreepers command to mcreepers class
		getCommand("mcreepers").setExecutor(new MCreepers(this));
		
		getServer().getPluginManager().registerEvents(new EntityListener(),this);
		this.getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "[MegaCreepers] Plugin Enabled.");
		return;		
	}

	public void onDisable() {
		this.getConfig().set("creeper-blast-power", creeperBlastPower);
		this.getConfig().set("all-creepers-powered",creeperPowered);
		this.getConfig().set("creeper-health",creeperHealth);
		
		this.getServer().getConsoleSender().sendMessage(ChatColor.RED + "[MegaCreepers] Plugin Disabled.");
	}
}
