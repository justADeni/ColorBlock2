package me.justadeni.colorblock2.misc

import me.justadeni.colorblock2.ColorBlock2
import org.bukkit.Location
import org.bukkit.Particle
import org.bukkit.block.Block
import org.bukkit.util.Vector


object Particle {

    fun ColorParticle(block : Block){

        if (ColorBlock2.confik.colorparticle.equals("NONE", ignoreCase = true))
            return

        val particle: Particle = Particle.valueOf(ColorBlock2.confik.colorparticle.uppercase())

        val loc1 = block.location
        val loclist = listOf(-1, 1)
        for (x in loclist) {
            for (y in loclist) {
                for (z in loclist) {
                    val location = Location(
                        block.world,
                        (block.x + x).toDouble(),
                        (block.y + y).toDouble(),
                        (block.z + z).toDouble()
                    )
                    drawLine(loc1, location, particle)
                }
            }
        }


    }

    fun UncolorParticle(block : Block){
        if (ColorBlock2.confik.uncolorparticle.equals("NONE", ignoreCase = true))
            return

        val particle : Particle = Particle.valueOf(ColorBlock2.confik.uncolorparticle.uppercase())

        val loc1 = block.location
        val loclist = listOf(-1,1)
        for (x in loclist){
            for (y in loclist){
                for (z in loclist){
                    val location = Location(
                        block.world,
                        (block.x + x).toDouble(),
                        (block.y + y).toDouble(),
                        (block.z + z).toDouble()
                    )
                    drawLine(loc1, location, particle)
                }
            }
        }
    }

    //from https://bukkit.org/threads/making-a-particle-line-from-point-1-to-point-2.465415/
    private fun drawLine(loc1: Location, loc2: Location, particle: Particle) {
        //point2.getWorld()?.let { Validate.isTrue(it.equals(world), "Lines cannot be in different worlds!") }
        val space = 0.1
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