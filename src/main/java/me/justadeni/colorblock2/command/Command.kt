package me.justadeni.colorblock2.command

import me.justadeni.colorblock2.ColorBlock2
import me.justadeni.colorblock2.misc.Msg.color
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object Command : CommandExecutor{

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean{

        if (command.name != "colorblock")
            return true

        if (sender is Player) {
            if (!sender.hasPermission(ColorBlock2.confik.adminpermission)) {
                sender.sendMessage(ColorBlock2.confik.pluginprefix.color() + ColorBlock2.confik.permissionerror.color())
                return true
            }
        }
        if (args.isEmpty()) {
            sender.sendMessage(ColorBlock2.confik.pluginprefix.color() + ColorBlock2.confik.wrongargserror.color())
            return true
        }
        if (args.size == 1) {
            if (args[0] != "reload") {
                sender.sendMessage(ColorBlock2.confik.pluginprefix.color() + ColorBlock2.confik.wrongargserror.color())
                return true
            }
            sender.sendMessage(ColorBlock2.confik.pluginprefix.color() + ColorBlock2.confik.configreloaded.color())
            ColorBlock2.confik.reload()
            ColorBlock2.confik.assign()
            return true
        }
        sender.sendMessage(ColorBlock2.confik.pluginprefix.color() + ColorBlock2.confik.wrongargserror.color())
        return true
    }

}