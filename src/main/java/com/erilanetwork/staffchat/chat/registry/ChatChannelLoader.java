package com.erilanetwork.staffchat.chat.registry;

import com.erilanetwork.staffchat.chat.impl.AdminChatChannel;
import com.erilanetwork.staffchat.chat.impl.StaffChatChannel;
import lombok.RequiredArgsConstructor;
import org.bukkit.plugin.java.JavaPlugin;

@RequiredArgsConstructor
public class ChatChannelLoader {

    private final JavaPlugin plugin;
    private final ChatChannelRegistry registry;

    public void load() {
        registry.clear();
        registry.register(new AdminChatChannel(
                read("channels.admin.prefix", "**"),
                read("channels.admin.permission", "erila.admin.chat"),
                read("channels.admin.format", "<dark_red>[AdminChat] <gray><player>: <white><message>")
        ));
        registry.register(new StaffChatChannel(
                read("channels.staff.prefix", "*"),
                read("channels.staff.permission", "erila.staff.chat"),
                read("channels.staff.format", "<blue>[StaffChat] <gray><player>: <white><message>")
        ));
    }

    public void reload() {
        plugin.reloadConfig();
        load();
    }

    private String read(String path, String fallback) {
        return plugin.getConfig().getString(path, fallback);
    }
}
