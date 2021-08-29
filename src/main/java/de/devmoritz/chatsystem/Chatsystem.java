package de.devmoritz.chatsystem;

import de.devmoritz.chatsystem.listener.ChatListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import net.luckperms.api.LuckPerms;

public final class Chatsystem extends JavaPlugin implements Listener {
    private static Chatsystem plugin;
    private static LuckPerms luckPermsProvider;
    public FileConfiguration config = getConfig();
    public String prefix = config.getString("global.prefix");
    public String noPerms = config.getString("global.noPerms");

    public static Chatsystem use() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;
        this.getServer().getPluginManager().registerEvents(this, this);
        registerEvents();
        registerCommands();
        configureConfig();

        Bukkit.getConsoleSender().sendMessage(reformat(prefix + " &8Plugin wurde erfolgreich &ageladen&8! | &6Made by §4D§6e§ev§2M§ao§br§1i§dt§5z"));
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(reformat(prefix + " &8Plugin wurde erfolgreich &4entladen&8! | &6Made by §4D§6e§ev§2M§ao§br§1i§dt§5z"));
    }

    public static String reformat(String format) {
        return ChatColor.translateAlternateColorCodes('&', format);
    }

    public void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new ChatListener(), this);
    }

    public void registerCommands() {
//        this.getCommand("chatsystem").setExecutor(new ReloadCommand(this));
    }

    public void configureConfig() {
        config.addDefault("settings.prefixSpacer", "&8| &f");
        config.addDefault("settings.nameSpacer", "&8➡ &7");
        config.addDefault("settings.standardName", "&7Spieler");
        config.options().copyDefaults(true);
        saveConfig();
    }
}
