package me.justadeni.colorblock2

class Config(private val plugin : ColorBlock2) {

    var droponcreative : Boolean = false
    var useoncreative  : Boolean = false
    lateinit var usepermission   : String
    lateinit var adminpermission : String
    lateinit var configreloaded  : String
    lateinit var permissionerror : String
    lateinit var wrongargserror  : String
    lateinit var pluginprefix : String

    fun save(){
        plugin.saveConfig()
    }
    fun reload(){
        plugin.reloadConfig()
    }

    fun assign(){
        droponcreative = getBool("DropOnCreative")
        useoncreative = getBool("UseOnCreative")
        usepermission = getString("UsePermission")
        adminpermission = getString("AdminPermission")
        configreloaded = getString("ConfigReloaded")
        permissionerror = getString("PermissionError")
        wrongargserror = getString("WrongArgsError")
        pluginprefix = getString("PluginPrefix")
    }
    private fun getBool(query : String) : Boolean {
        return plugin.config.getBoolean(query)
    }
    private fun getString(query: String) : String {
        return plugin.config.getString(query)!!
    }

}