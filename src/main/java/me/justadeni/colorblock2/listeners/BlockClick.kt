package me.justadeni.colorblock2.listeners

import me.justadeni.colorblock2.colorables.*
import me.justadeni.colorblock2.enums.Blocks
import me.justadeni.colorblock2.enums.Dyes
import me.justadeni.colorblock2.transformers.Color
import me.justadeni.colorblock2.transformers.Color.Color
import me.justadeni.colorblock2.transformers.Uncolor
import me.justadeni.colorblock2.transformers.Uncolor.Uncolor
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack

object BlockClick : Listener{

    @EventHandler
    fun onBlockClick(e : PlayerInteractEvent){
        if (e.action != Action.RIGHT_CLICK_BLOCK)
            return

        val block = e.clickedBlock!!
        val blockname = block.type.name

        if (Blocks.match(blockname) == "")
            return

        val player = e.player

        val mainhand = player.inventory.itemInMainHand
        val offhand = player.inventory.itemInOffHand
        if (player.isSneaking){
            val slot: Boolean = if (mainhand.type.isAir){
                true
            } else if (offhand.type.isAir){
                false
            } else {
                return
            }
            Uncolor(block, blockname)
        } else {
            val slot : Boolean = if (Dyes.match(mainhand.type.name) != ""){
                true
            } else if (Dyes.match(offhand.type.name) != ""){
                false
            } else {
                return
            }

            var dye : String
            if (slot){
                dye = mainhand.type.name
            } else {
                dye = offhand.type.name
            }
            Color(block, dye, blockname, player, slot)

        }
    }
}