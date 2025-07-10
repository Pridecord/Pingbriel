package dev.sdriver1.pingbriel;

import org.bukkit.plugin.java.JavaPlugin;

public final class Pingbriel extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new OnUserType(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
