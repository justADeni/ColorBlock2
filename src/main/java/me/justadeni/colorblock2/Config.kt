package me.justadeni.colorblock2

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.bukkit.Particle
import org.bukkit.Sound
import java.lang.Exception

class Config(private val plugin : ColorBlock2) {

    var worldguardhook : Boolean = false
    var landshook : Boolean = false

    var droponcreative : Boolean = false
    var droponsurvival : Boolean = true
    var useoncreative  : Boolean = false
    var useonsurvival  : Boolean = true

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

    var particlechance : Int = 50

    fun save(){
        plugin.saveConfig()
    }
    suspend fun reload(){
        plugin.reloadConfig()
    }
    suspend fun assign(){
        coroutineScope {
            async(Dispatchers.IO) {
                worldguardhook = getBool("WorldGuardHook")
                landshook = getBool("LandsHook")

                droponcreative = getBool("DropOnCreative")
                droponsurvival = getBool("DropOnSurvival")
                useoncreative = getBool("UseOnCreative")
                useonsurvival = getBool("UseOnSurvival")

                dyepermission = getString("DyePermission")
                undyepermission = getString("UndyePermission")
                adminpermission = getString("AdminPermission")

                configreloaded = getString("ConfigReloaded")
                permissionerror = getString("PermissionError")
                wrongargserror = getString("WrongArgsError")
                pluginprefix = getString("PluginPrefix")

                colorsound = getString("ColorSound")
                if (!existsSound(colorsound)){
                    ColorBlock2.msg.printError("ColorSound value $colorsound is not a valid sound. Using NONE.")
                    colorsound = "NONE"
                }
                colorvolume = getDouble("ColorVolume")

                uncolorsound = getString("UncolorSound")
                if (!existsSound(uncolorsound)){
                    ColorBlock2.msg.printError("UncolorSound value $uncolorsound is not a valid sound. Using NONE.")
                    uncolorsound = "NONE"
                }
                uncolorvolume = getDouble("UncolorVolume")

                colorparticle = getString("ColorParticle")
                if (!existsParticle(colorparticle)){
                    ColorBlock2.msg.printError("ColorParticle value $colorparticle is not a valid particle. Using NONE.")
                    colorparticle = "NONE"
                }
                uncolorparticle = getString("UncolorParticle")
                if (!existsParticle(uncolorparticle)){
                    ColorBlock2.msg.printError("UncolorParticle value $uncolorparticle is not a valid particle. Using NONE.")
                    uncolorparticle = "NONE"
                }
                particlechance = getInt("ParticleChance")
            }
        }
    }
    private suspend fun getBool(query : String) : Boolean {
        return plugin.config.getBoolean(query)
    }
    private suspend fun getDouble(query: String) : Double {
        return plugin.config.getDouble(query)
    }
    private suspend fun getInt(query: String) : Int {
        return plugin.config.getInt(query)
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
}