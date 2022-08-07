package me.justadeni.colorblock2.transformers

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonCancellable.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import me.justadeni.colorblock2.ColorBlock2
import me.justadeni.colorblock2.colorables.*
import me.justadeni.colorblock2.enums.Blocks
import me.justadeni.colorblock2.misc.Particle
import me.justadeni.colorblock2.misc.Sound
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

object Color {

    suspend fun Color(block:Block, dye:String, blockname:String, player:Player,
              slot:Boolean, itemsubtract: Boolean, dropdye: Boolean){

        var subtract = itemsubtract

        coroutineScope {

            launch {

                when (Blocks.match(blockname)) {
                    "SHULKER_BOX" -> subtract = ShulkerBox().paint(block, dye, dropdye, player)
                    "BED" -> subtract = Bed().paint(block, dye, dropdye, player)
                    "CONCRETE_POWDER" -> subtract = ConcretePowder().paint(block, dye, dropdye, player)
                    "CONCRETE" -> subtract = Concrete().paint(block, dye, dropdye, player)
                    "GLAZED_TERRACOTTA" -> subtract = GlazedTerracotta().paint(block, dye, dropdye, player)
                    "TERRACOTTA" -> subtract = Terracotta().paint(block, dye, dropdye, player)
                    "CARPET" -> subtract = Carpet().paint(block, dye, dropdye, player)
                    "STAINED_GLASS_PANE" -> subtract = StainedGlassPane().paint(block, dye, dropdye, player)
                    "STAINED_GLASS" -> subtract = StainedGlass().paint(block, dye, dropdye, player)
                    "WOOL" -> subtract = Wool().paint(block, dye, dropdye, player)
                    "CANDLE" -> subtract = Candle().paint(block, dye, dropdye, player)
                }
            }

            launch {
                if (!itemsubtract)
                    return@launch

                if (!subtract)
                    return@launch

                if (slot) {
                    val itemstacc = player.inventory.itemInMainHand
                    if (itemstacc.amount > 1) {
                        player.inventory.setItemInMainHand(ItemStack(itemstacc.type, itemstacc.amount - 1))
                    } else {
                        player.inventory.setItemInMainHand(ItemStack(Material.AIR))
                    }
                } else {
                    val itemstacc = player.inventory.itemInOffHand
                    if (itemstacc.amount > 1) {
                        player.inventory.setItemInOffHand(ItemStack(itemstacc.type, itemstacc.amount - 1))
                    } else {
                        player.inventory.setItemInOffHand(ItemStack(Material.AIR))
                    }
                }
            }
        }
    }

}