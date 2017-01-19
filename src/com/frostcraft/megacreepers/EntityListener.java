package com.frostcraft.megacreepers;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

public class EntityListener implements Listener {
	
	@EventHandler
	public void onEntitySpawn(EntitySpawnEvent event) {
		if (MegaCreepers.creeperPowered) {
			if (event.getEntity().getType().equals(EntityType.CREEPER)) {
				Creeper creeper = (Creeper) event.getEntity();
				creeper.setPowered(true);
			}
		}	
	}
	
	@EventHandler
	public void onEntityExplode(EntityExplodeEvent event) {
			if (event.getEntityType().equals(EntityType.CREEPER)) {
				if (MegaCreepers.creeperBlastPower > 0) {
					event.getLocation().getWorld().createExplosion(event.getLocation(), MegaCreepers.creeperBlastPower);					
				}
			}			

	}	
	
	@EventHandler
	public void onEntitySpawn(CreatureSpawnEvent event) {
			
			if (!event.getEntity().getType().equals(EntityType.CREEPER) && event.getSpawnReason().equals(SpawnReason.CUSTOM)) {
				Creeper creeper;
				creeper = (Creeper) event.getLocation().getWorld().spawnEntity(event.getLocation(), EntityType.CREEPER);
				creeper.setHealth(MegaCreepers.creeperHealth);
				creeper.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(MegaCreepers.creeperHealth);
			}


	}
}