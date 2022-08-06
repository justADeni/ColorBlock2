package me.justadeni.colorblock2.misc

import me.justadeni.colorblock2.ColorBlock2
import org.bukkit.Sound
import org.bukkit.entity.Player

object Sound {

    fun Sound(type : String, volume : Double, player : Player){

        if (type.equals("NONE", ignoreCase = true))
            return

        val vol : Float = volume.toFloat()
        val sound : Sound = Sound.valueOf(type.uppercase())
        player.playSound(player.location, sound, vol,1.0f)
    }
}