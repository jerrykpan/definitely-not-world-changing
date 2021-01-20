/*
GOALS:
- Live for 40 years
- Hatch 100 eggs


Region A Asia:
- mate/lay egg zone (can only lay here)
- more predators////////////////
- higher hurricane chance /////////////////


Region B North America:
- more food supplies (are less affected by natural disasters) (live life)/////////////
- higher tsunami chance  


Mechanics:
$$$ Actions $$$
mate() method (includes mate and lay)
- Mate (50%-5% depending on temperature, scales linearly) 
- Laying eggs: 110 eggs per nest, 2-8 nests per year, happens right after you mate (if laid during earthquake, instant death) 1 year cooldown period (cannot lay eggs)

determineIfHatchEgg() method for determining how many eggs survived until adulthood 
- Hatching eggs: 1% chance

live() method 
- Live: Spend the year living (takes 1 year)

$$$ Events $$$ 

migration() (moves the user to the other region)
- Migrate: Move to other region (takes 1 year and reduces finding food by 40%)
migration(boolean, naturalDisaster) 
- Force migration: Caused from food shortage or inhabitability caused by climate change (takes 1 year and reduces finding food by 40%, but also adds 10% another chance of dying from whatever the user is esacaping)
- Migrational death: (1-10% chance, based on temperature) 

earthquake() (determines if it happens and what would happen)
- Earthquake (10%-25%, based on temperature, instant death for egg nest)

predator() (determines if it happen and what would happen)
- Predators (40-5% based on age and termperature, cause instant death, population decreases with temperature increase)

tsunami() method (determines if it happens and what would happen)
- Tsunami ( 5-10% based on temperature, deals 30 dmg)

hurricane() method (determine if it happens and what would happen)
- Hurricane ( 5-30% based on temperature, 40 dmg)  

volcanoEruption() (determine if it happens and what would happen)
- Underwater volcano eruption (1% instant death chance)

checkIfFoundFood() (determines if the user has found a sufficient amount of food and what would each each possible option)
- Eating: Every year, there is a percent chance of finding sufficient amounts of food in your current region. This value may or may not change due to natural disasters. If the turtle has found sufficient food, then they do not take any dmg. If the turtle has not found sufficient food, they take 1-6 dmg (random chance).

aftermath(): educate user about seriousness of global climate change. Relate to experiences of turtle. Give user big lesson. 

Order of completion:
- tsunami()                           -A  Y
- earthquake()                        -A  Y
- hurricane()                         -j  Y
- predator()                          -j  Y
- volcanoEruption()                   -A  Y
- aftermath()                         -A  y
- mate()                              -j  Y
- determineIfHatchEgg()               -A  Y
- checkIfFoundFood()                  -j  Y
- migration()                         -A  Y
- migration(boolean naturalDisaster)  -j  Y
- printMenu()                         -j  Y

- live()                              -Both
- Main()                              -Both

Possible turtle actions:
- Migrate
- Live/find food for the time being
- Find a mate/lay eggs
- migration(boolean naturalDisaster) 
-

Find habitat 

TO-DO:
- make volcanic eruption be called in the migrate 
- check for hp in volcanic eruption
- make death message for volcanic eruption
- another possible description for live() (semi optional)
*/