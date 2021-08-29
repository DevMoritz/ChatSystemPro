package de.devmoritz.chatsystem.listener;

import de.devmoritz.chatsystem.Chatsystem;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Objects;

import static de.devmoritz.chatsystem.Chatsystem.reformat;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        LuckPerms luckPerms = LuckPermsProvider.get();
        Player p = e.getPlayer();
        String prefix = "";

        try {
            String group = luckPerms.getUserManager().getUser(p.getUniqueId()).getPrimaryGroup();
            System.out.println(group);
            prefix = luckPerms.getGroupManager().getGroup(group).getDisplayName();
            System.out.println(prefix);
        } catch (NullPointerException exception) {
            prefix = "";
        }
        // Prepare Message
        String message = e.getMessage();
        if ((p.hasPermission("chatsystem.colors"))) {
            message = reformat(message);
        }

        if (!Objects.equals(prefix, null)) {
            e.setFormat(reformat(prefix + " " + Chatsystem.use().config.getString("settings.prefixSpacer") + p.getDisplayName() + " " + Chatsystem.use().config.getString("settings.nameSpacer")) + message);
        } else {
            e.setFormat(reformat(Chatsystem.use().config.getString("settings.standardName") + " " + Chatsystem.use().config.getString("settings.prefixSpacer") + p.getDisplayName() + " " + Chatsystem.use().config.getString("settings.nameSpacer")) + message);
        }
    }
}