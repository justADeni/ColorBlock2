package me.justadeni.colorblock2

object Config {

    private val instance = ColorBlock2().getInstance()!!

    var droponcreative : Boolean = false
    var useoncreative : Boolean = false
    lateinit var usepermission : String
    lateinit var adminpermission : String

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
    }
    private fun getBool(query : String) : Boolean {
        return instance.config.getBoolean(query)
    }
    private fun getString(query: String) : String {
        return instance.config.getString(query)!!
    }

}