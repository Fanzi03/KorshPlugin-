package com.korsh.listeners;

import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {
    
    private final HashMap<UUID, Integer> emptyFightsCounts = new HashMap<>();
    private final HashMap<UUID, Long> breakBlocksCounts = new HashMap<>();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.getPlayer().sendMessage("Добро пожаловать на сервер");
    }
    
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        UUID playerUuid = event.getPlayer().getUniqueId();

        //empty clicks
        if (event.getAction() == Action.LEFT_CLICK_AIR){
            Integer count_clicks = emptyFightsCounts.getOrDefault(playerUuid, 0);
            
            if (count_clicks == 20){
                event.getPlayer().sendMessage("Перестань закликивать");
                count_clicks = 0;
            }

            emptyFightsCounts.put(
                playerUuid, 
                count_clicks + 1
            );
            
        }
    }

    //Break block
    @EventHandler
    public void onBlockEvent(BlockBreakEvent event){
        UUID playerUuid = event.getPlayer().getUniqueId();
        
        breakBlocksCounts.put(
            playerUuid,
            breakBlocksCounts.getOrDefault(playerUuid, 0L) + 1L
        );
        
        Long countBlocks = breakBlocksCounts.get(playerUuid);

        if (countBlocks % 1 == 0){
            event.getPlayer().sendMessage("Ты сломал " + countBlocks + " блоков");
        }
    }
}