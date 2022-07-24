package me.justadeni.colorblock2.transformers

import me.justadeni.colorblock2.colorables.*
import me.justadeni.colorblock2.enums.Blocks
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

object Color {

    fun Color(block : Block, dye : String, blockname : String, player : Player, slot : Boolean){

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

}