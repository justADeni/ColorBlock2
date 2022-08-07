package me.justadeni.colorblock2

import com.github.shynixn.mccoroutine.bukkit.SuspendingJavaPlugin
import com.github.shynixn.mccoroutine.bukkit.registerSuspendingEvents
import com.github.shynixn.mccoroutine.bukkit.setSuspendingExecutor
import com.github.shynixn.mccoroutine.bukkit.setSuspendingTabCompleter
import me.justadeni.colorblock2.command.Command
import me.justadeni.colorblock2.command.TabComplete
import me.justadeni.colorblock2.listeners.BlockClick

class ColorBlock2 : SuspendingJavaPlugin() {

    companion object {
        lateinit var confik: Config
    }

    override suspend fun onEnableAsync() {
        confik = Config(this)
        saveDefaultConfig()
        server.pluginManager.registerSuspendingEvents(BlockClick(), this)
        getCommand("colorblock")?.setSuspendingExecutor(Command)
        getCommand("colorblock")?.setSuspendingTabCompleter(TabComplete)
        confik.assign()
    }

    override suspend fun onDisableAsync() {

    }
}