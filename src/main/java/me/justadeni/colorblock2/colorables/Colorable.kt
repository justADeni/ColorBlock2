package me.justadeni.colorblock2.colorables

import me.justadeni.colorblock2.enums.Blocks
import me.justadeni.colorblock2.enums.Dyes
import org.bukkit.Material
import org.bukkit.Material.getMaterial
import org.bukkit.block.Block

abstract class Colorable {
    abstract val default : String
    abstract val suffix : String

    fun paint(block : Block, dye : String){
        val dyeSimple = dye.replace("_DYE", "")
        val blockName = block.type.name



        val newBlock = blockName.replace(Dyes.match(blockName) + "_", dyeSimple + "_" + blockName)
        val newBlockMat = getMaterial(newBlock)!!
        block.setType(newBlockMat)
    }

    fun unpaint(){

    }
}