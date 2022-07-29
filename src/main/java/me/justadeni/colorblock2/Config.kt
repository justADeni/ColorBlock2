package me.justadeni.colorblock2

import me.justadeni.colorblock2.misc.Msg

object Config {

    private val instance = ColorBlock2().getInstance()!!

    var droponcreative : Boolean = false
    var useoncreative  : Boolean = false
    lateinit var usepermission   : String
    lateinit var adminpermission : String
    lateinit var configreloaded  : String
    lateinit var permissionerror : String
    lateinit var wrongargserror  : String

    fun save(){
        instance.saveConfig()
    }
    fun reload(){
        instance.reloadConfig()
        assign()
    }

    private fun assign(){
        droponcreative = getBool("DropOnCreative")
        useoncreative = getBool("UseOnCreative")
        usepermission = getString("UsePermission")
        adminpermission = getString("AdminPermission")
        configreloaded = getString("ConfigReloaded")
        permissionerror = getString("PermissionError")
        wrongargserror = getString("WrongArgsError")
    }
    private fun getBool(query : String) : Boolean {
        return instance.config.getBoolean(query)
    }
    private fun getString(query: String) : String {
        return Msg.colorMsg(instance.config.getString(query)!!)
    }

}