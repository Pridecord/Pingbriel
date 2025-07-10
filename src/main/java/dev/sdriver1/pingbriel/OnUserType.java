package dev.sdriver1.pingbriel;

import io.papermc.paper.chat.ChatRenderer;
import io.papermc.paper.event.player.ChatEvent;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.List;
import java.util.regex.Pattern;

public class OnUserType implements Listener {
    private static final MiniMessage mm = MiniMessage.miniMessage();
    private static final Pattern MENTION = Pattern.compile("(@\\w+)");

    @EventHandler
    public void OnUserSendMessage(ChatEvent event) {
        String rawMessage = mm.serialize(event.message());
        String lower = rawMessage.toLowerCase();
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (lower.contains("@" + p.getName().toLowerCase())) {
                p.playSound(
                        p.getLocation(),
                        Sound.ENTITY_EXPERIENCE_ORB_PICKUP,
                        2f, 1f
                );
            }
        }

        ChatRenderer renderer = event.renderer();
        event.renderer((sender, displayName, msg, viewer) -> {
            Component base = renderer.render(sender, displayName, msg, viewer);

            String serialized = mm.serialize(base);
            String replaced = serialized.replaceAll(
                    MENTION.pattern(),
                    "<yellow><b>$1</b></yellow>"
            );
            String wrapped = "<white>" + replaced + "</white>";

            return mm.deserialize(wrapped);
        });
    }
}
