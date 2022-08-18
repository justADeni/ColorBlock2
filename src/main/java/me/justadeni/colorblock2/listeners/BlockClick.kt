package me.justadeni.colorblock2.listeners

import me.justadeni.colorblock2.ColorBlock2
import me.justadeni.colorblock2.colorables.*
import me.justadeni.colorblock2.enums.Blocks
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack

class BlockClick : Listener {

    @EventHandler
    suspend fun onBlockClick(e : PlayerInteractEvent) {
        if (e.action != Action.RIGHT_CLICK_BLOCK)
            return

        if (e.hand == null)
            return

        val block = e.clickedBlock!!

        val blockname = block.type.name

        if (Blocks.match(blockname) == "")
            return

        val player = e.player

        val hand = if (e.hand == EquipmentSlot.HAND) {
            player.inventory.itemInMainHand
        } else {
            player.inventory.itemInOffHand
        }

        val iscreative: Boolean = player.gameMode == GameMode.CREATIVE

        val drop: Boolean = if (iscreative) {
            ColorBlock2.confik.droponcreative
        } else {
            ColorBlock2.confik.droponsurvival
        }

        if (player.isSneaking) {
            if (!(player.hasPermission(ColorBlock2.confik.undyepermission) || player.hasPermission(ColorBlock2.confik.adminpermission)))
                return

            if (!hand.type.isAir)
                return

            if (e.hand == EquipmentSlot.OFF_HAND)
                if (player.inventory.itemInMainHand.type.isAir)
                    return

            if (!ColorBlock2.manager.canDye(player, block))
                return

            e.isCancelled = true

            when (Blocks.match(blockname)) {
                "SHULKER_BOX" -> ShulkerBox().unpaint(block, drop, player)
                "BED" -> Bed().unpaint(block, drop, player)
                "CONCRETE_POWDER" -> ConcretePowder().unpaint(block, drop, player)
                "CONCRETE" -> Concrete().unpaint(block, drop, player)
                "GLAZED_TERRACOTTA" -> GlazedTerracotta().unpaint(block, drop, player)
                "TERRACOTTA" -> Terracotta().unpaint(block, drop, player)
                "CARPET" -> Carpet().unpaint(block, drop, player)
                "GLASS_PANE" -> StainedGlassPane().unpaint(block, drop, player)
                "GLASS" -> StainedGlass().unpaint(block, drop, player)
                "WOOL" -> Wool().unpaint(block, drop, player)
                "CANDLE" -> Candle().unpaint(block, drop, player)
            }
        } else {
            if (!(player.hasPermission(ColorBlock2.confik.dyepermission) || player.hasPermission(ColorBlock2.confik.adminpermission)))
                return

            if (!hand.type.name.contains("DYE"))
                return

            val dye = hand.type.name

            if (e.hand == EquipmentSlot.OFF_HAND)
                if (player.inventory.itemInMainHand.type.name.contains("DYE"))
                    return

            if (!ColorBlock2.manager.canDye(player, block))
                return

            e.isCancelled = true

            val use: Boolean = if (iscreative) {
                ColorBlock2.confik.useoncreative
            } else {
                ColorBlock2.confik.useonsurvival
            }

            val subtract = when (Blocks.match(blockname)) {
                "SHULKER_BOX" -> ShulkerBox().paint(block, dye, drop, player)
                "BED" -> Bed().paint(block, dye, drop, player)
                "CONCRETE_POWDER" -> ConcretePowder().paint(block, dye, drop, player)
                "CONCRETE" -> Concrete().paint(block, dye, drop, player)
                "GLAZED_TERRACOTTA" -> GlazedTerracotta().paint(block, dye, drop, player)
                "TERRACOTTA" -> Terracotta().paint(block, dye, drop, player)
                "CARPET" -> Carpet().paint(block, dye, drop, player)
                "GLASS_PANE" -> StainedGlassPane().paint(block, dye, drop, player)
                "GLASS" -> StainedGlass().paint(block, dye, drop, player)
                "WOOL" -> Wool().paint(block, dye, drop, player)
                "CANDLE" -> Candle().paint(block, dye, drop, player)
                else -> false
            }

            if (!use)
                return

            if (!subtract)
                return

            if (e.hand == EquipmentSlot.HAND) {
                if (hand.amount > 1)
                    player.inventory.setItemInMainHand(ItemStack(hand.type, hand.amount - 1))
                else
                    player.inventory.setItemInMainHand(ItemStack(Material.AIR))
            } else {
                if (hand.amount > 1)
                    player.inventory.setItemInOffHand(ItemStack(hand.type, hand.amount - 1))
                else
                    player.inventory.setItemInOffHand(ItemStack(Material.AIR))
            }

        }

    }
}