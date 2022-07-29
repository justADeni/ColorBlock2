package me.justadeni.colorblock2

object Config {

    private val instance = ColorBlock2().getInstance()!!

    var droponcreative = get("DropOnCreative")
    var useoncreative = get("UseOnCreative")

    private fun save(){
        instance.saveConfig()
    }
    private fun reload(){
        instance.reloadConfig()
    }
    private fun get(query : String) : Boolean {
        return instance.config.getBoolean(query)
    }

}