package com.erilanetwork.staffchat.chat.base;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.entity.Player;

@Getter
@RequiredArgsConstructor
public abstract class AbstractChatChannel implements ChatChannel {

    private final String name;
    private final String prefix;
    private final String permission;
    private final String format;

    @Override
    public Component render(Player sender, String message) {
        return MiniMessage.miniMessage().deserialize(
                format,
                Placeholder.unparsed("player", sender.getName()),
                Placeholder.unparsed("message", message)
        );
    }
}
