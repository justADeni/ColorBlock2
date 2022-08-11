package me.justadeni.colorblock2.command

import com.github.shynixn.mccoroutine.bukkit.SuspendingCommandExecutor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import me.justadeni.colorblock2.ColorBlock2
import me.justadeni.colorblock2.misc.Msg.Companion.color
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object Command : SuspendingCommandExecutor{

    override suspend fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean{

        val result = coroutineScope {

            async(Dispatchers.IO) {

                if (command.name != "colorblock")
                    return@async true

                val pluginprefix = ColorBlock2.confik.pluginprefix.color()

                if (sender is Player) {
                    if (!sender.hasPermission(ColorBlock2.confik.adminpermission)) {
                        sender.sendMessage(pluginprefix + ColorBlock2.confik.permissionerror.color())
                        return@async true
                    }
                }
                if (args.isEmpty()) {
                    sender.sendMessage(pluginprefix + ColorBlock2.confik.wrongargserror.color())
                    return@async true
                }
                if (args.size == 1) {
                    if (args[0] != "reload") {
                        sender.sendMessage(pluginprefix + ColorBlock2.confik.wrongargserror.color())
                        return@async true
                    }
                    sender.sendMessage(pluginprefix + ColorBlock2.confik.configreloaded.color())
                    ColorBlock2.confik.reload()
                    ColorBlock2.confik.assign()
                    ColorBlock2.compatibilityManager.init()
                    return@async true
                }
                sender.sendMessage(pluginprefix + ColorBlock2.confik.wrongargserror.color())
                return@async true
            }
        }
        return result.await()
    }

}