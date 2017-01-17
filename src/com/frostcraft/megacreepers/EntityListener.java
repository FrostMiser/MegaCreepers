package com.frostcraft.megacreepers;

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
		if (event.getEntity().getType().equals(EntityType.CREEPER)) {
			Creeper creeper = (Creeper) event.getEntity();
			creeper.setPowered(true);
		}
	}
	
	@EventHandler
	public void onEntityExplode(EntityExplodeEvent event) {
		if (event.getEntityType().equals(EntityType.CREEPER)) {
			event.getLocation().getWorld().createExplosion(event.getLocation(), 10);			
		}
	}	
	
	public void onEntitySpawn(CreatureSpawnEvent event) {
		if (!event.getEntity().getType().equals(EntityType.CREEPER) && event.getSpawnReason().equals(SpawnReason.CUSTOM)) {
			Creeper creeper = (Creeper) event.getLocation().getWorld().spawnEntity(event.getLocation(), EntityType.CREEPER);
			creeper.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(100.0);
			creeper.setHealth(100.0);
		}
	}
}