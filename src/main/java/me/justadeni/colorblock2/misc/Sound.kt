package me.justadeni.colorblock2.misc

import me.justadeni.colorblock2.ColorBlock2
import org.bukkit.Sound
import org.bukkit.entity.Player

object Sound {

    fun ColorSound(player : Player){

        if (ColorBlock2.confik.colorsound == "NONE")
            return

        val volume : Float = ColorBlock2.confik.colorvolume.toFloat()
        val sound : Sound = Sound.valueOf("Sound." + ColorBlock2.confik.colorsound)
        player.playSound(player.location, sound, volume,1.0f)
    }

    fun UncolorSound(player: Player){

        if (ColorBlock2.confik.colorsound == "NONE")
            return

        val volume : Float = ColorBlock2.confik.uncolorvolume.toFloat()
        val sound : Sound = Sound.valueOf("Sound." + ColorBlock2.confik.uncolorsound)
        player.playSound(player.location, sound, volume,1.0f)
    }
}