package dev.sdriver1.pingbriel;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Pingbriel extends JavaPlugin {
    private final Map<UUID, String> pingColors = new HashMap<>();
    private File dataFile;
    private File pingSoundFile;
    private FileConfiguration pingSoundCfg;
    private FileConfiguration dataConfig;

    @Override
    public void onEnable() {
        // Colors
        dataFile = new File(getDataFolder(), "ping_color.yml");
        if (!dataFile.exists()) {
            getDataFolder().mkdirs();
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                getLogger().severe("Could not create ping_color.yml");
                e.printStackTrace();
            }
        }
        dataConfig = YamlConfiguration.loadConfiguration(dataFile);
        for (String key : dataConfig.getKeys(false)) {
            pingColors.put(UUID.fromString(key), dataConfig.getString(key));
        }

        // Sound
        pingSoundFile = new File(getDataFolder(), "ping_sound.yml");
        if (!pingSoundFile.exists()) {
            saveResource("ping_sound.yml", false);
        }
        pingSoundCfg = YamlConfiguration.loadConfiguration(pingSoundFile);

        // Color
        getCommand("ping_color").setExecutor(new PingColorCommand(this));
        getCommand("ping_color").setTabCompleter(new PingColorTabCompleter());

        // Sound
        getCommand("ping_sound").setExecutor(new PingSoundCommand(this));
        getCommand("ping_sound").setTabCompleter(new PingSoundTabCompleter());

        // Plugin/Server
        getServer().getPluginManager().registerEvents(new OnUserType(this), this);
    }

    public void savePingColors() {
        for (String key : dataConfig.getKeys(false)) {
            dataConfig.set(key, null);
        }
        for (Map.Entry<UUID, String> e : pingColors.entrySet()) {
            dataConfig.set(e.getKey().toString(), e.getValue());
        }
        try {
            dataConfig.save(dataFile);
        } catch (Exception ex) {
            getLogger().severe("Could not save ping-colors.yml");
            ex.printStackTrace();
        }
    }

    public boolean isPingSoundEnabled(UUID id) {
        return pingSoundCfg.getBoolean(id.toString(), true);
    }

    public void setPingSoundEnabled(UUID id, boolean val) {
        pingSoundCfg.set(id.toString(), val);
        try {
            pingSoundCfg.save(pingSoundFile);
        } catch (IOException e) {
            getLogger().severe("Could not save ping_sound.yml");
            e.printStackTrace();
        }
    }

    public Map<UUID, String> getPingColors() {
        return pingColors;
    }
}
