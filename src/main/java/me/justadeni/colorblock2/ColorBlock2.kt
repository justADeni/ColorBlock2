package me.justadeni.colorblock2

import me.justadeni.colorblock2.listeners.BlockClick
import org.bukkit.plugin.java.JavaPlugin

class ColorBlock2 : JavaPlugin() {
    override fun onEnable() {
        server.pluginManager.registerEvents(BlockClick, this)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}