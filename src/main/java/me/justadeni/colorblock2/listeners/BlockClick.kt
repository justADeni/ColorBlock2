package me.justadeni.colorblock2.listeners

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
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

        var slot = true
        val player = e.player
        val hand = if (e.hand == EquipmentSlot.HAND) {
            player.inventory.itemInMainHand
        } else {
            slot = false
            player.inventory.itemInOffHand
        }

        val iscreative: Boolean = player.gameMode == GameMode.CREATIVE

        if (player.isSneaking) {
            if (!(player.hasPermission(ColorBlock2.confik.undyepermission) || player.hasPermission(ColorBlock2.confik.adminpermission)))
                return

            if (!hand.type.isAir) {
                return
            }

            e.isCancelled = true

            val droponcreative: Boolean = if (iscreative) {
                ColorBlock2.confik.droponcreative
            } else {
                true
            }

            when (Blocks.match(blockname)){
                "SHULKER_BOX" -> ShulkerBox().unpaint(block, droponcreative, player)
                "BED" -> Bed().unpaint(block, droponcreative, player)
                "CONCRETE_POWDER" -> ConcretePowder().unpaint(block, droponcreative, player)
                "CONCRETE" -> Concrete().unpaint(block, droponcreative, player)
                "GLAZED_TERRACOTTA" -> GlazedTerracotta().unpaint(block, droponcreative, player)
                "TERRACOTTA" -> Terracotta().unpaint(block, droponcreative, player)
                "CARPET" -> Carpet().unpaint(block, droponcreative, player)
                "STAINED_GLASS_PANE" -> StainedGlassPane().unpaint(block, droponcreative, player)
                "STAINED_GLASS" -> StainedGlass().unpaint(block, droponcreative, player)
                "WOOL" -> Wool().unpaint(block, droponcreative, player)
                "CANDLE" -> Candle().unpaint(block, droponcreative, player)
            }
        } else {
            if (!(player.hasPermission(ColorBlock2.confik.dyepermission) || player.hasPermission(ColorBlock2.confik.adminpermission)))
                return

            if (!hand.type.name.contains("DYE"))
                return

            val dye = hand.type.name

            e.isCancelled = true

            val useoncreative: Boolean = if (iscreative) {
                ColorBlock2.confik.useoncreative
            } else {
                true
            }

            val droponcreative: Boolean = if (iscreative) {
                ColorBlock2.confik.droponcreative
            } else {
                true
            }

            var subtract = useoncreative

            coroutineScope {

                launch {

                    when (Blocks.match(blockname)) {
                        "SHULKER_BOX" -> subtract = ShulkerBox().paint(block, dye, droponcreative, player)
                        "BED" -> subtract = Bed().paint(block, dye, droponcreative, player)
                        "CONCRETE_POWDER" -> subtract = ConcretePowder().paint(block, dye, droponcreative, player)
                        "CONCRETE" -> subtract = Concrete().paint(block, dye, droponcreative, player)
                        "GLAZED_TERRACOTTA" -> subtract = GlazedTerracotta().paint(block, dye, droponcreative, player)
                        "TERRACOTTA" -> subtract = Terracotta().paint(block, dye, droponcreative, player)
                        "CARPET" -> subtract = Carpet().paint(block, dye, droponcreative, player)
                        "STAINED_GLASS_PANE" -> subtract = StainedGlassPane().paint(block, dye, droponcreative, player)
                        "STAINED_GLASS" -> subtract = StainedGlass().paint(block, dye, droponcreative, player)
                        "WOOL" -> subtract = Wool().paint(block, dye, droponcreative, player)
                        "CANDLE" -> subtract = Candle().paint(block, dye, droponcreative, player)
                    }
                }

                launch {
                    if (!useoncreative)
                        return@launch

                    if (!subtract)
                        return@launch

                    if (slot) {
                        val itemstacc = player.inventory.itemInMainHand
                        if (itemstacc.amount > 1) {
                            player.inventory.setItemInMainHand(ItemStack(itemstacc.type, itemstacc.amount - 1))
                        } else {
                            player.inventory.setItemInMainHand(ItemStack(Material.AIR))
                        }
                    } else {
                        val itemstacc = player.inventory.itemInOffHand
                        if (itemstacc.amount > 1) {
                            player.inventory.setItemInOffHand(ItemStack(itemstacc.type, itemstacc.amount - 1))
                        } else {
                            player.inventory.setItemInOffHand(ItemStack(Material.AIR))
                        }
                    }
                }
            }
        }

    }
}