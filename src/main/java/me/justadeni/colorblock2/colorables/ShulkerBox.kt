package me.justadeni.colorblock2.colorables

import me.justadeni.colorblock2.enums.Dyes
import org.bukkit.Material
import org.bukkit.block.Block

class ShulkerBox : Colorable() {
    override val default = "SHULKER_BOX"

    override fun paint(block: Block, dye: String, dropdye: Boolean) {
        //this abomination had to be done
        //because Kotlin "sMaRt cAsTs"
        val inventory = (block as org.bukkit.block.ShulkerBox).snapshotInventory //here

        val dyeSimple = dye.replace("_DYE", "") //for example "LIGHT_GRAY"
        val blockName = (block as Block).type.name //uppercase of full block name
        val oldDye = Dyes.match(blockName) //dye a block used to haveval oldDye = Dyes.match(blockName) //dye a block used to have

        if (Dyes.match(blockName) != "") {
            val newBlock = blockName.replace(oldDye, dyeSimple)
            val newBlockMat = Material.getMaterial(newBlock)!!
            (block as Block).type = newBlockMat //and here
        } else {
            val newBlock = dyeSimple + "_" + default
            val newBlockMat = Material.getMaterial(newBlock)!!
            (block as Block).type = newBlockMat //and here
        }

        if (dropdye)
            dropdye(block, oldDye)

        block.inventory.contents = inventory.contents
        block.state.update()
    }
}