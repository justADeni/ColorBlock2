package me.justadeni.colorblock2.misc

import com.github.justadeni.HexColorLib.color
import me.justadeni.colorblock2.ColorBlock2
import net.md_5.bungee.api.ChatColor

class Msg(private val plugin : ColorBlock2) {

    fun printError(msg: String) {
        val console = plugin.server.consoleSender

        val color1 = "#AB4C37"
        val color2 = "#FF0101"

        console.sendMessage(ColorBlock2.confik.pluginprefix.color() +(color1 + msg + color2).color())
    }

    fun printSuccess(msg: String) {
        val console = plugin.server.consoleSender

        val color1 = "#3A9C20"
        val color2 = "#31E900"

        console.sendMessage(ColorBlock2.confik.pluginprefix.color() + (color1 + msg + color2).color())
    }
}