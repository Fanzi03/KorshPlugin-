package com.korsh;

import java.util.Objects;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

import com.korsh.commands.CalculateCommand;
import com.korsh.commands.CoordinateCommand;
import com.korsh.commands.ProjectCommand;
import com.korsh.listeners.PlayerListener;
import com.korsh.managers.PluginManager;

public class KorshPlugin extends JavaPlugin {
    
    @Override
    public void onEnable() {
        
        // Initialize managers
        PluginManager.getInstance().initialize();
        
        // Register listeners
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        
        getLogger().warning("KorshPlugin has been enabled!");

        //Registration
        registerCommand("calculate", new CalculateCommand());
        registerCommand("coordinates", new CoordinateCommand(), null);
        registerCommand("comp", new ProjectCommand(), null);
    }

    @Override
    public void onDisable() {
        getLogger().warning("KorshPlugin has been disabled!");
    }

    @ParametersAreNonnullByDefault
    private void registerCommand(String name, CommandExecutor executor, @Nullable TabCompleter tabCompleter){
        Objects.requireNonNull(getCommand(name)).setExecutor(executor);
        if (tabCompleter != null){
            Objects.requireNonNull(getCommand(name)).setTabCompleter(tabCompleter);
        }
    }
    
    //Command handler
    @ParametersAreNonnullByDefault
    private <T extends CommandExecutor & TabCompleter> void registerCommand(String name, T command){
        registerCommand(name, command, command);
    }
    
}