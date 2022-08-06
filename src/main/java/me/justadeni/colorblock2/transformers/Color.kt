package me.justadeni.colorblock2.transformers

import kotlinx.coroutines.NonCancellable.cancel
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

        var sound = true

        when (Blocks.match(blockname)){
            "SHULKER_BOX" -> subtract = ShulkerBox().paint(block, dye, dropdye)
            "BED" -> subtract = Bed().paint(block, dye, dropdye)
            "CONCRETE_POWDER" -> subtract = ConcretePowder().paint(block, dye, dropdye)
            "CONCRETE" -> subtract = Concrete().paint(block, dye, dropdye)
            "GLAZED_TERRACOTTA" -> subtract = GlazedTerracotta().paint(block, dye, dropdye)
            "TERRACOTTA" -> subtract = Terracotta().paint(block, dye, dropdye)
            "CARPET" -> subtract = Carpet().paint(block, dye, dropdye)
            "STAINED_GLASS_PANE" -> subtract = StainedGlassPane().paint(block, dye, dropdye)
            "STAINED_GLASS" -> subtract = StainedGlass().paint(block, dye, dropdye)
            "WOOL" -> subtract = Wool().paint(block, dye, dropdye)
            "CANDLE" -> subtract = Candle().paint(block, dye, dropdye)
            else -> sound = false
        }

        if (sound){
            Sound.Sound(ColorBlock2.confik.colorparticle, ColorBlock2.confik.colorvolume, player)
            Particle.Particle(ColorBlock2.confik.colorparticle, block)
        }

        if (!itemsubtract)
            return

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