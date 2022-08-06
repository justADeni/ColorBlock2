package me.justadeni.colorblock2.enums

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

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
        suspend fun names() : ArrayList<String>{
            var list = arrayListOf<String>()
            for (name in Dyes.values())
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