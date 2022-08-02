package me.justadeni.colorblock2.listeners

import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import me.justadeni.colorblock2.ColorBlock2
import me.justadeni.colorblock2.enums.Blocks
import me.justadeni.colorblock2.enums.Dyes
import me.justadeni.colorblock2.transformers.Color.Color
import me.justadeni.colorblock2.transformers.Uncolor.Uncolor
import org.bukkit.GameMode
import org.bukkit.Sound
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent

object BlockClick : Listener {

    @EventHandler
    suspend fun onBlockClick(e : PlayerInteractEvent) = coroutineScope{
        launch {
            if (!(e.player.hasPermission(ColorBlock2.confik.usepermission) || e.player.hasPermission(ColorBlock2.confik.adminpermission)))
                //return@launch
                cancel()

            if (e.action != Action.RIGHT_CLICK_BLOCK)
                cancel()

            val block = e.clickedBlock!!
            val blockname = block.type.name

            if (Blocks.match(blockname) == "")
                cancel()

            val player = e.player

            val mainhand = player.inventory.itemInMainHand
            val offhand = player.inventory.itemInOffHand

            var iscreative: Boolean = player.gameMode == GameMode.CREATIVE

            if (player.isSneaking) {
                val slot: Boolean = if (mainhand.type.isAir) {
                    true
                } else if (offhand.type.isAir) {
                    false
                } else {
                    cancel()
                    false
                }
                e.isCancelled = true
                if (iscreative)
                    if (ColorBlock2.confik.droponcreative)
                        iscreative = false
                Uncolor(block, blockname, !iscreative)
            } else {
                val slot: Boolean = if ((mainhand.type.name).contains("DYE")) {
                    true
                } else if ((offhand.type.name).contains("DYE")) {
                    false
                } else {
                    cancel()
                    false
                }

                var dye: String = if (slot) {
                    mainhand.type.name
                } else {
                    offhand.type.name
                }
                e.isCancelled = true
                if (iscreative)
                    if (ColorBlock2.confik.useoncreative)
                        iscreative = false
                Color(block, dye, blockname, player, slot, !iscreative, !iscreative)

            }
        }
    }

}