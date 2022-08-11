package me.justadeni.colorblock2

import com.github.shynixn.mccoroutine.bukkit.SuspendingJavaPlugin
import com.github.shynixn.mccoroutine.bukkit.registerSuspendingEvents
import com.github.shynixn.mccoroutine.bukkit.setSuspendingExecutor
import com.github.shynixn.mccoroutine.bukkit.setSuspendingTabCompleter
import me.justadeni.colorblock2.command.Command
import me.justadeni.colorblock2.command.TabComplete
import me.justadeni.colorblock2.compatibility.CompatibilityManager
import me.justadeni.colorblock2.listeners.BlockClick
import me.justadeni.colorblock2.misc.Msg

class ColorBlock2 : SuspendingJavaPlugin() {

    companion object {
        lateinit var confik: Config
        lateinit var msg: Msg
        lateinit var compatibilityManager: CompatibilityManager
    }

    override suspend fun onEnableAsync() {
        confik = Config(this)
        msg = Msg(this)
        compatibilityManager = CompatibilityManager(this)
        saveDefaultConfig()
        compatibilityManager.init()
        server.pluginManager.registerSuspendingEvents(BlockClick(), this)
        getCommand("colorblock")?.setSuspendingExecutor(Command)
        getCommand("colorblock")?.setSuspendingTabCompleter(TabComplete)
        confik.assign()
    }

    override suspend fun onDisableAsync() {

    }
}