package me.justadeni.colorblock2.compatibility

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import me.justadeni.colorblock2.ColorBlock2
import org.bukkit.block.Block
import org.bukkit.entity.Player

class CompatibilityManager(private val plugin : ColorBlock2) {

    private var worldguard = false
    private var lands = false

    suspend fun init(){
        if (ColorBlock2.confik.worldguardhook) {
            if (plugin.server.pluginManager.getPlugin("WorldGuard") != null) {
                worldguard = true
                ColorBlock2.msg.printSuccess("WorldGuard hook loaded successfully")
            } else {
                ColorBlock2.msg.printError("WorldGuard hook failed to load")
            }
        }
        if (ColorBlock2.confik.landshook) {
            if (plugin.server.pluginManager.getPlugin("Lands") != null) {
                lands = true
                ColorBlock2.msg.printSuccess("Lands hook loaded successfully")
            } else {
                ColorBlock2.msg.printError("Lands hook failed to load")
            }
        }
    }

    suspend fun canDye(player : Player, block : Block) : Boolean{

        val permission = coroutineScope {
            async(Dispatchers.IO) {

                if (worldguard)
                    if (!WorldGuard.canDye(player, block))
                        return@async false

                if (lands)
                    if (!Lands.canDye(player, block, plugin))
                        return@async false

                return@async true
            }
        }

        return permission.await()
    }
}