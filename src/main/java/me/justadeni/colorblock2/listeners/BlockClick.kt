package me.justadeni.colorblock2.listeners

import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent

class BlockClick {

    @Override
    fun onBlockClick(e : PlayerInteractEvent){
        if (e.action != Action.RIGHT_CLICK_BLOCK)
            return
        if (!e.hasItem())
            if (e.player.isSneaking)
                //
        if (e.player.isSneaking)
            return

    }

}