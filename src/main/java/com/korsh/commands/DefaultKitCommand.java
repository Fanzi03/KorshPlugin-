package com.korsh.commands;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import com.korsh.components.KorshComponents;

import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.ChatColor;

public class DefaultKitCommand implements CommandExecutor{
  
  private final Plugin plugin;
  private final HashMap<UUID, Boolean> isUsedKits = new HashMap<>();
  
  public DefaultKitCommand(Plugin plugin){
    this.plugin = plugin;
  }

  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
      @NotNull String @NotNull [] args
  ) {
    if (!(sender instanceof Player)){
      sender.sendMessage(ChatColor.RED + "Набор может получить только игрок");
      return true;
    }
    
    Player player = (Player) sender;
    UUID playerUuid = player.getUniqueId(); 
    
    Boolean isUsed = isUsedKits.getOrDefault(playerUuid, false);
    
    if (!isUsed){
      isUsedKits.put(playerUuid, true);

      ItemStack axe = ItemStack.of(Material.STONE_AXE);
      ItemStack edge = ItemStack.of(Material.STONE_SWORD);
      ItemStack pickaxe = ItemStack.of(Material.STONE_PICKAXE);
      ItemStack apples = ItemStack.of(Material.APPLE, 5);
      ItemStack bread = ItemStack.of(Material.BREAD, 5);
      ItemStack pants = ItemStack.of(Material.LEATHER_LEGGINGS);
      
      for (ItemStack stack : List.of(axe,edge, pickaxe,apples,bread,pants)){
        ItemMeta meta = stack.getItemMeta();

        //Enchantmenting (Зачарование)
        meta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
        
        //Описание  
        meta.lore(List.of(
          Component.text("Предмет из дефолтного набора"),
          Component.text("Воспользуйся с умом")
        ));

        stack.setItemMeta(meta);
      }
      
      //Change name
      ItemMeta meta = bread.getItemMeta();
      meta.customName(KorshComponents.usuallyComponent("Хлебушек"));
      bread.setItemMeta(meta);
      
      //Coloring and make custom data
      LeatherArmorMeta meta_paints = (LeatherArmorMeta) pants.getItemMeta();
      meta_paints.setColor(Color.PURPLE);

      meta_paints.getPersistentDataContainer()
        .set(
          new NamespacedKey(plugin, "custom_data"), 
          PersistentDataType.STRING,
          "Штаны 100 гривен"
        );

      pants.setItemMeta(meta_paints);
      


      player.getInventory().addItem(edge, axe, pickaxe, apples, bread,pants);
    }else{
      player.sendMessage("Стандартный набор можно получить только один раз.");   
    }

    return true;
  }
  
}
