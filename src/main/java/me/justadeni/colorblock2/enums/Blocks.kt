package me.justadeni.colorblock2.enums

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

enum class Blocks {

    SHULKER_BOX,
    BED,
    CONCRETE_POWDER,
    CONCRETE,
    GLAZED_TERRACOTTA,
    TERRACOTTA,
    CARPET,
    GLASS_PANE,
    GLASS,
    WOOL,
    CANDLE;

    companion object {
        suspend fun names() : ArrayList<String>{

            val list = arrayListOf<String>()
            for (name in Blocks.values())
                list.add(name.name)
            return list
        }

        suspend fun match(bloc : String) : String{
            val match = coroutineScope {
                async(Dispatchers.IO) {
                    for (name in names())
                        if (bloc.contains(name))
                            return@async name

                    return@async ""
                }
            }
            return match.await()
        }
    }

}