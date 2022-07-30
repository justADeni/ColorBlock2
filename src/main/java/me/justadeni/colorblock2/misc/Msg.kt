package me.justadeni.colorblock2.misc

import net.md_5.bungee.api.ChatColor

object Msg {

    fun color(message: String): String {

        if (message.contains("#")) {

            val messages = message.split("#")
            val startTag = messages[1].take(6)
            val endTag = messages[2].take(6)
            val newmessage = message.replace("#$startTag", "").replace("#$endTag", "")

            val r1 = hexToRgb(startTag.substring(0, 2)) //171
            val g1 = hexToRgb(startTag.substring(2, 4)) //76
            val b1 = hexToRgb(startTag.substring(4, 6)) //55

            val r2 = hexToRgb(endTag.substring(0, 2)) //255
            val g2 = hexToRgb(endTag.substring(2, 4)) //1
            val b2 = hexToRgb(endTag.substring(4, 6)) //1

            var returnMessage = ""
            val lenght = newmessage.length.toDouble() //31

            var currentR = r1.toDouble() //171
            currentR = checkBounds(currentR)
            var currentG = g1.toDouble() //76
            currentG = checkBounds(currentG)
            var currentB = b1.toDouble() //55
            currentB = checkBounds(currentB)

            val incrementR = ((r1-r2)/lenght).toDouble() //-2.7
            val incrementG = ((g1-g2)/lenght).toDouble() //2.4
            val incrementB = ((b1-b2)/lenght).toDouble() //1.7

            for (i in 0 until lenght.toInt()){
                returnMessage += ChatColor.of(java.awt.Color(currentR.toInt(), currentG.toInt(), currentB.toInt()))
                returnMessage += newmessage[i]

                currentR -= incrementR //255
                currentR = checkBounds(currentR)

                currentG -= incrementG //1
                currentG = checkBounds(currentG)

                currentB -= incrementB //1
                currentB = checkBounds(currentB)
            }

            return returnMessage

        } else {
            return ChatColor.translateAlternateColorCodes('&', message)
        }
    }

    private fun hexToRgb(hex : String) : Int {
        return getNum(hex[0])*16 + getNum(hex[1])
    }

    private fun getNum(num: Char) : Int {
        return when (num){
            'A' -> 10
            'B' -> 11
            'C' -> 12
            'D' -> 13
            'E' -> 14
            'F' -> 15
            else -> num.code
        }
    }

    private fun checkBounds(num : Double) : Double {
        return if (num > 254)
            254.0
        else if (num < 1)
            1.0
        else
            num
    }
}