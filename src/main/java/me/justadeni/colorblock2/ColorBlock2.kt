package me.justadeni.colorblock2

import me.justadeni.colorblock2.listeners.BlockClick
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class ColorBlock2 : JavaPlugin() {
    override fun onEnable() {
        Bukkit.getLogger().info("plugin enabled")
        server.pluginManager.registerEvents(BlockClick, this)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}