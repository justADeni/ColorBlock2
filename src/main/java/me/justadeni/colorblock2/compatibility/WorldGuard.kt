package me.justadeni.colorblock2.compatibility

import com.github.justadeni.HexColorLib.color
import com.sk89q.worldedit.bukkit.BukkitAdapter
import com.sk89q.worldguard.WorldGuard
import com.sk89q.worldguard.bukkit.WorldGuardPlugin
import com.sk89q.worldguard.protection.flags.Flag
import com.sk89q.worldguard.protection.flags.Flags
import com.sk89q.worldguard.protection.flags.StateFlag
import com.sk89q.worldguard.protection.flags.registry.FlagConflictException
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry
import me.justadeni.colorblock2.ColorBlock2
import org.bukkit.block.Block
import org.bukkit.entity.Player


class WorldGuard(val player: Player, val block: Block, val plugin: ColorBlock2) {
    companion object {
        private var BLOCK_COLOR: StateFlag? = null

        fun flag(): Boolean {
            if (BLOCK_COLOR != null)
                return true

            val registry: FlagRegistry = WorldGuard.getInstance().flagRegistry

            return try {
                val flag = StateFlag("block-color", false)
                registry.register(flag)
                BLOCK_COLOR = flag
                true
            } catch (e: FlagConflictException) {
                val existing: Flag<*>? = registry.get("block-color")

                if (existing is StateFlag) {
                    BLOCK_COLOR = existing
                    true
                } else {
                    false
                }
            }

        }
    }

    fun can(): Boolean {
        if (player.hasPermission(ColorBlock2.confik.adminpermission))
            return true

        val localPlayer = WorldGuardPlugin.inst().wrapPlayer(player)
        val query = WorldGuard.getInstance().platform.regionContainer.createQuery()

        val can = query.testState(BukkitAdapter.adapt(block.location), localPlayer, BLOCK_COLOR)

        if (query.getApplicableRegions(BukkitAdapter.adapt(block.location)).regions.isNotEmpty()) {
            if (can) {
                if (!ColorBlock2.confik.worldguardallowmessage.equals("NONE", true))
                    player.sendMessage(ColorBlock2.confik.pluginprefix + ColorBlock2.confik.worldguardallowmessage.color())
                else if (!ColorBlock2.confik.worldguarddisallowmessage.equals("NONE", true))
                    player.sendMessage(ColorBlock2.confik.pluginprefix + ColorBlock2.confik.worldguarddisallowmessage.color())
            }
        }


        return can
    }

}