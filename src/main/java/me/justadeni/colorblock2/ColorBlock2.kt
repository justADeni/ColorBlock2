package me.justadeni.colorblock2

import me.justadeni.colorblock2.command.Command
import me.justadeni.colorblock2.listeners.BlockClick
import org.bukkit.plugin.java.JavaPlugin

class ColorBlock2 : JavaPlugin() {

    companion object {
        lateinit var confik: Config
    }

    override fun onEnable() {
        confik = Config(this)
        saveDefaultConfig()
        server.pluginManager.registerEvents(BlockClick, this)
        getCommand("colorblock")?.setExecutor(Command)
        confik.assign()
    }

    override fun onDisable() {

    }
}