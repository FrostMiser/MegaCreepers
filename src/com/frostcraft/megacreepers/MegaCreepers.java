package com.frostcraft.megacreepers;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class MegaCreepers extends JavaPlugin {

	public static int creeperBlastPower;
	public static boolean creeperPowered;
	
	public void onEnable() {
		this.saveDefaultConfig();
		creeperBlastPower = this.getConfig().getInt("creeper-blast-power");
		creeperPowered = this.getConfig().getBoolean("all-creepers-powered");
		
		getServer().getPluginManager().registerEvents(new EntityListener(),this);
		this.getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "[MegaCreepers] Plugin Enabled.");
		return;		
	}


	public void onDisable() {
		this.getConfig().set("creeper-blast-power", creeperBlastPower);
		this.getConfig().set("all-creepers-powered",creeperPowered);
		
		this.getServer().getConsoleSender().sendMessage(ChatColor.RED + "[MegaCreepers] Plugin Disabled.");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{

		Player p;
		try {
			p = (Player) sender;	
		}
		catch (Exception e) {
			p = null;
			sender.sendMessage(ChatColor.RED + "This command must be issued by a player.");
			return true;
		}
		

		if (p != null) {
			if (cmd.getName().equalsIgnoreCase("mcreepers")) {
				if (args.length <= 1) {
					p.sendMessage(ChatColor.GREEN + "   MegaCreepers"); 
					p.sendMessage(ChatColor.GREEN + "/mcreepers options");
					p.sendMessage("============================");
					p.sendMessage(ChatColor.GREEN + "setblast [1-20] " + ChatColor.AQUA + "Set creeper blast power. (currently " + creeperBlastPower + ")");
					p.sendMessage(ChatColor.GREEN + "powered" + ChatColor.AQUA + "Toggles powered creepers. (currently " + creeperPowered + ")");
				}
				else {
					if (args[0].equals("setblast")) {
						try { Integer.parseInt(args[1]); }
						catch (Exception e) { p.sendMessage(ChatColor.RED + "Blast power must be a number between 0 and 20");return true;}
						int blastAmount = Integer.parseInt(args[1]);

						if (blastAmount < 0 || blastAmount > 20) { p.sendMessage(ChatColor.RED + "Blast power must be a number between 0 and 20");return true;}
						
						this.getConfig().set("creeper-blast-power", blastAmount);
						creeperBlastPower = blastAmount;
						this.saveConfig();
						p.sendMessage(ChatColor.AQUA + "Blast power set to " + creeperBlastPower);
					}
					else if (args[0].equals("powered")) {
						if (args[1].equalsIgnoreCase("off")) {
							creeperPowered = false;
							p.sendMessage(ChatColor.AQUA + "All creepers powered is now Off.");
							this.getConfig().set("all-creepers-powered",creeperPowered);
							this.saveConfig();
						}
						else if (args[1].equalsIgnoreCase("on")) {
							creeperPowered = true;
							p.sendMessage(ChatColor.AQUA + "All creepers powered is now On.");
							this.getConfig().set("all-creepers-powered",creeperPowered);
							this.saveConfig();
						}						
						else {
							p.sendMessage(ChatColor.RED + "Invalid option, options are On or Off.");
						}
					}
				}
			}
		}

		return true;
	}
}
