package me.justadeni.colorblock2.transformers

import me.justadeni.colorblock2.colorables.*
import me.justadeni.colorblock2.enums.Blocks
import org.bukkit.block.Block

object Uncolor {

    fun Uncolor(block: Block, blockname: String){

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