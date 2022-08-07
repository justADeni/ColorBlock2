package me.justadeni.colorblock2.listeners

import com.github.shynixn.mccoroutine.bukkit.launch
import com.github.shynixn.mccoroutine.bukkit.ticks
import kotlinx.coroutines.*
import kotlinx.coroutines.NonCancellable.cancel
import me.justadeni.colorblock2.ColorBlock2
import me.justadeni.colorblock2.enums.Blocks
import me.justadeni.colorblock2.transformers.Color.Color
import me.justadeni.colorblock2.transformers.Uncolor.Uncolor
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.block.Block
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.MainHand
import java.util.UUID

class BlockClick(private val plugin : ColorBlock2) : Listener {

    @EventHandler
    suspend fun onBlockClick(e : PlayerInteractEvent) {
        if (!(e.player.hasPermission(ColorBlock2.confik.usepermission) || e.player.hasPermission(ColorBlock2.confik.adminpermission)))
            return

        if (e.action != Action.RIGHT_CLICK_BLOCK)
            return

        if (e.hand == null)
            return

        val block = e.clickedBlock!!

        val blockname = block.type.name

        if (Blocks.match(blockname) == "")
            return

        var slot = true
        val player = e.player
        val hand = if (e.hand == EquipmentSlot.HAND) {
            player.inventory.itemInMainHand
        } else {
            slot = false
            player.inventory.itemInOffHand
        }

        val iscreative: Boolean = player.gameMode == GameMode.CREATIVE

        if (player.isSneaking) {
            if (!hand.type.isAir) {
                return
            }

            e.isCancelled = true

            val droponcreative: Boolean = if (iscreative) {
                ColorBlock2.confik.droponcreative
            } else {
                true
            }

            Uncolor(block, blockname, player, droponcreative)
        } else {
            if (!hand.type.name.contains("DYE"))
                return

            val dye = hand.type.name

            e.isCancelled = true

            val useoncreative: Boolean = if (iscreative) {
                ColorBlock2.confik.useoncreative
            } else {
                true
            }

            val droponcreative: Boolean = if (iscreative) {
                ColorBlock2.confik.droponcreative
            } else {
                true
            }


            Color(block, dye, blockname, player, slot, useoncreative, droponcreative)
        }

    }
}