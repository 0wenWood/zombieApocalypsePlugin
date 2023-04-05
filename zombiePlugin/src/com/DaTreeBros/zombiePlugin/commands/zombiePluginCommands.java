package com.DaTreeBros.zombiePlugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class zombiePluginCommands implements CommandExecutor {

    public static boolean isActive = false;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equals("startZombie") && !isActive) {
            sender.sendMessage("Started Apocalypse");
            isActive = true;
        } else if (command.getName().equals("startZombie") && isActive) {
            sender.sendMessage("Apocalypse was already started");
        }
        return isActive;
    }
}
