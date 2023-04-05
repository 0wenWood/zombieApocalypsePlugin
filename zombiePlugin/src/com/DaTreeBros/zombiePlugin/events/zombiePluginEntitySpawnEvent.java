package com.DaTreeBros.zombiePlugin.events;

import com.DaTreeBros.zombiePlugin.commands.zombiePluginCommands;
import com.DaTreeBros.zombiePlugin.zombiePlugin;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.*;
import org.bukkit.entity.*;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class zombiePluginEntitySpawnEvent implements Listener {

    @EventHandler
    public static void onEntitySpawn(EntitySpawnEvent event){
        if(zombiePluginCommands.isActive) {
            if(zombiePlugin.hostileMobCount < 900) {
                if (event.getEntity() instanceof Creeper) {
                    int random = new Random().nextInt(4) + 1;
                    for (int i = 0; i < random; i++) {
                        summonCustomZombie(event.getLocation());
                    }
                    random = new Random().nextInt(4) + 1;
                    for (int i = 0; i < random; i++) {
                        summonCustomSkeleton(event.getLocation());
                    }

                    event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack(Material.GUNPOWDER));
                    ((Creeper) event.getEntity()).setHealth(0);
                } else if (event.getEntity() instanceof Enderman) {
                    int random = new Random().nextInt(4) + 4;
                    for (int i = 0; i < random; i++) {
                        summonCustomZombie(event.getLocation());
                    }
                    random = new Random().nextInt(4) + 2;
                    for (int i = 0; i < random; i++) {
                        summonCustomSkeleton(event.getLocation());
                    }

                    event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack(Material.ENDER_PEARL));
                    ((Enderman) event.getEntity()).setHealth(0);
                } else if (event.getEntity() instanceof Spider) {
                    int random = new Random().nextInt(4) + 1;
                    for (int i = 0; i < random; i++) {
                        summonCustomZombie(event.getLocation());
                    }
                    random = new Random().nextInt(2) + 1;
                    for (int i = 0; i < random; i++) {
                        summonCustomSkeleton(event.getLocation());
                    }

                    event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack(Material.STRING));
                    event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack(Material.SPIDER_EYE));
                    ((Spider) event.getEntity()).setHealth(0);
                } else if (event.getEntity() instanceof Slime) {
                    int random = new Random().nextInt(2) + 1;
                    for (int i = 0; i < random; i++) {
                        summonCustomZombie(event.getLocation());
                    }
                    random = new Random().nextInt(2) + 1;
                    for (int i = 0; i < random; i++) {
                        summonCustomSkeleton(event.getLocation());
                    }
                    ((Slime) event.getEntity()).setHealth(0);
                } else if (event.getEntity() instanceof Blaze) {
                    int random = new Random().nextInt(2) + 2;
                    for (int i = 0; i < random; i++) {
                        summonCustomZombie(event.getLocation());
                    }
                    random = new Random().nextInt(2) + 1;
                    for (int i = 0; i < random; i++) {
                        summonCustomSkeleton(event.getLocation());
                    }
                    ((Blaze) event.getEntity()).setHealth(0);

                    event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack(Material.BLAZE_POWDER));
                } else if (event.getEntity() instanceof PigZombie) {
                    int random = new Random().nextInt(2) + 1;
                    for (int i = 0; i < random; i++) {
                        summonCustomZombie(event.getLocation());
                    }
                    random = new Random().nextInt(2) + 1;
                    for (int i = 0; i < random; i++) {
                        summonCustomSkeleton(event.getLocation());
                    }

                    event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack(Material.GOLD_NUGGET));
                    ((PigZombie) event.getEntity()).setHealth(0);
                }
            } else {
                if(event.getEntity() instanceof Monster && !(event.getEntity() instanceof Boss)){
                    event.getEntity().remove();
                }
            }
        }
    }

    public static Skeleton summonCustomSkeleton(Location location) {
        Skeleton skeleton = (Skeleton)location.getWorld().spawnEntity(location, EntityType.SKELETON);
        skeleton.getEquipment().setHelmet(new ItemStack(Material.STONE_BUTTON));
        skeleton.getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(1000);
        zombiePlugin.hostileMobCount++;
        return skeleton;
    }

    public static Zombie summonCustomZombie(Location location){
        Zombie zombie = (Zombie)location.getWorld().spawnEntity(location, EntityType.ZOMBIE);
        zombie.getEquipment().setHelmet(new ItemStack(Material.STONE_BUTTON));
        zombie.getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(1000);
        zombie.getAttribute(Attribute.ZOMBIE_SPAWN_REINFORCEMENTS).setBaseValue(0.5);
        AttributeInstance health = zombie.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        int effects = new Random().nextInt(10);
        zombiePlugin.hostileMobCount++;
        switch (effects) {
            case 0, 5 -> health.setBaseValue(25.0);
            case 1, 6 -> {
                health.setBaseValue(15.0);
                zombie.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
            }
            case 2, 7 -> {
                health.setBaseValue(30.0);
                zombie.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.2);
                zombie.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(3.0);
            }
            case 3, 8 -> zombie.addScoreboardTag("Summoner");
            case 4 -> {
                zombie.setGlowing(true);
                zombie.addScoreboardTag("Explosion");
            }
            default -> {}
        }
        return zombie;
    }
}
