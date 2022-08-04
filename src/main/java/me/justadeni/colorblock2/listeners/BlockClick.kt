package me.justadeni.colorblock2.listeners

import kotlinx.coroutines.*
import kotlinx.coroutines.NonCancellable.cancel
import me.justadeni.colorblock2.ColorBlock2
import me.justadeni.colorblock2.enums.Blocks
import me.justadeni.colorblock2.transformers.Color.Color
import me.justadeni.colorblock2.transformers.Uncolor.Uncolor
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent

object BlockClick : Listener {

    @EventHandler
    suspend fun onBlockClick(e : PlayerInteractEvent) {

        println(Bukkit.isPrimaryThread().toString())

        //withContext(Dispatchers.IO){

            println(Bukkit.isPrimaryThread().toString())
                if (!(e.player.hasPermission(ColorBlock2.confik.usepermission) || e.player.hasPermission(ColorBlock2.confik.adminpermission)))
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

                val iscreative: Boolean = player.gameMode == GameMode.CREATIVE

                if (player.isSneaking) {
                    if (!mainhand.type.isAir && !offhand.type.isAir) {
                        cancel()
                    }

                    e.isCancelled = true

                    val droponcreative: Boolean = if (iscreative) {
                        ColorBlock2.confik.droponcreative
                    } else {
                        true
                    }

                    Uncolor(block, blockname, player, droponcreative)
                } else {
                    val slot: Boolean = if ((mainhand.type.name).contains("DYE")) {
                        true
                    } else if ((offhand.type.name).contains("DYE")) {
                        false
                    } else {
                        cancel()
                        false
                    }

                    val dye: String = if (slot) {
                        mainhand.type.name
                    } else {
                        offhand.type.name
                    }

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

   // }
}