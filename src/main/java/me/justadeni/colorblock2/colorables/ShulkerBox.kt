package me.justadeni.colorblock2.colorables

import me.justadeni.colorblock2.enums.Dyes
import org.bukkit.Material
import org.bukkit.block.Block

class ShulkerBox : Colorable() {
    override val default = "SHULKER_BOX"

    override fun paint(block : Block, dye : String, dropdye : Boolean) {
        val inventory = (block as org.bukkit.block.ShulkerBox).snapshotInventory

        val dyeSimple = dye.replace("_DYE", "") //for example "LIGHT_GRAY"
        val blockName = (block as Block).type.name //uppercase of full block name

        val oldDye = Dyes.match(blockName) //dye a block used to have
        val newBlock = blockName.replace(oldDye, dyeSimple)
        val newBlockMat = Material.getMaterial(newBlock)!!
        (block as Block).type = newBlockMat

        if (dropdye)
            dropdye(block, oldDye)

        //TODO: rest of inventory shenanigans
        //henloooo :D u cute, sending kithes <3

}