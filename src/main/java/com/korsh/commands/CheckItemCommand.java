package com.korsh.commands;

import java.util.Objects;

import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import net.kyori.adventure.text.Component;

public class CheckItemCommand implements CommandExecutor{

  private final Plugin plugin;
  
  public CheckItemCommand(Plugin plugin){
    this.plugin = plugin;
  }

  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
    @NotNull String @NotNull [] args
  ) {
    if (!(sender instanceof Player)){
      sender.sendMessage("Проверку предмета может осуществлять только игрок");
      return true;
    }
    
    Player player = (Player) sender;
    
    PersistentDataContainer container = player.getInventory()
      .getItemInMainHand()
      .getItemMeta()
      .getPersistentDataContainer();

    var custom_data = new NamespacedKey(plugin, "custom_data");
    if (container.has(custom_data, PersistentDataType.STRING)){
      player.sendMessage(
        Objects.requireNonNull(container.get(custom_data, PersistentDataType.STRING))
      );
    }else{
      player.sendMessage(Component.text("Предмет не имеет скрытой информации"));
    }

    return true;
  }
  
}
