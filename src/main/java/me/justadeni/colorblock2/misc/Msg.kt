package me.justadeni.colorblock2.misc

import com.github.justadeni.HexColorLib.color
import me.justadeni.colorblock2.ColorBlock2
import net.md_5.bungee.api.ChatColor

class Msg(private val plugin : ColorBlock2) {

    fun printError(msg: String) {
        val console = plugin.server.consoleSender
        console.sendMessage(ColorBlock2.confik.pluginprefix.color() +("#AB4C37$msg#FF0101").color())
    }

    fun printSuccess(msg: String) {
        val console = plugin.server.consoleSender
        console.sendMessage(ColorBlock2.confik.pluginprefix.color() + ("#3A9C20$msg#31E900").color())
    }
}