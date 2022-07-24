package me.justadeni.colorblock2.enums

enum class Blocks {

    SHULKER_BOX,
    BED,
    CONCRETE_POWDER,
    CONCRETE,
    GLAZED_TERRACOTTA,
    TERRACOTTA,
    CARPET,
    STAINED_GLASS_PANE,
    STAINED_GLASS,
    WOOL,
    CANDLE;

    companion object {
        fun names() : ArrayList<String>{
            var list = arrayListOf<String>()
            for (name in Blocks.values())
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