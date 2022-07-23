package me.justadeni.colorblock2.listeners

import me.justadeni.colorblock2.enums.Blocks
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent

object BlockClick : Listener{

    @EventHandler
    fun onBlockClick(e : PlayerInteractEvent){
        if (e.action != Action.RIGHT_CLICK_BLOCK)
            return

        val blockname = e.clickedBlock!!.type.name

        if (!e.hasItem())
            if (e.player.isSneaking)
                if (Blocks.names().contains(blockname))

        if (e.player.isSneaking)
            return

    }

}