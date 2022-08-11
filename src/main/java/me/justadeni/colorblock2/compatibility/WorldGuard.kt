package me.justadeni.colorblock2.compatibility

import com.sk89q.worldedit.bukkit.BukkitAdapter
import com.sk89q.worldedit.math.BlockVector3
import com.sk89q.worldguard.WorldGuard
import com.sk89q.worldguard.bukkit.WorldGuardPlugin
import com.sk89q.worldguard.protection.ApplicableRegionSet
import com.sk89q.worldguard.protection.flags.Flags
import com.sk89q.worldguard.protection.managers.RegionManager
import com.sk89q.worldguard.protection.regions.ProtectedRegion
import com.sk89q.worldguard.protection.regions.RegionContainer
import org.bukkit.block.Block
import org.bukkit.entity.Player


object WorldGuard {

    fun canDye(player: Player, block: Block): Boolean {
        /*
        val container: RegionContainer = WorldGuard.getInstance().platform.regionContainer
        val regionmanager: RegionManager = container.get(BukkitAdapter.adapt(block.world)) ?: return true
        val vector: BlockVector3? = BukkitAdapter.asBlockVector(block.location)
        val set: ApplicableRegionSet = regionmanager.getApplicableRegions(vector)
        if (set.size() == 0)
            return true

        var topregion: ProtectedRegion? = null
        for (region in set) {
            if (topregion == null)
                topregion = region
            else if (region.priority > topregion.priority)
                topregion = region
        }
         */

        val localPlayer = WorldGuardPlugin.inst().wrapPlayer(player)
        val query = WorldGuard.getInstance().platform.regionContainer.createQuery()
        return query.testState(BukkitAdapter.adapt(block.location), localPlayer, Flags.BLOCK_BREAK)
    }

}