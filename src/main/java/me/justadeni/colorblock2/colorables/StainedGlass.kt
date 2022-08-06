package me.justadeni.colorblock2.colorables

import me.justadeni.colorblock2.ColorBlock2
import me.justadeni.colorblock2.enums.Dyes
import me.justadeni.colorblock2.misc.Particle
import me.justadeni.colorblock2.misc.Sound
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.entity.Player

class StainedGlass : Colorable() {
    override val default = "GLASS"

    override suspend fun paint(block: Block, dye: String, dropdye: Boolean, player : Player) : Boolean{
        val dyeSimple = dye.replace("_DYE", "") //for example "LIGHT_GRAY"
        val blockName = block.type.name //uppercase of full block name

        val oldDye = Dyes.match(blockName) //dye a block used to have

        if (oldDye == dyeSimple)
            return false

        if (oldDye != "") {
            val newBlock = blockName.replace(oldDye, dyeSimple)
            val newBlockMat = Material.getMaterial(newBlock)!!
            block.type = newBlockMat

            if (dropdye)
                dropdye(block, oldDye)
        } else {
            val newBlock = dyeSimple + "_STAINED_" + default
            val newBlockMat = Material.getMaterial(newBlock)!!
            block.type = newBlockMat
        }

        Sound.Sound(ColorBlock2.confik.colorsound, ColorBlock2.confik.colorvolume, player)
        Particle.Particle(ColorBlock2.confik.colorparticle, block)

        return true
    }
}