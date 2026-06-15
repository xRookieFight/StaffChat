package com.erilanetwork.staffchat;

import com.erilanetwork.staffchat.chat.registry.ChatChannelLoader;
import com.erilanetwork.staffchat.chat.registry.ChatChannelRegistry;
import com.erilanetwork.staffchat.command.StaffChatCommand;
import com.erilanetwork.staffchat.listener.EventListener;
import com.erilanetwork.staffchat.message.MessageService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin {

    @Getter
    @Setter(AccessLevel.PRIVATE)
    private static Plugin plugin;

    @Getter
    @Setter(AccessLevel.PRIVATE)
    private ChatChannelLoader chatChannelLoader;

    @Getter
    @Setter(AccessLevel.PRIVATE)
    private MessageService messageService;

    @Getter
    private final ChatChannelRegistry chatChannelRegistry = new ChatChannelRegistry();

    @Override
    public void onLoad() {
        setPlugin(this);
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        setMessageService(new MessageService(this));
        getMessageService().load();
        setChatChannelLoader(new ChatChannelLoader(this, chatChannelRegistry));
        getChatChannelLoader().load();
        getServer().getPluginManager().registerEvents(new EventListener(chatChannelRegistry), this);
        StaffChatCommand staffChatCommand = new StaffChatCommand(getChatChannelLoader(), getMessageService());
        getCommand("staffchat").setExecutor(staffChatCommand);
        getCommand("staffchat").setTabCompleter(staffChatCommand);
        getLogger().info(getMessageService().plain("log.enabled"));
    }

    @Override
    public void onDisable() {
        if (getMessageService() != null) {
            getLogger().info(getMessageService().plain("log.disabled"));
        }
    }
}
