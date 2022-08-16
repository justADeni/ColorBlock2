package me.justadeni.colorblock2.compatibility

import me.justadeni.colorblock2.ColorBlock2
import org.bukkit.block.Block
import org.bukkit.entity.Player

abstract class General(val player: Player, val block: Block, val plugin: ColorBlock2){
    abstract fun can() : Boolean
}