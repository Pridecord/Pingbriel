package dev.sdriver1.pingbriel;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import net.kyori.adventure.text.format.NamedTextColor;

import java.util.Set;
import java.util.stream.Collectors;

public class PingColorCommand implements CommandExecutor {
    private final Pingbriel plugin;
    private static final MiniMessage mm = MiniMessage.miniMessage();

    public PingColorCommand(Pingbriel plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage("Usage: /" + label + " <preset|#RRGGBB>");
            sender.sendMessage("Presets: " + presetList());
            return true;
        }

        String input = args[0];
        String chosen = null;

        if (input.matches("^#[0-9A-Fa-f]{6}$")) {
            chosen = input.toLowerCase();
        } else if (input.matches("^[0-9A-Fa-f]{6}$")) {
            chosen = "#" + input.toLowerCase();
        } else {
            String lower = input.toLowerCase();
            if (NamedTextColor.NAMES.value(lower) != null) {
                chosen = lower;
            }
        }

        if (chosen == null) {
            sender.sendMessage("Invalid color. Use a preset or #RRGGBB.");
            sender.sendMessage("Presets: " + presetList());
            return true;
        }

        plugin.getPingColors().put(p.getUniqueId(), chosen);
        Component feedback = mm.deserialize("Ping color set to <" + chosen + ">" + chosen + "</" + chosen + ">.");
        sender.sendMessage(feedback);
        return true;
    }

    private static String presetList() {
        Set<String> keys = NamedTextColor.NAMES.keys();
        return keys.stream().sorted().collect(Collectors.joining(", "));
    }
}
