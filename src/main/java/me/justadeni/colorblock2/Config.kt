package me.justadeni.colorblock2

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import me.justadeni.colorblock2.misc.Msg
import org.bukkit.Particle
import org.bukkit.Sound
import java.lang.NullPointerException
import java.util.*

class Config(private val plugin : ColorBlock2) {

    var worldguardhook : Boolean = false
    lateinit var worldguarddisallowmessage : String
    lateinit var worldguardallowmessage : String
    var landshook : Boolean = false
    lateinit var landsdisallowmessage : String
    lateinit var landsallowmessage : String

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
                worldguarddisallowmessage = getString("WorldGuardDisallowMessage")
                worldguardallowmessage = getString("WorldGuardAllowMessage")
                landshook = getBool("LandsHook")
                landsdisallowmessage = getString("LandsDisallowMessage")
                landsallowmessage = getString("LandsAllowMessage")

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

                colorsound = getString("ColorSound").checkSound()
                colorvolume = getDouble("ColorVolume")

                uncolorsound = getString("UncolorSound").checkSound()
                uncolorvolume = getDouble("UncolorVolume")

                colorparticle = getString("ColorParticle").checkParticle()
                uncolorparticle = getString("UncolorParticle").checkParticle()

                particlechance = getInt("ParticleChance")
            }
        }
    }
    private suspend fun getBool(query : String) : Boolean {
        return try {
            plugin.config.getBoolean(query)
        } catch (e : NullPointerException){
            ColorBlock2.msg.printError(query.replaceFirstChar { query[0].uppercase() } + "value not found. Using false.")
            plugin.config.set(query, false)
            false
        }
    }
    private suspend fun getDouble(query: String) : Double {
        return try {
            plugin.config.getDouble(query)
        } catch (e : NullPointerException){
            ColorBlock2.msg.printError(query.replaceFirstChar { query[0].uppercase() } + "value not found. Using 5.0.")
            plugin.config.set(query, 5.0)
            5.0
        }
    }
    private suspend fun getInt(query: String) : Int {

        return try {
            plugin.config.getInt(query)
        } catch (e : NullPointerException){
            ColorBlock2.msg.printError(query.replaceFirstChar { query[0].uppercase() } + "value not found. Using 5.")
            plugin.config.set(query, 5)
            5
        }
    }
    private suspend fun getString(query: String) : String {
        return try {
            plugin.config.getString(query)!!
        } catch (e : NullPointerException){
            ColorBlock2.msg.printError(query.replaceFirstChar { query[0].uppercase() } + "value not found. Using NONE.")
            plugin.config.set(query, "NONE")
            "NONE"
        }
    }

    private suspend fun String.checkSound() : String {
        if (this.equals("NONE", ignoreCase = true))
            return this

        return try {
            Sound.valueOf(this)
            this
        } catch (e : EnumConstantNotPresentException){
            ColorBlock2.msg.printError("UncolorSound value $this is not a valid sound. Using NONE.")
            "NONE"
        }
    }

    private suspend fun String.checkParticle() : String {
        if (this.equals("NONE", ignoreCase = true))
            return this

        return try {
            Particle.valueOf(this)
            this
        } catch (e : EnumConstantNotPresentException){
            ColorBlock2.msg.printError("ColorParticle value $this is not a valid particle. Using NONE.")
            "NONE"
        }
    }
}