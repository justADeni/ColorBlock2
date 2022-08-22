[![Codacy Badge](https://app.codacy.com/project/badge/Grade/7138e8d6a281409cbfdb2eff876450a5)](https://www.codacy.com/gh/justADeni/ColorBlock2/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=justADeni/ColorBlock2&amp;utm_campaign=Badge_Grade)
# ColorBlock2
Dye blocks by clicking them with a dye!

[Original ColorBlock](https://github.com/justADeni/ColorBlock) was one of my first plugins, and it was written poorly. 
ColorBlock2 aims to do the same thing, but more elegantly, with proper OOP and in Kotlin

### If you encounter any issue or want to submit a feature request, feel free to message me on Discord or open bug report/pull request

![](https://github.com/justADeni/ColorBlock2/blob/master/img/1.png?raw=true)
![https://www.spigotmc.org/resources/kotlin-stdlib.80808/](https://github.com/justADeni/ColorBlock2/blob/master/img/ColorBlock-Dependencies.png?raw=true)
![](https://github.com/justADeni/ColorBlock2/blob/master/img/2.png?raw=true)
![](https://github.com/justADeni/ColorBlock2/blob/master/img/3.png?raw=true)
![](https://github.com/justADeni/ColorBlock2/blob/master/img/4.png?raw=true)

```yaml
###################################################
#                                                 #
#                ColorBlock2 Config               #
#                                                 #
###################################################

#Hooks for various protection plugins
WorldGuardHook: false
LandsHook: false

#Drop option indicates whether old dye of block drops when coloring block
DropOnCreative: false
DropOnSurvival: true
#Use option indicates whether new dye gets consumed when coloring block
#Recommended to set both things to same values to avoid creating infinite dye exploit
UseOnCreative: false
UseOnSurvival: true

#Allows you to customize permissions
DyePermission: "colorblock.dye"
UndyePermission: "colorblock.undye"
AdminPermission: "colorblock.admin"

#Allows You to customize in-game messages. Hex color codes or classic color codes are supported
ConfigReloaded: "#3A9C20Config reloaded#31E900"
PermissionError: "#AB4C37Insufficient permissions#FF0101"
WrongArgsError: "#AB4C37Wrong or insufficient arguments#FF0101"
PluginPrefix: "#D80F52[ColorBlock2] #30D3F3"

#Allows you to set sound that will be transmitted when dyeing a block. Set to "NONE" to disable
ColorSound: "BLOCK_BAMBOO_HIT"
#Volume indicates how far away sound will be heard. 5.0 means it will be heard from 5 blocks away
ColorVolume: 5.0

UncolorSound: "BLOCK_SOUL_SAND_BREAK"
UncolorVolume: 5.0

#Allows you to set Particle effects when dyeing or undyeing a block. Set to "NONE" to disable
ColorParticle: "FIREWORKS_SPARK"
UncolorParticle: "CRIT"

#Particle Chance of appearing, 0 to 100
#0 to disable, 100 = all 27 particles
ParticleChance: 50
```

![](https://github.com/justADeni/ColorBlock2/blob/master/img/5.png?raw=true)
