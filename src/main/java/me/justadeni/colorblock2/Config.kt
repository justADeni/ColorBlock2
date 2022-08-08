package me.justadeni.colorblock2

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import me.justadeni.colorblock2.misc.Msg.color
import org.bukkit.Particle
import org.bukkit.Sound
import java.lang.Exception

class Config(private val plugin : ColorBlock2) {

    var droponcreative : Boolean = false
    var useoncreative  : Boolean = false

    lateinit var dyepermission   : String
    lateinit var undyepermission : String
    lateinit var adminpermission : String

    lateinit var configreloaded  : String
    lateinit var permissionerror : String
    lateinit var wrongargserror  : String
    lateinit var pluginprefix : String

    lateinit var colorsound : String
    var colorvolume : Double = 5.0

    lateinit var uncolorsound : String
    var uncolorvolume : Double = 5.0

    lateinit var colorparticle : String
    lateinit var uncolorparticle : String

    fun save(){
        plugin.saveConfig()
    }
    suspend fun reload(){
        plugin.reloadConfig()
    }
    suspend fun assign(){
        coroutineScope {
            async(Dispatchers.IO) {
                droponcreative = getBool("DropOnCreative")
                useoncreative = getBool("UseOnCreative")

                dyepermission = getString("DyePermission")
                undyepermission = getString("UndyePermission")
                adminpermission = getString("AdminPermission")

                configreloaded = getString("ConfigReloaded")
                permissionerror = getString("PermissionError")
                wrongargserror = getString("WrongArgsError")
                pluginprefix = getString("PluginPrefix")

                colorsound = getString("ColorSound")
                if (!existsSound(colorsound)){
                    printErr("ColorSound value $colorsound is not a valid sound. Using NONE.")
                    colorsound = "NONE"
                }
                colorvolume = getDouble("ColorVolume")

                uncolorsound = getString("UncolorSound")
                if (!existsSound(uncolorsound)){
                    printErr("UncolorSound value $uncolorsound is not a valid sound. Using NONE.")
                    uncolorsound = "NONE"
                }
                uncolorvolume = getDouble("UncolorVolume")

                colorparticle = getString("ColorParticle")
                if (!existsParticle(colorparticle)){
                    printErr("ColorParticle value $colorparticle is not a valid particle. Using NONE.")
                    colorparticle = "NONE"
                }
                uncolorparticle = getString("UncolorParticle")
                if (!existsParticle(uncolorparticle)){
                    printErr("UncolorParticle value $uncolorparticle is not a valid particle. Using NONE.")
                    uncolorparticle = "NONE"
                }
            }
        }
    }
    private suspend fun getBool(query : String) : Boolean {
        return plugin.config.getBoolean(query)
    }
    private suspend fun getDouble(query: String) : Double {
        return plugin.config.getDouble(query)
    }
    private suspend fun getString(query: String) : String {
        return plugin.config.getString(query)!!
    }

    private suspend fun existsSound(query: String) : Boolean {
        if (query.equals("NONE", ignoreCase = true))
            return true

        return try {
            Sound.valueOf(query)
            true
        } catch (e : Exception){
            false
        }
    }

    private suspend fun existsParticle(query: String) : Boolean {
        if (query.equals("NONE", ignoreCase = true))
            return true

        return try {
            Particle.valueOf(query)
            true
        } catch (e : Exception){
            false
        }
    }

    private suspend fun printErr(msg : String) {
        val console = plugin.server.consoleSender

        val color1 = "#AB4C37"
        val color2 = "#FF0101"

        console.sendMessage((color1 + "ColorBlock Error while loading config" + color2).color())
        console.sendMessage((color1 + msg + color2).color())
    }
}