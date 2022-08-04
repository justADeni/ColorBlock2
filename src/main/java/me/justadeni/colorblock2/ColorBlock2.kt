package me.justadeni.colorblock2

import com.github.shynixn.mccoroutine.bukkit.registerSuspendingEvents
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
        server.pluginManager.registerSuspendingEvents(BlockClick, this)
        getCommand("colorblock")?.setExecutor(Command)
        confik.assign()
    }

    override fun onDisable() {

    }
}