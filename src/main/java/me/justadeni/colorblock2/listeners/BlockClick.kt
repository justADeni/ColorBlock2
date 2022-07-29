package me.justadeni.colorblock2.listeners

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import me.justadeni.colorblock2.enums.Blocks
import me.justadeni.colorblock2.enums.Dyes
import me.justadeni.colorblock2.transformers.Color.Color
import me.justadeni.colorblock2.transformers.Uncolor.Uncolor
import org.bukkit.GameMode
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent

object BlockClick : Listener{

    @EventHandler
    fun onBlockClick(e : PlayerInteractEvent) = runBlocking{
        launch {
            if (e.action != Action.RIGHT_CLICK_BLOCK)
                return@launch

            val block = e.clickedBlock!!
            val blockname = block.type.name

            if (Blocks.match(blockname) == "")
                return@launch

            val player = e.player

            val mainhand = player.inventory.itemInMainHand
            val offhand = player.inventory.itemInOffHand

            val iscreative: Boolean = player.gameMode == GameMode.CREATIVE

            if (player.isSneaking) {
                val slot: Boolean = if (mainhand.type.isAir) {
                    true
                } else if (offhand.type.isAir) {
                    false
                } else {
                    return@launch
                }
                e.isCancelled = true
                Uncolor(block, blockname, !iscreative)
            } else {
                val slot: Boolean = if (Dyes.match(mainhand.type.name) != "") {
                    true
                } else if (Dyes.match(offhand.type.name) != "") {
                    false
                } else {
                    return@launch
                }

                var dye: String = if (slot) {
                    mainhand.type.name
                } else {
                    offhand.type.name
                }
                e.isCancelled = true
                Color(block, dye, blockname, player, slot, !iscreative, !iscreative)

            }
        }
    }
}