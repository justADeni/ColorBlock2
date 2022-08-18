package me.justadeni.colorblock2.compatibility

import com.github.justadeni.HexColorLib.color
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

class Lands(val player: Player, val block: Block, val plugin: ColorBlock2){

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

    fun can() : Boolean{
        if (player.hasPermission(ColorBlock2.confik.adminpermission))
            return true

        val landsIntegration = LandsIntegration(plugin)

        return run {
            val area: Area = landsIntegration.getAreaByLoc(block.location) ?: return@run true

            if (area.hasFlag(player.uniqueId, BLOCK_COLOR!!)){
                if (!ColorBlock2.confik.landsallowmessage.equals("NONE", true))
                    player.sendMessage(ColorBlock2.confik.pluginprefix + ColorBlock2.confik.landsallowmessage.color())
                else if (!ColorBlock2.confik.landsdisallowmessage.equals("NONE", true))
                    player.sendMessage(ColorBlock2.confik.pluginprefix + ColorBlock2.confik.landsdisallowmessage.color())

                return@run true
            }

            return@run false
        }
    }
}