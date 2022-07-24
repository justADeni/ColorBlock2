package me.justadeni.colorblock2

import me.justadeni.colorblock2.listeners.BlockClick
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.plugin.java.JavaPlugin

class ColorBlock2 : JavaPlugin() {
    override fun onEnable() {
        Bukkit.getLogger().info(colorMsg("&6Plugin Enabled"))
        server.pluginManager.registerEvents(BlockClick, this)
    }

    override fun onDisable() {
        Bukkit.getLogger().info(colorMsg("&3Plugin Disabled"))
    }

    companion object {
        fun colorMsg(message : String) : String {
            return ChatColor.translateAlternateColorCodes('&', message)
        }
    }
}