package me.justadeni.colorblock2.colorables

import me.justadeni.colorblock2.enums.Dyes
import org.bukkit.Material
import org.bukkit.block.Block

class ShulkerBox : Colorable() {
    override val default = "SHULKER_BOX"

    override fun paint(block: Block, dye: String, dropdye: Boolean) : Boolean{
        //this abomination had to be done
        //because Kotlin "sMaRt cAsTs"
        val inventory = (block as org.bukkit.block.ShulkerBox).snapshotInventory //here

        val dyeSimple = dye.replace("_DYE", "") //for example "LIGHT_GRAY"
        val blockName = (block as Block).type.name //uppercase of full block name

        val oldDye = Dyes.match(blockName) //dye a block used to have

        if (oldDye == dyeSimple)
            return false

        if (oldDye != "") {
            val newBlock = blockName.replace(oldDye, dyeSimple)
            val newBlockMat = Material.getMaterial(newBlock)!!
            (block as Block).type = newBlockMat //and here

            if (dropdye)
                dropdye(block, oldDye)
        } else {
            val newBlock = dyeSimple + "_" + default
            val newBlockMat = Material.getMaterial(newBlock)!!
            (block as Block).type = newBlockMat //and here
        }

        block.inventory.contents = inventory.contents
        block.state.update()

        return true
    }

    override fun unpaint(block: Block, dropdye: Boolean) {
        val inventory = (block as org.bukkit.block.ShulkerBox).snapshotInventory

        val blockName = (block as Block).type.name //uppercase of full block name
        if (blockName == default)
            return

        val oldDye = Dyes.match(blockName)

        val newBlockMat = Material.getMaterial(default)!!
        (block as Block).type = newBlockMat

        if (dropdye)
            dropdye(block, oldDye)

        block.inventory.contents = inventory.contents
        block.state.update()
    }
}