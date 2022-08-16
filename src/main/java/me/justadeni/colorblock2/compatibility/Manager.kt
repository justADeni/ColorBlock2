package me.justadeni.colorblock2.compatibility

import com.github.justadeni.HexColorLib.color
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import me.justadeni.colorblock2.ColorBlock2
import org.bukkit.block.Block
import org.bukkit.entity.Player

class Manager(private val plugin : ColorBlock2) {

    private var worldguard = false
    private var lands = false

    suspend fun init(){
        if (ColorBlock2.confik.worldguardhook) {
            if (plugin.server.pluginManager.getPlugin("WorldGuard") != null && WorldGuard.flag()) {
                worldguard = true
                ColorBlock2.msg.printSuccess("WorldGuard hook loaded successfully")
            } else {
                ColorBlock2.msg.printError("WorldGuard hook failed to load")
            }
        }
        if (ColorBlock2.confik.landshook) {
            if (plugin.server.pluginManager.getPlugin("Lands") != null && Lands.flag(plugin)) {
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

                val pluginprefix = ColorBlock2.confik.pluginprefix.color()

                if (worldguard)
                    if (WorldGuard(player, block, plugin).can()) {
                        val msg = ColorBlock2.confik.worldguardallowmessage
                        if (!msg.equals("NONE", true))
                            player.sendMessage(pluginprefix + msg.color())

                        return@async false
                    } else {
                        val msg = ColorBlock2.confik.worldguarddisallowmessage
                        if (!msg.equals("NONE", true))
                            player.sendMessage(pluginprefix + msg.color())
                    }

                if (lands)
                    if (Lands(player, block, plugin).can()) {
                        val msg = ColorBlock2.confik.landsallowmessage
                        if (!msg.equals("NONE", true))
                            player.sendMessage(pluginprefix + msg.color())

                        return@async false
                    } else {
                        val msg = ColorBlock2.confik.landsdisallowmessage
                        if (!msg.equals("NONE", true))
                            player.sendMessage(pluginprefix + msg.color())
                    }

                return@async true
            }
        }

        return permission.await()
    }
}