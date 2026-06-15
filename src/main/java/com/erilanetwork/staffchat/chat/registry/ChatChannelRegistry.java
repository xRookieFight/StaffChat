package com.erilanetwork.staffchat.chat.registry;

import com.erilanetwork.staffchat.chat.base.ChatChannel;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Comparator;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ChatChannelRegistry {

    private final ConcurrentMap<String, ChatChannel> channels = new ConcurrentHashMap<>();

    public void register(ChatChannel channel) {
        channels.put(channel.getName(), channel);
    }

    public void unregister(String name) {
        channels.remove(name);
    }

    public void clear() {
        channels.clear();
    }

    public Optional<ChatChannel> match(String message) {
        return channels.values().stream()
                .filter(channel -> message.startsWith(channel.getPrefix()))
                .max(Comparator.comparingInt(channel -> channel.getPrefix().length()));
    }

    public void broadcast(ChatChannel channel, Player sender, String message) {
        Component rendered = channel.render(sender, message);
        Bukkit.getConsoleSender().sendMessage(rendered);
        Bukkit.getOnlinePlayers().stream()
                .filter(player -> player.hasPermission(channel.getPermission()))
                .forEach(player -> player.sendMessage(rendered));
    }
}
