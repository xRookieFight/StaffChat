package com.erilanetwork.staffchat.listener;

import com.erilanetwork.staffchat.chat.base.ChatChannel;
import com.erilanetwork.staffchat.chat.registry.ChatChannelRegistry;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Optional;

@RequiredArgsConstructor
public class EventListener implements Listener {

    private final ChatChannelRegistry registry;

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onChat(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        Optional<ChatChannel> match = registry.match(message);
        if (match.isEmpty()) {
            return;
        }

        ChatChannel channel = match.get();
        Player sender = event.getPlayer();
        if (!sender.hasPermission(channel.getPermission())) {
            return;
        }

        String stripped = message.substring(channel.getPrefix().length()).trim();
        if (stripped.isEmpty()) {
            return;
        }

        event.setCancelled(true);
        registry.broadcast(channel, sender, stripped);
    }
}
