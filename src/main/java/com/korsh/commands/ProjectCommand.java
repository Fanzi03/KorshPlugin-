package com.korsh.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import com.korsh.components.KorshComponents;

import net.kyori.adventure.text.Component;

public class ProjectCommand implements CommandExecutor{

  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
      @NotNull String @NotNull [] args
  ) {
    /* 
    if (args.length < 1){
      sender.sendMessage(ChatColor.RED + "Нужно добавить любой текст");
      return false;
    }
    */

    //String text= String.join(" ", args);

    sender.sendMessage(
      Component.empty()
        //.append(KorshComponents.usuallyComponent(text)).appendNewline()
        .append(KorshComponents.projectComponent())
        //.append(suprize)
    );

    return true;
  }
  
}
