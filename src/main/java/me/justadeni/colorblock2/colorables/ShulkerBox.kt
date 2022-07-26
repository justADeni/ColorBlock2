package me.justadeni.colorblock2.colorables

import me.justadeni.colorblock2.enums.Dyes
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.block.ShulkerBox

class ShulkerBox : Colorable() {
    override val default = "SHULKER_BOX"

    override fun paint(block: Block, dye: String, dropdye: Boolean) : Boolean{
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

        return true
    }

    override fun unpaint(block: Block, dropdye: Boolean) {
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
    }
}