package me.justadeni.colorblock2.colorables

import me.justadeni.colorblock2.ColorBlock2
import me.justadeni.colorblock2.enums.Blocks
import me.justadeni.colorblock2.enums.Dyes
import me.justadeni.colorblock2.misc.Particle
import me.justadeni.colorblock2.misc.Sound
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.Material.getMaterial
import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

abstract class Colorable {
    abstract val default : String

    open suspend fun paint(block : Block, dye : String, dropdye : Boolean, player : Player) : Boolean{
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

        Sound.Sound(ColorBlock2.confik.colorsound, ColorBlock2.confik.colorvolume, player)
        Particle.Particle(ColorBlock2.confik.colorparticle, block)

        return true
    }

    open suspend fun unpaint(block: Block, dropdye : Boolean, player : Player){
        val blockName = block.type.name

        if (blockName == default)
            return

        val oldDye = Dyes.match(blockName)

        val newBlockMat = getMaterial(default)!!
        block.type = newBlockMat

        if (dropdye)
            dropdye(block, oldDye)

        Sound.Sound(ColorBlock2.confik.uncolorsound, ColorBlock2.confik.uncolorvolume, player)
        Particle.Particle(ColorBlock2.confik.uncolorparticle, block)
    }

    fun dropdye(block: Block, dye: String){
        val newDye = dye + "_DYE"
        val newDyeMat = getMaterial(newDye)!!
        val loc = block.location
        val newLoc = Location(block.world, loc.x+0.5, loc.y+0.5, loc.z+0.5)

        block.world.dropItemNaturally(newLoc, ItemStack(newDyeMat))
    }
}