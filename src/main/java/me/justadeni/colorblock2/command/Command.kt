package me.justadeni.colorblock2.command

import me.justadeni.colorblock2.ColorBlock2
import me.justadeni.colorblock2.Config
import me.justadeni.colorblock2.misc.Msg
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object Command : CommandExecutor{
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (command.name != "colorblock")
            return true
        if (sender is Player) {
            if (!sender.hasPermission(Config.adminpermission)) {
                sender.sendMessage(Config.permissionerror)
                return true
            }
        }
        if (args.isEmpty()) {
            sender.sendMessage(Config.wrongargserror)
            return true
        }
        if (args.size == 1){
            if (args[0] != "reload") {
                sender.sendMessage(Config.wrongargserror)
                return true
            }
            sender.sendMessage(Config.configreloaded)
            Config.reload()
            return true
        }
        sender.sendMessage(Config.wrongargserror)
        return true
    }


}