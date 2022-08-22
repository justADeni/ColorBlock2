package me.justadeni.colorblock2.colorables

import me.justadeni.colorblock2.ColorBlock2
import me.justadeni.colorblock2.enums.Dyes
import me.justadeni.colorblock2.misc.Particle
import me.justadeni.colorblock2.misc.Sound
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.block.data.BlockData
import org.bukkit.block.data.type.Candle
import org.bukkit.entity.Player

class Candle : Colorable() {
    override val default = "CANDLE"

    override suspend fun paint(block: Block, dye: String, dropdye: Boolean, player : Player) : Boolean {

        val candle = block.blockData as Candle

        val dyeSimple = dye.replace("_DYE", "") //for example "LIGHT_GRAY"
        val blockName = block.type.name //uppercase of full block name

        val oldDye = Dyes.match(blockName)

        if (oldDye == dyeSimple)
            return false

        val newBlockMat : Material = if (oldDye != "") {
            val newBlock = blockName.replace(oldDye, dyeSimple)

            if (dropdye)
                dropdye(block, oldDye)

            Material.getMaterial(newBlock)!!
        } else {
            val newBlock = dyeSimple + "_" + default

            Material.getMaterial(newBlock)!!
        }

        val newCandle: Candle =
            newBlockMat.createBlockData { blockData: BlockData ->
                (blockData as Candle).candles = candle.candles
                blockData.isLit = candle.isLit
            } as Candle
        block.blockData = newCandle

        Sound.Sound(ColorBlock2.confik.colorsound, ColorBlock2.confik.colorvolume, player)
        Particle.Particle(ColorBlock2.confik.colorparticle, block)

        return true
    }

    override suspend fun unpaint(block: Block, dropdye: Boolean, player: Player) {
        val candle = block.blockData as Candle

        val blockName = block.type.name
        if (blockName == default)
            return

        val oldDye = Dyes.match(blockName)

        val newBlockMat = Material.getMaterial(default)!!
        block.type = newBlockMat

        if (dropdye)
            dropdye(block, oldDye)

        val newCandle: Candle =
            newBlockMat.createBlockData { blockData: BlockData ->
                (blockData as Candle).candles = candle.candles
                blockData.isLit = candle.isLit
            } as Candle
        block.blockData = newCandle

        Sound.Sound(ColorBlock2.confik.uncolorsound, ColorBlock2.confik.uncolorvolume, player)
        Particle.Particle(ColorBlock2.confik.uncolorparticle, block)
    }
}