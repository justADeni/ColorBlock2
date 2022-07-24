package me.justadeni.colorblock2.colorables

import me.justadeni.colorblock2.enums.Dyes
import org.bukkit.Material
import org.bukkit.block.Block

class StainedGlass : Colorable() {
    override val default = "GLASS"

    override fun paint(block: Block, dye: String, dropdye: Boolean) : Boolean{
        val dyeSimple = dye.replace("_DYE", "") //for example "LIGHT_GRAY"
        val blockName = block.type.name //uppercase of full block name

        val oldDye = Dyes.match(blockName) //dye a block used to have

        if (oldDye == dyeSimple)
            return false

        if (oldDye != "") {
            val newBlock = blockName.replace(oldDye, dyeSimple)
            val newBlockMat = Material.getMaterial(newBlock)!!
            block.type = newBlockMat //and here

            if (dropdye)
                dropdye(block, oldDye)
        } else {
            val newBlock = dyeSimple + "_STAINED_" + default
            val newBlockMat = Material.getMaterial(newBlock)!!
            block.type = newBlockMat //and here
        }

        return true
    }
}