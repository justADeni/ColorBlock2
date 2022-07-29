package me.justadeni.colorblock2.misc

import org.bukkit.ChatColor

object Msg {
    fun colorMsg(message : String) : String {
        return ChatColor.translateAlternateColorCodes('&', message)
    }
}