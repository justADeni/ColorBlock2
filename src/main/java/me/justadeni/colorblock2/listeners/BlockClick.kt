package me.justadeni.colorblock2.listeners

import me.justadeni.colorblock2.colorables.*
import me.justadeni.colorblock2.enums.Blocks
import me.justadeni.colorblock2.enums.Dyes
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
            uncolor(block, blockname)
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
            color(block, dye, blockname, player, slot)
        }
    }

    private fun color(block : Block, dye : String, blockname : String, player : Player, slot : Boolean){

        val dropDye = true
        var subtract = false

        when (Blocks.match(blockname)){
            "SHULKER_BOX" -> subtract = ShulkerBox().paint(block, dye, dropDye)
            "BED" -> subtract = Bed().paint(block, dye, dropDye)
            "CONCRETE_POWDER" -> subtract = ConcretePowder().paint(block, dye, dropDye)
            "CONCRETE" -> subtract = Concrete().paint(block, dye, dropDye)
            "GLAZED_TERRACOTTA" -> subtract = GlazedTerracotta().paint(block, dye, dropDye)
            "TERRACOTTA" -> subtract = Terracotta().paint(block, dye, dropDye)
            "CARPET" -> subtract = Carpet().paint(block, dye, dropDye)
            "STAINED_GLASS_PANE" -> subtract = StainedGlassPane().paint(block, dye, dropDye)
            "STAINED_GLASS" -> subtract = StainedGlass().paint(block, dye, dropDye)
            "WOOL" -> subtract = Wool().paint(block, dye, dropDye)
            "CANDLE" -> subtract = Candle().paint(block, dye, dropDye)
        }

        if (!subtract)
            return


        if (slot){
            val itemstacc = player.inventory.itemInMainHand
            if (itemstacc.amount > 1){
                player.inventory.setItemInMainHand(ItemStack(itemstacc.type, itemstacc.amount-1))
            } else {
                player.inventory.setItemInMainHand(ItemStack(Material.AIR))
            }
        } else {
            val itemstacc = player.inventory.itemInOffHand
            if (itemstacc.amount > 1){
                player.inventory.setItemInOffHand(ItemStack(itemstacc.type, itemstacc.amount-1))
            } else {
                player.inventory.setItemInOffHand(ItemStack(Material.AIR))
            }
        }
    }

    private fun uncolor(block: Block, blockname: String){

        val dropDye = true

        when (Blocks.match(blockname)){
            "SHULKER_BOX" -> ShulkerBox().unpaint(block, dropDye)
            "BED" -> Bed().unpaint(block, dropDye)
            "CONCRETE_POWDER" -> ConcretePowder().unpaint(block, dropDye)
            "CONCRETE" -> Concrete().unpaint(block, dropDye)
            "GLAZED_TERRACOTTA" -> GlazedTerracotta().unpaint(block, dropDye)
            "TERRACOTTA" -> Terracotta().unpaint(block, dropDye)
            "CARPET" -> Carpet().unpaint(block, dropDye)
            "STAINED_GLASS_PANE" -> StainedGlassPane().unpaint(block, dropDye)
            "STAINED_GLASS" -> StainedGlass().unpaint(block, dropDye)
            "WOOL" -> Wool().unpaint(block, dropDye)
            "CANDLE" -> Candle().unpaint(block, dropDye)
        }
    }
}