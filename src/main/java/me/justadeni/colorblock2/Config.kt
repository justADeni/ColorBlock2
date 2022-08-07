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
                    printErr("#21DB0BColorSound value " + colorsound + " is not a valid sound. Using NONE#DA0808")
                    colorsound = "NONE"
                }
                colorvolume = getDouble("ColorVolume")

                uncolorsound = getString("UncolorSound")
                if (!existsSound(uncolorsound)){
                    printErr("#21DB0BUncolorSound value " + uncolorsound + " is not a valid sound. Using NONE.#DA0808")
                    uncolorsound = "NONE"
                }
                uncolorvolume = getDouble("UncolorVolume")

                colorparticle = getString("ColorParticle")
                if (!existsParticle(colorparticle)){
                    printErr("#21DB0BColorParticle value " + colorparticle + " is not a valid particle. Using NONE.#DA0808")
                    colorparticle = "NONE"
                }
                uncolorparticle = getString("UncolorParticle")
                if (!existsParticle(uncolorparticle)){
                    printErr("#21DB0BUncolorParticle value " + uncolorparticle + " is not a valid particle. Using NONE.#DA0808")
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
            val sound = Sound.valueOf(query)
            true
        } catch (e : Exception){
            false
        }
    }

    private suspend fun existsParticle(query: String) : Boolean {
        if (query.equals("NONE", ignoreCase = true))
            return true

        return try {
            val sound = Particle.valueOf(query)
            true
        } catch (e : Exception){
            false
        }
    }

    private suspend fun printErr(msg : String) {
        val console = plugin.server.consoleSender

        console.sendMessage("#21DB0BColorBlock Error while loading config#DA0808".color())
        console.sendMessage(msg.color())
    }
}