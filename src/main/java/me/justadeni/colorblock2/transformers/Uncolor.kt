package me.justadeni.colorblock2.transformers

import me.justadeni.colorblock2.colorables.*
import me.justadeni.colorblock2.enums.Blocks
import org.bukkit.block.Block

object Uncolor {

    fun Uncolor(block:Block, blockname:String, dropdye:Boolean){

        when (Blocks.match(blockname)){
            "SHULKER_BOX" -> ShulkerBox().unpaint(block, dropdye)
            "BED" -> Bed().unpaint(block, dropdye)
            "CONCRETE_POWDER" -> ConcretePowder().unpaint(block, dropdye)
            "CONCRETE" -> Concrete().unpaint(block, dropdye)
            "GLAZED_TERRACOTTA" -> GlazedTerracotta().unpaint(block, dropdye)
            "TERRACOTTA" -> Terracotta().unpaint(block, dropdye)
            "CARPET" -> Carpet().unpaint(block, dropdye)
            "STAINED_GLASS_PANE" -> StainedGlassPane().unpaint(block, dropdye)
            "STAINED_GLASS" -> StainedGlass().unpaint(block, dropdye)
            "WOOL" -> Wool().unpaint(block, dropdye)
            "CANDLE" -> Candle().unpaint(block, dropdye)
        }
    }

}