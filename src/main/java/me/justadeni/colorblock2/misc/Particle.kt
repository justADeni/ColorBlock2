package me.justadeni.colorblock2.misc

import com.github.shynixn.mccoroutine.bukkit.ticks
import kotlinx.coroutines.delay
import org.bukkit.Location
import org.bukkit.Particle
import org.bukkit.block.Block
import org.bukkit.util.Vector


object Particle {

    suspend fun Particle(type: String, block: Block) {

        if (type.equals("NONE", ignoreCase = true))
            return

        val particle: Particle = Particle.valueOf(type.uppercase())

        val loc1 = Location(block.world, block.x+0.5, block.y+0.5, block.z+0.5)
        for (x in -1..1) {
            for (y in -1..1) {
                for (z in -1..1) {
                    val loc2 = Location(
                        block.world,
                        (block.x + 0.5 + x),
                        (block.y + 0.5 + y),
                        (block.z + 0.5 + z)
                    )
                    drawLine(loc1, loc2, particle)
                    delay(2.ticks)
                }
            }
        }
    }

    //from https://bukkit.org/threads/making-a-particle-line-from-point-1-to-point-2.465415/
    private suspend fun drawLine(loc1: Location, loc2: Location, particle: Particle) {
        //point2.getWorld()?.let { Validate.isTrue(it.equals(world), "Lines cannot be in different worlds!") }
        val space = 0.2
        val distance: Double = loc1.distance(loc2)
        val p1: Vector = loc1.toVector()
        val p2: Vector = loc2.toVector()
        val vector: Vector = p2.clone().subtract(p1).normalize().multiply(space)
        var length = 0.0
        while (length < distance) {
            loc1.world?.spawnParticle(particle, p1.x, p1.y, p1.z, 1)
            length += space
            p1.add(vector)
        }
    }

}