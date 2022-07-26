package me.justadeni.colorblock2

import me.justadeni.colorblock2.listeners.BlockClick
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.plugin.java.JavaPlugin

class ColorBlock2 : JavaPlugin() {
    override fun onEnable() {
        server.pluginManager.registerEvents(BlockClick, this)
    }

    override fun onDisable() {

    }

    companion object {
        fun colorMsg(message : String) : String {
            return ChatColor.translateAlternateColorCodes('&', message)
        }
    }
}