package me.justadeni.colorblock2.command

import com.github.shynixn.mccoroutine.bukkit.SuspendingTabCompleter
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import me.justadeni.colorblock2.ColorBlock2
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

object TabComplete : SuspendingTabCompleter {
    override suspend fun onTabComplete(sender: CommandSender, command: Command, alias: String, args: Array<out String>): List<String> {
        val list = coroutineScope {
            async {
                val returnList = arrayListOf<String>()
                if (args.size == 1){
                    if (sender.hasPermission(ColorBlock2.confik.adminpermission)){
                        returnList.add("reload")
                    }
                }
                return@async returnList
            }
        }
        return list.await()
    }
}