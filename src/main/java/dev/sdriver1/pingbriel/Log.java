package dev.sdriver1.pingbriel;

// Yoinked from Gaybriel
// https://github.com/DeveloLongScript/GaybrielMC/blob/04f044fc4fa236c25840f1dfdb5b28e5d585bd48/src/main/java/me/dvelo/gaybrielMC/objects/Log.java

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class Log {
    public static Component success(String text) {
        return MiniMessage.miniMessage().deserialize("<#6ef713>[s] " + text);
    }
    public static Component error(String text) {
        return MiniMessage.miniMessage().deserialize("<#cc1006>[e] " + text);
    }
    public static Component info(String text) {
        return MiniMessage.miniMessage().deserialize("<#05abff>[i] " + text);
    }
    public static Component infoNoTag(String text) {
        return MiniMessage.miniMessage().deserialize("<#05abff>" + text);
    }
    public static Component warn(String text) {
        return MiniMessage.miniMessage().deserialize("<#ffcd05>[w] " + text);
    }
}