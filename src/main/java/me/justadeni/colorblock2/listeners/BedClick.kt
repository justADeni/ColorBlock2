package me.justadeni.colorblock2.listeners

import me.justadeni.colorblock2.ColorBlock2
import me.justadeni.colorblock2.enums.Dyes
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerBedEnterEvent

class BedClick : Listener {

    @EventHandler
    suspend fun onBedClick(e : PlayerBedEnterEvent){
        if (e.player.isSneaking){
            if (e.player.hasPermission(ColorBlock2.confik.undyepermission) || e.player.hasPermission(ColorBlock2.confik.adminpermission))
                if (e.player.inventory.itemInMainHand.type.isAir || e.player.inventory.itemInOffHand.type.isAir)
                    if (e.bed.blockData.material != Material.WHITE_BED)
                        e.isCancelled = true

        } else {
            if (e.player.hasPermission(ColorBlock2.confik.dyepermission) || e.player.hasPermission(ColorBlock2.confik.adminpermission)){

                if (e.player.inventory.itemInMainHand.type.isAir && e.player.inventory.itemInOffHand.type.isAir)
                    return

                val mainDye = Dyes.match(e.player.inventory.itemInMainHand.type.name)
                val offDye  = Dyes.match(e.player.inventory.itemInOffHand.type.name)

                val finalDye : String = if (mainDye != "" && offDye == ""){
                    mainDye
                } else if (mainDye == "" && offDye != ""){
                    offDye
                } else if (mainDye != "" && offDye != ""){
                    mainDye
                } else {
                    return
                }

                if (e.bed.blockData.material.name.contains(finalDye))
                    return

                e.isCancelled = true
            }
        }
    }

}