package dev.sdriver1.pingbriel;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PingSoundCommand implements CommandExecutor {
    private final Pingbriel plugin;

    public PingSoundCommand(Pingbriel plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players may use this command.");
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage("Usage: /ping_sound <on|off>");
            return true;
        }

        String arg = args[0].toLowerCase();
        boolean enabled;
        if (arg.equals("on") || arg.equals("true")) {
            enabled = true;
        } else if (arg.equals("off") || arg.equals("false")) {
            enabled = false;
        } else {
            sender.sendMessage("Usage: /ping_sound <on|off>");
            return true;
        }

        plugin.setPingSoundEnabled(player.getUniqueId(), enabled);

        sender.sendMessage("Ping sound " + (enabled ? "enabled" : "disabled") + ".");
        return true;
    }
}
