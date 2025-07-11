package dev.sdriver1.pingbriel;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import net.kyori.adventure.text.format.NamedTextColor;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PingColorTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 1) {
            String partial = args[0].toUpperCase();
            return NamedTextColor.NAMES.keys().stream()
                    .filter(name -> name.startsWith(partial))
                    .map(String::toLowerCase)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
