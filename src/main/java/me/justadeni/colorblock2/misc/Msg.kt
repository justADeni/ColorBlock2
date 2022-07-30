package me.justadeni.colorblock2.misc

import org.bukkit.ChatColor
import org.bukkit.ChatColor.COLOR_CHAR
import java.util.regex.Matcher
import java.util.regex.Pattern

object Msg {

    fun color(message: String): String {

        if (message.contains("#")) {

            val messages = message.split("#")

            val startTag = "#" + messages[1].take(6)
            val endTag = "#" + messages[2].take(6)

            val newmessage = message.replace(startTag, "").replace(endTag, "")

            val hexPattern: Pattern = Pattern.compile("$startTag([A-Fa-f0-9]{6})$endTag")
            val matcher: Matcher = hexPattern.matcher(newmessage)
            val buffer = StringBuffer(newmessage.length + 4 * 8)
            while (matcher.find()) {
                val group: String = matcher.group(1)
                matcher.appendReplacement(
                    buffer, COLOR_CHAR + "x"
                            + COLOR_CHAR + group[0] + COLOR_CHAR + group[1]
                            + COLOR_CHAR + group[2] + COLOR_CHAR + group[3]
                            + COLOR_CHAR + group[4] + COLOR_CHAR + group[5]
                )
            }
            return matcher.appendTail(buffer).toString()
        } else {
            return ChatColor.translateAlternateColorCodes('&', message)
        }
    }
}