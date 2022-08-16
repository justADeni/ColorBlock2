package me.justadeni.colorblock2.compatibility

import com.sk89q.worldguard.protection.flags.StateFlag
import me.angeschossen.lands.api.exceptions.FlagConflictException
import me.angeschossen.lands.api.flags.Flag
import me.angeschossen.lands.api.flags.FlagRegistry
import me.angeschossen.lands.api.flags.Flags
import me.angeschossen.lands.api.flags.types.LandFlag
import me.angeschossen.lands.api.flags.types.RoleFlag
import me.angeschossen.lands.api.integration.LandsIntegration
import me.angeschossen.lands.api.integration.LandsIntegrator
import me.angeschossen.lands.api.land.Area
import me.angeschossen.lands.api.land.Land
import me.angeschossen.lands.api.player.LandPlayer
import me.angeschossen.lands.api.role.Role
import me.justadeni.colorblock2.ColorBlock2
import org.bukkit.block.Block
import org.bukkit.entity.Player

class Lands(player: Player, block: Block, plugin: ColorBlock2) : General(player, block, plugin) {

    companion object{
        private var BLOCK_COLOR : RoleFlag? = null

        fun flag(plugin : ColorBlock2) : Boolean {
            if (BLOCK_COLOR != null)
                return true

            val registry: FlagRegistry = LandsIntegration(plugin).flagRegistry

            return try {
                val flag = RoleFlag(plugin, RoleFlag.Category.ACTION, "BLOCK_COLOR", true, true)
                registry.register(flag)
                BLOCK_COLOR = flag
                true
            } catch (e : FlagConflictException){
                val existing: Flag? = registry.get("BLOCK_COLOR")

                if (existing is RoleFlag) {
                    BLOCK_COLOR = existing
                    true
                } else {
                    false
                }
            }
        }
    }

    override fun can() : Boolean{
        if (player.hasPermission(ColorBlock2.confik.adminpermission))
            return true

        val landsIntegration = LandsIntegration(plugin)
        val area: Area = landsIntegration.getAreaByLoc(block.location) ?: return true

        if (area.hasFlag(player.uniqueId, BLOCK_COLOR!!))
            return true

        return false
    }
}