package com.DaTreeBros.zombiePlugin;

import org.bukkit.ChatColor;
import com.DaTreeBros.zombiePlugin.commands.*;
import com.DaTreeBros.zombiePlugin.events.*;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.entity.*;
import org.bukkit.plugin.java.*;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.List;
import java.util.Random;

public class zombiePlugin extends JavaPlugin {

    public static int hostileMobCount = 0;

    @Override
    public void onEnable() {
        getCommand("startZombie").setExecutor(new zombiePluginCommands());
        getServer().getPluginManager().registerEvents(new zombiePluginsEvents(), this);
        getServer().getPluginManager().registerEvents(new zombiePluginEntitySpawnEvent(), this);
        getServer().getPluginManager().registerEvents(new zombiePluginZombieProximityEvent(), this);
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[zombiePlugin]: Plugin is enabled");

        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, () -> {
            if (getServer().getOnlinePlayers() != null && !getServer().getOnlinePlayers().isEmpty() && zombiePluginCommands.isActive) {
                List<Player> players = (List<Player>) getServer().getOnlinePlayers();
                int randPlayer = new Random().nextInt(players.size());
                Player player = players.get(randPlayer);
                int randX = (int)new Random().nextDouble(-4, 4) * 16 + player.getLocation().getBlockX();
                int randZ = (int)new Random().nextDouble(-4, 4) * 16 + player.getLocation().getBlockZ();
                int randY = player.getWorld().getHighestBlockYAt((int) randX, (int) randZ) + 1;
                Location location = new Location(player.getWorld(), randX, randY, randZ);
                player.getWorld().spawnEntity(location, EntityType.ENDERMAN);
            }
        }, 0L, 3600L);

        scheduler.scheduleSyncRepeatingTask(this, () -> {
            if (getServer().getOnlinePlayers() != null && !getServer().getOnlinePlayers().isEmpty() && zombiePluginCommands.isActive) {
                hostileMobCount = 0;
                List<Player> players = (List<Player>) getServer().getOnlinePlayers();
                for(int i = 0; i < players.size(); i++){
                    for(int j = 0; j < players.get(i).getWorld().getEntities().size(); j++){
                        hostileMobCount += (players.get(i).getWorld().getEntities().get(j) instanceof Monster)? 1 : 0;
                    }
                }
                getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "" + hostileMobCount);
            }
        }, 0L, 40L);
    }

    @Override
    public void onDisable() {
        hostileMobCount = 0;
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "[zombiePlugin]: Plugin is disabled");
    }
}
