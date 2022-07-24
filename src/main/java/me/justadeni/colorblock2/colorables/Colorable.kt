package me.justadeni.colorblock2.colorables

import me.justadeni.colorblock2.enums.Blocks
import me.justadeni.colorblock2.enums.Dyes
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.Material.getMaterial
import org.bukkit.block.Block
import org.bukkit.inventory.ItemStack

abstract class Colorable {
    abstract val default : String

    open fun paint(block : Block, dye : String, dropdye : Boolean) : Boolean{
        val dyeSimple = dye.replace("_DYE", "") //for example "LIGHT_GRAY"
        val blockName = block.type.name //uppercase of full block name

        val oldDye = Dyes.match(blockName) //dye a block used to have

        if (oldDye == dyeSimple)
            return false

        val newBlock = blockName.replace(oldDye, dyeSimple)
        val newBlockMat = getMaterial(newBlock)!!
        block.type = newBlockMat

        if (dropdye)
            dropdye(block, oldDye)

        return true
    }

    open fun unpaint(block: Block, dropdye : Boolean){
        val blockName = block.type.name //uppercase of full block name

        if (blockName == default)
            return

        val oldDye = Dyes.match(blockName)

        val newBlockMat = getMaterial(default)!!
        block.type = newBlockMat

        if (dropdye)
            dropdye(block, oldDye)
    }

    fun dropdye(block: Block, dye: String){
        val newDye = dye + "_DYE"
        val newDyeMat = getMaterial(newDye)!!
        val loc = block.location
        val newLoc = Location(block.world, loc.x+0.5, loc.y+0.5, loc.z+0.5)

        block.world.dropItemNaturally(newLoc, ItemStack(newDyeMat))
    }
}