package dev.sdriver1.pingbriel;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PingSoundTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            String partial = args[0].toLowerCase();
            return Stream.of("on", "off")
                    .filter(opt -> opt.startsWith(partial))
                    .collect(Collectors.toList());
        }
        // no suggestions for further arguments
        return List.of();
    }
}
