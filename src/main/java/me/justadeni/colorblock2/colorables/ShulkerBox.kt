package me.justadeni.colorblock2.colorables

import me.justadeni.colorblock2.ColorBlock2
import me.justadeni.colorblock2.enums.Dyes
import me.justadeni.colorblock2.misc.Particle
import me.justadeni.colorblock2.misc.Sound
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.block.ShulkerBox
import org.bukkit.entity.Player

class ShulkerBox : Colorable() {
    override val default = "SHULKER_BOX"

    override suspend fun paint(block: Block, dye: String, dropdye: Boolean, player : Player) : Boolean{
        val inventory = (block.state as ShulkerBox).snapshotInventory

        val dyeSimple = dye.replace("_DYE", "")
        val blockName = block.type.name

        val oldDye = Dyes.match(blockName)

        if (oldDye == dyeSimple)
            return false

        if (oldDye != "") {
            val newBlock = blockName.replace(oldDye, dyeSimple)
            val newBlockMat = Material.getMaterial(newBlock)!!
            block.type = newBlockMat

            if (dropdye)
                dropdye(block, oldDye)
        } else {
            val newBlock = dyeSimple + "_" + default
            val newBlockMat = Material.getMaterial(newBlock)!!
            block.type = newBlockMat
        }

        (block.state as ShulkerBox).inventory.contents = inventory.contents
        block.state.update()

        Sound.Sound(ColorBlock2.confik.colorsound, ColorBlock2.confik.colorvolume, player)
        Particle.Particle(ColorBlock2.confik.colorparticle, block)

        return true
    }

    override suspend fun unpaint(block: Block, dropdye: Boolean, player: Player) {
        val inventory = (block.state as ShulkerBox).snapshotInventory

        val blockName = block.type.name
        if (blockName == default)
            return

        val oldDye = Dyes.match(blockName)

        val newBlockMat = Material.getMaterial(default)!!
        block.type = newBlockMat

        if (dropdye)
            dropdye(block, oldDye)

        (block.state as ShulkerBox).inventory.contents = inventory.contents
        block.state.update()

        Sound.Sound(ColorBlock2.confik.uncolorsound, ColorBlock2.confik.uncolorvolume, player)
        Particle.Particle(ColorBlock2.confik.uncolorparticle, block)
    }
}