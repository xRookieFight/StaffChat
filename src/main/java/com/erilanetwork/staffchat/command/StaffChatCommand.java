package com.erilanetwork.staffchat.command;

import com.erilanetwork.staffchat.chat.registry.ChatChannelLoader;
import com.erilanetwork.staffchat.message.MessageService;
import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@RequiredArgsConstructor
public class StaffChatCommand implements CommandExecutor, TabCompleter {

    private static final String RELOAD_PERMISSION = "erila.staffchat.reload";

    private final ChatChannelLoader loader;
    private final MessageService messages;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length != 1 || !args[0].equalsIgnoreCase("reload")) {
            sender.sendMessage(messages.get("command.usage", Placeholder.unparsed("label", label)));
            return true;
        }

        if (!sender.hasPermission(RELOAD_PERMISSION)) {
            sender.sendMessage(messages.get("command.no-permission"));
            return true;
        }

        loader.reload();
        messages.load();
        sender.sendMessage(messages.get("command.reloaded"));
        return true;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1 && sender.hasPermission(RELOAD_PERMISSION)) {
            return List.of("reload");
        }
        return List.of();
    }
}
