// Context //
Over the past few decades, the number of people who suffer from hunger has been slowly declining. However, this number has been slowly increasing again in 2015. Additionally, the COVID-19 pandemic could now double that number, causing another 130 million people at risk of suffering hunger by the end of 2020.

// Purpose/Use //
This program will serve as a simulation, simulating the life of a farmer who has a family of 4 that lives in the farmlands of a developing country. As a farmer, the user will have to manage their crops, hunger/nutrition levels, and business. 

// Time Mechanic //
This simulation will have a time mechanic, where the user can choose to do 1 action per day. The user can choose out of tending to the farm, spending time with family, or doing business. After finishing an activity, the user will have to sleep and start the next day. 

By the end of 30 days, if the user survives, the simlulation will congratulate them on living through to the end. Ideally, the user will have a better understanding of how difficult hunger is to handle.

// Farm Mechanic //
The user will have a 3x3 farm land, made with a 2-D array, that they will have to manage over the course of a month. This farm starts off with blank spaces of land with initial values of 0. Inside each element space, there will be a numerical value. This value indicates the weight/worth of that particular crop and new crops will start as a value of 1. For simplicity, crops will increase by a value of 1 per day. However, using random number generation (RNG), crops have a small chance (RNG) to instantly go bad and be rendered as waste, resetting the crop's value to 0.
    
As an action, the user can choose between fertilizing or harvesting their crops.
In order to fertilize the crops, the user must pay $50. By fertilizing crops, the value growth is multiplied by 2 until those crops are harvested.
If the user wishes to harvest their crops, all their crops (the sum of the values) will be added to their storage and the values of all crops will return to 0. The user can then choose how many rows of crop to plant back, each row costing $25. 
    
// Hunger/Nutrition Mechanic //
The user will have a level of hunger they must maintain. If this level were to ever meet 0, the simulation ends and the user 'starves'. The user can eat before they perform any time-consuming Actions. The max hunger level the user can obtain is 100. The user can eat their own crops, exchanging 1:3 ratio of crop to hunger.

Different Actions consume different amounts of hunger. 
Here is the hunger per action:
- Tending crops  : 0
- Fertilizing    : 25
- Planting       : 5 per row
- Harvesting     : 25
- Doing business : 15
- Resting        : 5

// Business Mechanic // 
By indulging in business, the user will be able to select 1 out of the 3 available people to talk to. Each person has their own crop/$ trade ratio. Some people may have better ratios than others, though they might have more concerning aspects explained later. The user can check their money by selecting the business menu.

// Pandemic Mechanic //
 Seeing how our current pandemic will affect the issue of hunger in our world, implementing this into this simulation will ideally show the connection between the pandemic and its effect on hunger. 
    
At the beginning of the simulation, there will be a 50% chance (RNG) of there being a pandemic going on. The sickness can heavily impact the simulation. As mentioned before, there will be 3 different people the user can talk with to do business. However, ones with better trade ratios will have a higher chance (RNG) of carrying the virus, thereby possibly transferring it to the user.

If the user happens to catch the virus, the user is incapable of working on their farm. The user is still able to do business.

// Resting Mechanic // 
If the user does not wish to do any other actions, they can choose to rest for the day. If the user has the virus, resting gives them a 5% chance (RNG) of recovering and being well again. 
