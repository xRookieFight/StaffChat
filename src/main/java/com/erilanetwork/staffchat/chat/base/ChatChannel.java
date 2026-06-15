package com.erilanetwork.staffchat.chat.base;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

public interface ChatChannel {

    String getName();

    String getPrefix();

    String getPermission();

    Component render(Player sender, String message);
}
