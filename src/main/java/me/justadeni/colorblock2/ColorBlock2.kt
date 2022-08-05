package me.justadeni.colorblock2

import com.github.shynixn.mccoroutine.bukkit.SuspendingJavaPlugin
import com.github.shynixn.mccoroutine.bukkit.registerSuspendingEvents
import com.github.shynixn.mccoroutine.bukkit.setSuspendingExecutor
import me.justadeni.colorblock2.command.Command
import me.justadeni.colorblock2.listeners.BlockClick
import org.bukkit.plugin.java.JavaPlugin

class ColorBlock2 : SuspendingJavaPlugin() {

    companion object {
        lateinit var confik: Config
    }

    override suspend fun onEnableAsync() {
        confik = Config(this)
        saveDefaultConfig()
        server.pluginManager.registerSuspendingEvents(BlockClick(this), this)
        getCommand("colorblock")?.setSuspendingExecutor(Command)
        confik.assign()
    }

    override suspend fun onDisableAsync() {

    }
}