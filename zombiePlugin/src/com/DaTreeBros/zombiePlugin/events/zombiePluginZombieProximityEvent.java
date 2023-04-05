package com.DaTreeBros.zombiePlugin.events;

import com.DaTreeBros.zombiePlugin.zombiePlugin;
import org.bukkit.Particle;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.List;


public class zombiePluginZombieProximityEvent implements Listener {

    @EventHandler
    public static void onEntityProximity(EntityDeathEvent event) {
        if(event.getEntity() instanceof Zombie){
            if(event.getEntity().getScoreboardTags().contains("Summoner")){
                List<Entity> nearbyEntities = event.getEntity().getNearbyEntities(3,3,3);
                if(!nearbyEntities.isEmpty()){
                    for(int i = 0; i < nearbyEntities.size(); i++){
                        if(!(nearbyEntities.get(i) instanceof Monster || nearbyEntities.get(i) instanceof Player
                                || nearbyEntities.get(i) instanceof Item || nearbyEntities.get(i) instanceof Boss)) {
                            zombiePluginEntitySpawnEvent.summonCustomZombie(nearbyEntities.get(i).getLocation());
                            nearbyEntities.get(i).remove();
                        }
                    }
                }
                event.getEntity().getLocation().getWorld().spawnParticle(Particle.SNEEZE, event.getEntity().getLocation(),10);
            } else if (event.getEntity().getScoreboardTags().contains("Explosion")){
                event.getEntity().getLocation().getWorld().createExplosion(event.getEntity().getLocation(), 2.0f, true);
            }
        }
        if(event.getEntity() instanceof Player){
            zombiePluginEntitySpawnEvent.summonCustomZombie(event.getEntity().getLocation());
        }
    }
}
