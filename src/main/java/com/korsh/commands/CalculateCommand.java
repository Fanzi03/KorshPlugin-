package com.korsh.commands;


import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.md_5.bungee.api.ChatColor;

public class CalculateCommand implements CommandExecutor, TabCompleter{

  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
      @NotNull String [] args
  ) {
    if (args.length != 3){
      sender.sendMessage("Неправильные аргументы");
      return false;
    }

    int result;
    int number1;
    int number2;
    //Parsing args
    try{
      number1 = Integer.parseInt(args[0]);
      number2 = Integer.parseInt(args[2]);
    }catch(NumberFormatException e){
      sender.sendMessage("Первый и третий агрумент должны являтся числами.");
      return false;
    }

    String operation = args[1];

    switch (operation){
      case("+"):
        result = number1 + number2;
        break;
      case("-"):
        result = number1 - number2;
        break; 
      case("/"):
        if (number2 == 0){
          sender.sendMessage(ChatColor.RED+ "Делить на ноль нельзя");
          return true;  
        }

        result = number1 / number2;
        break;
      case("*"):
        result = number1 * number2;
        break;
      default:
        sender.sendMessage(ChatColor.RED + "Операция: " + operation + " не существует.\nВалидные операции: +,-,/,*");
        return false;
    }
    
    sender.sendMessage("Результат: " + result);
    return true;
  }

  @Override
  public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command,
      @NotNull String label, @NotNull String @NotNull [] args
  ) {
    if (args.length == 2){
      return List.of("+", "-", "*", "/");
    }
    
    return List.of();
  }

}
