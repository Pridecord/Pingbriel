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
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can toggle ping sound.");
            return true;
        }
        if (args.length != 1 || (!args[0].equalsIgnoreCase("true") && !args[0].equalsIgnoreCase("false"))) {
            sender.sendMessage("Usage: /ping_sound <true|false>");
            return true;
        }
        boolean on = Boolean.parseBoolean(args[0]);
        Player p = (Player) sender;
        plugin.setPingSoundEnabled(p.getUniqueId(), on);
        sender.sendMessage("Ping sound " + (on ? "enabled" : "disabled") + ".");
        return true;
    }
}
