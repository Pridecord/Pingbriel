package dev.sdriver1.pingbriel;

import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class PingColorCommand implements CommandExecutor {
    private final Pingbriel plugin;
    private static final List<String> PRESETS = List.of("yellow", "red", "green", "blue");

    public PingColorCommand(Pingbriel plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender,
                             Command cmd,
                             String label,
                             String[] args) {
        if (!(sender instanceof Player) || args.length != 1) {
            sender.sendMessage(Component.text("Usage: /ping_color <preset|#RRGGBB>"));
            return true;
        }

        String choice = args[0].toLowerCase(Locale.ROOT);
        if (PRESETS.contains(choice) || choice.matches("^#?[0-9a-f]{6}$")) {
            if (choice.matches("^[0-9a-f]{6}$")) choice = "#" + choice;
            UUID uid = ((Player) sender).getUniqueId();
            plugin.getPingColors().put(uid, choice);
            plugin.savePingColors();
            sender.sendMessage(Component.text("Ping color set to " + choice));
        } else {
            sender.sendMessage(Component.text("Invalid color. Use a preset or #RRGGBB."));
        }
        return true;
    }
}
