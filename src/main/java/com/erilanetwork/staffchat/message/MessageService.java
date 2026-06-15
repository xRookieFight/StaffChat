package com.erilanetwork.staffchat.message;

import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

@RequiredArgsConstructor
public class MessageService {

    private static final String FILE_NAME = "messages.yml";

    private final JavaPlugin plugin;
    private FileConfiguration messages;

    public void load() {
        File file = new File(plugin.getDataFolder(), FILE_NAME);
        if (!file.exists()) {
            plugin.saveResource(FILE_NAME, false);
        }
        messages = YamlConfiguration.loadConfiguration(file);
    }

    public Component get(String key, TagResolver... resolvers) {
        return MiniMessage.miniMessage().deserialize(raw(key), resolvers);
    }

    public String plain(String key, TagResolver... resolvers) {
        return PlainTextComponentSerializer.plainText().serialize(get(key, resolvers));
    }

    private String raw(String key) {
        return messages.getString(key, key);
    }
}
