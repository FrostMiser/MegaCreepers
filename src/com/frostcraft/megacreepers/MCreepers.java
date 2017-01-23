package com.frostcraft.megacreepers;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class MCreepers implements CommandExecutor  {
	
	Plugin megaCreepers;
	
	public MCreepers (Plugin plugin) {
		megaCreepers = plugin;
	}
	
	@Override
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
		
		//mcreepers command
		if (p != null) {
			if (cmd.getName().equalsIgnoreCase("mcreepers")) {
				if (args.length <= 1) {
					p.sendMessage(ChatColor.GREEN + "   MegaCreepers"); 
					p.sendMessage(ChatColor.GREEN + "/mcreepers options");
					p.sendMessage("============================");
					p.sendMessage(ChatColor.GREEN + "setblast [0-20] " + ChatColor.AQUA + "Set creeper blast power. 0 to return to default (currently " + MegaCreepers.creeperBlastPower + ")");
					p.sendMessage(ChatColor.GREEN + "powered [On|Off] " + ChatColor.AQUA + "All powered creepers (on or off). (currently " + (MegaCreepers.creeperPowered?"On":"Off") + ")");
					p.sendMessage(ChatColor.GREEN + "sethealth [1-20] " + ChatColor.AQUA + "Set creeper health. (currently " + MegaCreepers.creeperHealth + ")");
//					p.sendMessage(ChatColor.GREEN + "setspeed [1-20] " + ChatColor.AQUA + "Set creeper speed. (currently " + MegaCreepers.creeperSpeed+ ")");
				}
				else {
					if (args[0].equals("setblast")) {
						try { Integer.parseInt(args[1]); }
						catch (Exception e) { p.sendMessage(ChatColor.RED + "Blast power must be a number between 0 and 20");return true;}
						int blastAmount = Integer.parseInt(args[1]);

						if (blastAmount < 0 || blastAmount > 20) { p.sendMessage(ChatColor.RED + "Blast power must be a number between 0 and 20");return true;}
						
						megaCreepers.getConfig().set("creeper-blast-power", blastAmount);
						MegaCreepers.creeperBlastPower = blastAmount;
						megaCreepers.saveConfig();
						p.sendMessage(ChatColor.AQUA + "Creeper blast power set to " + MegaCreepers.creeperBlastPower);
					}
					else if (args[0].equals("powered")) {
						if (args[1].equalsIgnoreCase("off")) {
							MegaCreepers.creeperPowered = false;
							p.sendMessage(ChatColor.AQUA + "All creepers powered is now Off.");
							megaCreepers.getConfig().set("all-creepers-powered",MegaCreepers.creeperPowered);
							megaCreepers.saveConfig();
						}
						else if (args[1].equalsIgnoreCase("on")) {
							MegaCreepers.creeperPowered = true;
							p.sendMessage(ChatColor.AQUA + "All creepers powered is now On.");
							megaCreepers.getConfig().set("all-creepers-powered",MegaCreepers.creeperPowered);
							megaCreepers.saveConfig();
						}						
						else {
							p.sendMessage(ChatColor.RED + "Invalid option, options are On or Off.");
						}
					}
					else if (args[0].equals("sethealth")) {
						try { Integer.parseInt(args[1]); }
						catch (Exception e) { p.sendMessage(ChatColor.RED + "Health must be a number between 1 and 20");return true;}
						int healthAmount = Integer.parseInt(args[1]);

						if (healthAmount <= 0 || healthAmount > 20) { p.sendMessage(ChatColor.RED + "Health must be a number between 1 and 20");return true;}
						
						megaCreepers.getConfig().set("creeper-health", healthAmount);
						MegaCreepers.creeperHealth = healthAmount;
						megaCreepers.saveConfig();
						p.sendMessage(ChatColor.AQUA + "Creeper health set to " + MegaCreepers.creeperHealth);
					}
				/*	else if (args[0].equals("setspeed")) {
						try { Integer.parseInt(args[1]); }
						catch (Exception e) { p.sendMessage(ChatColor.RED + "Speed must be a number between 1 and 20");return true;}
						int speedAmount = Integer.parseInt(args[1]);

						if (speedAmount <= 0 || speedAmount > 20) { p.sendMessage(ChatColor.RED + "Speed must be a number between 1 and 20");return true;}
						
						megaCreepers.getConfig().set("creeper-speed", speedAmount);
						MegaCreepers.creeperSpeed = (speedAmount/10);
						megaCreepers.saveConfig();
						p.sendMessage(ChatColor.AQUA + "Creeper speed set to " + speedAmount);
					}*/						
				}
			}
		}

		return true;
	}
}
