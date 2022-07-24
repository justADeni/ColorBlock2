package me.justadeni.colorblock2.enums

enum class Dyes {

    WHITE,
    LIGHT_GRAY,
    LIGHT_BLUE,
    GRAY,
    BLACK,
    BROWN,
    RED,
    ORANGE,
    YELLOW,
    LIME,
    GREEN,
    CYAN,
    BLUE,
    PURPLE,
    MAGENTA,
    PINK;

    companion object {
        fun names() : ArrayList<String>{
            var list = arrayListOf<String>()
            for (name in Dyes.values())
                list.add(name.name)
            return list
        }

        fun match(bloc : String) : String{
            for (name in names())
                if (bloc.contains(name))
                    return name
            return ""
        }
    }

}