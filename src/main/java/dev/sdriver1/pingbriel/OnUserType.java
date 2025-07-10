package dev.sdriver1.pingbriel;

import io.papermc.paper.chat.ChatRenderer;
import io.papermc.paper.event.player.ChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

public class OnUserType implements Listener {
    private static final MiniMessage mm = MiniMessage.miniMessage();
    private static final Pattern MENTION = Pattern.compile("(@\\w+)");
    private final Pingbriel plugin;

    public OnUserType(Pingbriel plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onChat(ChatEvent event) {
        String raw = mm.serialize(event.message());

        // play ping sound, case-insensitive
        String lower = raw.toLowerCase();
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (lower.contains("@" + p.getName().toLowerCase())) {
                p.playSound(p.getLocation(),
                        Sound.ENTITY_EXPERIENCE_ORB_PICKUP,
                        2f, 1f);
            }
        }

        ChatRenderer original = event.renderer();
        event.renderer((sender, displayName, msg, viewer) -> {
            Component base = original.render(sender, displayName, msg, viewer);
            String serialized = mm.serialize(base);

            String color = "yellow";
            if (viewer instanceof Player pViewer) {
                color = plugin.getPingColors()
                        .getOrDefault(pViewer.getUniqueId(), "yellow");
            }

            String replaced = serialized.replaceAll(
                    "(@\\w+)",
                    "<" + color + "><b>$1</b></" + color + ">"
            );
            return mm.deserialize("<white>" + replaced + "</white>");
        });
    }
}
