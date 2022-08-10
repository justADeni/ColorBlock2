package me.justadeni.colorblock2.colorables

import com.github.shynixn.mccoroutine.bukkit.ticks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import me.justadeni.colorblock2.ColorBlock2
import me.justadeni.colorblock2.enums.Dyes
import me.justadeni.colorblock2.misc.Particle
import me.justadeni.colorblock2.misc.Sound
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.block.BlockFace
import org.bukkit.block.data.BlockData
import org.bukkit.block.data.type.Bed
import org.bukkit.entity.Player
import java.util.UUID


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

        val headbedblock = getHeadBlock(block)
        val headbed = headbedblock.blockData as Bed
        setBed(headbedblock, headbed.facing, newBlockMat)

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

        val headbedblock = getHeadBlock(block)
        val headbed = headbedblock.blockData as Bed
        setBed(headbedblock, headbed.facing, newBlockMat)

        Sound.Sound(ColorBlock2.confik.uncolorsound, ColorBlock2.confik.uncolorvolume, player)
        Particle.Particle(ColorBlock2.confik.uncolorparticle, block)

    }


    private suspend fun getHeadBlock(block : Block) : Block{
        val bedData = block.blockData as Bed
        if (bedData.part == Bed.Part.HEAD)
            return block

        var x = 0
        var z = 0

        if (bedData.facing == BlockFace.EAST)
            x += 1
        else if (bedData.facing == BlockFace.WEST)
            x -= 1
        else if (bedData.facing == BlockFace.SOUTH)
            z += 1
        else if (bedData.facing == BlockFace.NORTH)
            z -= 1

        val loc = Location(block.world, (block.x+x).toDouble(), block.y.toDouble(), (block.z+z).toDouble())
        return loc.block

        //return block.getRelative(bedData.facing.oppositeFace)
    }

    private suspend fun setBed(startBlock: Block, facing: BlockFace, material: Material?){

        startBlock.setBlockData(material!!.createBlockData { data ->
            (data as Bed).setPart(
                Bed.Part.HEAD
            )
            (data as Bed).facing = facing
        }, false)

        val footBlock = startBlock.getRelative(facing.oppositeFace)

        footBlock.setBlockData(material!!.createBlockData { data ->
            (data as Bed).setPart(
                Bed.Part.FOOT
            )
            (data as Bed).facing = facing
        }, false)
    }
}