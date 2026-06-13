package com.korsh.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import net.md_5.bungee.api.ChatColor;

public class CoordinateCommand implements CommandExecutor{

  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
  @NotNull String @NotNull [] args) {
    if (!(sender instanceof Player)){
      sender.sendMessage(ChatColor.RED + "Это команда предназначена для игрока");
      return true;
    }
    
    Player player = (Player) sender;
    
    Location location = player.getLocation();
    
    double x = location.getX();
    double y = location.getY();
    double z = location.getZ();

    String worldName = location.getWorld().getName();
    
    player.sendMessage(String.format(
          "Кординаты : X: %.2f, Y: %.2f, Z: %.2f в мире %s",
          x,y,z, worldName  
    ));

    return true;
  }
  
}
