package com.DaTreeBros.zombiePlugin.events;

import com.DaTreeBros.zombiePlugin.commands.zombiePluginCommands;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class zombiePluginsEvents implements Listener {

    @EventHandler
    public static void onPlayerJoin(PlayerJoinEvent event) {
        zombiePluginCommands.isActive = true;
    }
}
