package me.justadeni.colorblock2

import me.justadeni.colorblock2.listeners.BlockClick
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.plugin.java.JavaPlugin

class ColorBlock2 : JavaPlugin() {

    private var instance : ColorBlock2? = null

    fun getInstance() : ColorBlock2? {
        return instance
    }

    override fun onEnable() {
        instance = this
        server.pluginManager.registerEvents(BlockClick, this)
        Config.reload()
    }

    override fun onDisable() {
        instance = null
    }
}