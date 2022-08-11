package me.justadeni.colorblock2.compatibility

import me.angeschossen.lands.api.flags.Flags
import me.angeschossen.lands.api.integration.LandsIntegration
import me.angeschossen.lands.api.land.Area
import me.justadeni.colorblock2.ColorBlock2
import org.bukkit.block.Block
import org.bukkit.entity.Player

object Lands {

    fun canDye(player: Player, block: Block, plugin: ColorBlock2): Boolean {
        val landsIntegration = LandsIntegration(plugin)
        val area: Area = landsIntegration.getAreaByLoc(block.location) ?: return true

        if (area.hasFlag(player.uniqueId, Flags.BLOCK_BREAK))
                return true

        return false
    }
}