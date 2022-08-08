package me.justadeni.colorblock2.colorables

import me.justadeni.colorblock2.ColorBlock2
import me.justadeni.colorblock2.enums.Dyes
import me.justadeni.colorblock2.misc.Particle
import me.justadeni.colorblock2.misc.Sound
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.block.data.BlockData
import org.bukkit.block.data.type.Bed
import org.bukkit.entity.Player

class Bed : Colorable() {
    override val default = "WHITE_BED"

    override suspend fun paint(block : Block, dye : String, dropdye : Boolean, player : Player) : Boolean{
        val dyeSimple = dye.replace("_DYE", "") //for example "LIGHT_GRAY"
        val blockName = block.type.name //uppercase of full block name

        val bed = block.blockData as Bed

        val oldDye = Dyes.match(blockName) //dye a block used to have

        if (oldDye == dyeSimple)
            return false

        val newBlock = blockName.replace(oldDye, dyeSimple)
        val newBlockMat = Material.getMaterial(newBlock)!!

        if (dropdye)
            dropdye(block, oldDye)

        val newBed: Bed =
            newBlockMat.createBlockData { blockData: BlockData ->
                (blockData as Bed).part = bed.part
                (blockData as Bed).facing = bed.facing
            } as Bed
        block.blockData = newBed

        Sound.Sound(ColorBlock2.confik.colorsound, ColorBlock2.confik.colorvolume, player)
        Particle.Particle(ColorBlock2.confik.colorparticle, block)

        return true
    }

    override suspend fun unpaint(block: Block, dropdye : Boolean, player : Player){
        val bed = block.blockData as Bed

        val blockName = block.type.name

        if (blockName == default)
            return

        val oldDye = Dyes.match(blockName)

        val newBlockMat = Material.getMaterial(default)!!

        if (dropdye)
            dropdye(block, oldDye)

        val newBed: Bed =
            newBlockMat.createBlockData { blockData: BlockData ->
                (blockData as Bed).part = bed.part
                (blockData as Bed).facing = bed.facing
            } as Bed
        block.blockData = newBed

        Sound.Sound(ColorBlock2.confik.uncolorsound, ColorBlock2.confik.uncolorvolume, player)
        Particle.Particle(ColorBlock2.confik.uncolorparticle, block)
    }
}