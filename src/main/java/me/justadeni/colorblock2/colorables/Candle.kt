package me.justadeni.colorblock2.colorables

import me.justadeni.colorblock2.enums.Dyes
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.block.data.BlockData
import org.bukkit.block.data.type.Candle

class Candle : Colorable() {
    override val default = "CANDLE"

    override fun paint(block: Block, dye: String, dropdye: Boolean) : Boolean {

        val candle = block.blockData as Candle

        val dyeSimple = dye.replace("_DYE", "") //for example "LIGHT_GRAY"
        val blockName = block.type.name //uppercase of full block name

        val oldDye = Dyes.match(blockName)

        if (oldDye == dyeSimple)
            return false

        lateinit var newBlockMat : Material

        if (oldDye != "") {
            val newBlock = blockName.replace(oldDye, dyeSimple)
            newBlockMat = Material.getMaterial(newBlock)!!

            if (dropdye)
                dropdye(block, oldDye)
        } else {

            val newBlock = dyeSimple + "_" + default
            newBlockMat = Material.getMaterial(newBlock)!!
        }

        val newCandle: Candle =
            newBlockMat.createBlockData { blockData: BlockData ->
                (blockData as Candle).candles = candle.candles
                (blockData as Candle).isLit = candle.isLit
            } as Candle
        block.blockData = newCandle

        return true
    }

    override fun unpaint(block: Block, dropdye: Boolean) {
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
                (blockData as Candle).isLit = candle.isLit
            } as Candle
        block.blockData = newCandle
    }
}