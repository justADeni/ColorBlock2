package me.justadeni.colorblock2.transformers

import me.justadeni.colorblock2.ColorBlock2
import me.justadeni.colorblock2.colorables.*
import me.justadeni.colorblock2.enums.Blocks
import me.justadeni.colorblock2.misc.Particle
import me.justadeni.colorblock2.misc.Sound
import org.bukkit.block.Block
import org.bukkit.entity.Player

object Uncolor {

    suspend fun Uncolor(block:Block, blockname:String, player : Player, dropdye:Boolean){

        when (Blocks.match(blockname)){
            "SHULKER_BOX" -> ShulkerBox().unpaint(block, dropdye, player)
            "BED" -> Bed().unpaint(block, dropdye, player)
            "CONCRETE_POWDER" -> ConcretePowder().unpaint(block, dropdye, player)
            "CONCRETE" -> Concrete().unpaint(block, dropdye, player)
            "GLAZED_TERRACOTTA" -> GlazedTerracotta().unpaint(block, dropdye, player)
            "TERRACOTTA" -> Terracotta().unpaint(block, dropdye, player)
            "CARPET" -> Carpet().unpaint(block, dropdye, player)
            "STAINED_GLASS_PANE" -> StainedGlassPane().unpaint(block, dropdye, player)
            "STAINED_GLASS" -> StainedGlass().unpaint(block, dropdye, player)
            "WOOL" -> Wool().unpaint(block, dropdye, player)
            "CANDLE" -> Candle().unpaint(block, dropdye, player)
        }
    }

}