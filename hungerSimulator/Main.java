import java.util.Scanner; // importing the Scanner class for user input
import java.util.Random; // importing the Random class for random number generation


class Main {
  // declaring static variables so that multiple parts of the simulation can use static variables
  // simulation variables
  static Random rand = new Random();  // creating the Random object, for random generation
  static Scanner input = new Scanner ( System.in ); // creating the Scanner object, for reading user input
  static double cropStorage = 0;         // variable for quantity of crop the user has
  static int hunger = 100;               // variable of the user's hunger level
  static double growthRate = 1;          // variable of crop growth rate multiplier
  static double money = 500;             // variable to keep track of the user's money
  static String response;                // declaring the user input variable, for storing the user's input
  static int day = 1;                    // variable to keep track of day #  
  static int tradeOffer;                 // variable for holding the amount of crop the user wants to trade
  static int hungerCostPlantRow = 5;     // number variable for how much hunger is consumed by planting a row in the farm
  static int hungerCostFertilize = 15;   // number variable for how much hunger is consumed by fertilizing
  static int hungerCostHarvest = 15;     // number variable for how much hunger is consumed by harvesting crops
  static int hungerCostRest = 5;         // number variable for how much hunger 
  static int hungerCostBusiness = 10;    // number variable for how much hunger is consumed by doing business
  static String [] traderNames = {"Lowe", "Meddel", "Hye"};    // the names of each trader the user can exchange with
  static double [] traderRatios = {0.90, 1.25, 2.0};       // the trade ratios of each business person who the user can trade with
  static double profit;                  // variable for storing the user's trade profit for the day
  static int cropDeathChance = 25;       // variable for the percent chance of crops dying, checked once daily
  static int fertilizedDeathChance = 15;  // variable for the percent chance of a fertilized crop dying
  static int virusDailyDeathChance = 10; // the variable for the percent chance of dying from the virus, checked once daily
  static int [] traderVirusChances = {5, 15, 45};     // list of numbers representing the percent chance of catching the virus through the business people
  static int virusRecoveryChance = 5;    // the variable for the percent chance of recovering from the virus by resting
  static int restRecoveryBoost = 5;     // number variable for the extra percent chance of recovering from the virus
  static boolean wellRested = false;    // variable to determine if the user rested for that day
  static double costPlantRow = 25;       // number variable for how much money it costs to plant a row of crops
  static double costFertilize = 50;           // number variable for how money it costs to fertilize the user's farm
  static int wantEat;                    // variable for user's input of how much they would like to eat
  static double hungerPerCrop = 3;       // variable for crop to hunger ratio 
  static boolean alive = true;           // setting the status of the user's life
  static boolean virus = false;          // variable to determine if a virus outbreak 
  static boolean caughtVirus = false;    // variable to determine if the user has caught the virus
  static boolean fertilizedFarm = false; // variable to determine if the user has fertilized their farm
  // achievement variables
  static int achievements = 5;               // number of possible achievements in the simulation
  static int achievementsGotten = 0;         // number of achievements gotten in one runthrough of the simulation
  static int totalCropsHarvested = 0;        // variable for the total amount of crops harvested
  static int timesRested = 0;                // number of how many times the user rested
  static boolean recoveredVirus = false;           // variable to determine if the user recovered from the virus
  static boolean tradedTrader  [] = {false, false, false};  // list of whether the user traded with each trader


  public static void intro(){
    // A: tell user about the introduction to simulation and instructions/controls of the simulation
    System.out.println("It is 2020. You are a farmer with a family of 4 who has to manage a farm and business relations with nearby business people. Unfortunately, malnutrition/hunger has been affecting your local area due to crops dying of weathering and poor conditions.\n");           // first few introductory sentences describing the scenario
    if (virus) {                // if the virus exists
      System.out.println("Additionally, there is also a global virus outbreak you have to watch out for. Though not extremely contagious, it is a very difficult virus to survive.\n");           // virus warning message
    }
    System.out.println("The goal in this simulation is to survive for 30 days. During this time, you can perform an Action per day. You can choose between tending to the farm, doing business with people, or resting for the day. Once you complete an Action, the day will pass and the next will start.\n\nPerforming actions will decrease your hunger level. In order to maintain your hunger, you must eat some of your own crops that you grow. Losing hunger can result in unpleasant outcomes. If you would like more details, they will be in the Help section.\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"); // summarizing what the user can do
  }

  
  public static boolean checkFarmEmpty(double [][] farm) {
    // Checks to see if the farm is empty
    for (int i = 0; i < farm.length; i++) {       // iterating through each farm row
      for (int j = 0; j < farm[i].length; j++) {  // iterating through each space in the row
        if (farm[i][j] != 0) {                    // if the space is not empty
          return false;                           // the farm is not empty
        }
      }
    }
    return true;                                  // the farm is empty
  }


  public static boolean checkRowEmpty(double [] row) {
    // Checks to see if the row is empty
    for (int i = 0; i < row.length; i++) {    // iterates through each element
      if (row[i] != 0) {                      // if the cell is not empty
        return false;                      
      }
    }
    return true;
  }



  public static void showFarm(double [][] farm) {
    // Displays the user's current farm layout with each square's values
    double col1, col2, col3; // temporary variables for storing the row values
    System.out.println("\n-------------------");  // top line of the grid
    for (int i = 0; i < farm.length; i++) {
      col1 = farm[i][0];    // value in the first column slot
      col2 = farm[i][1];    // value in the second column slot
      col3 = farm[i][2];    // value in the third column slot
      System.out.printf("| %3.0f | %3.0f | %3.0f |\n", col1, col2, col3);        // formatting the output of the array farm
      System.out.println("-------------------"); // printing a break line for formatting
    }
  }


  public static void plantCrops(double [][] farm) {
    // sets empty rows of crops in user's farm to 1 depending on the user's input
    System.out.print("\nHow many rows would you like to plant? (max 3): ");   // prompting the user for how many crops they would like to plant
    int rows = input.nextInt();                       // reading the user's choice of rows to plant
    input.nextLine();                                 // preventing nextInt/nextLine error    
    while ((rows * costPlantRow) > money) {
      System.out.print("\nYou do not have enough money for this number of rows to plant. Please enter an appropriate amount: ");                         // prompting the user for a valid value
      rows = input.nextInt();                         // reading the user's choice of rows to plant
      input.nextLine();                               // preventing nextInt/nextLine error   
    }
    money -= rows * costPlantRow;                     // subtracting the money cost of planting crops per row
    hunger -= rows * hungerCostPlantRow;              // subtracting the hunger cost of planting crops per row
    int rowNum = 0;                                   // keeping track of the row number
    int rowsPlanted = 0;                              // variable to keep track of many rows of crops were planted
    if (rows >= 1 && rows <= farm.length) {           // if the user entered a valid input for the number of rows
      while (rowNum < farm.length && rowsPlanted < rows) {    // iterating through the number of rows the user inputted
        if (checkRowEmpty(farm[rowNum])) {            // if the row is empty
          for (int j = 0; j < farm[rowNum].length; j++) {  // iterating through each space in the row
            farm[rowNum][j] = 1;                           // setting the space of farm to the value of 1
          }
          rowsPlanted += 1;                           // noting that another row has been planted
        }
        rowNum += 1;            // add to row number
      }
      if (rowsPlanted == rows) {
        System.out.println("\nGazing at all your hard work, you feel a sense of pride in what you do. You head back to your house ready for the rest of the day...");     // message of successful planting
      } else if (rowsPlanted < rows) {         // if the user planted excess rows of crops
      System.out.println("\n\nStaring at all your filled up crop space, you stand there with excess crop plant in hand. Confused, you throw away the crop plant wondering why you bothered bringing so much to plant. Seems like you wasted excess money and energy for nothing...");         // message saying they could only plant to the maximum amount
      }
    } else {        // if the user entered an invalid input
      System.out.println("\nYou couldn't make up your mind on how many to plant. The numbers inside your head caused your brain to swirl and you pass out. You wake up the next day...");   // telling the user their input did not work and the day has passed
    }
  }


  public static void tendFarm(double [][] farm) {
    // Adds 1 to the value in each square of the user's farm if they have planted crops already
    for (int i = 0; i < farm.length; i++){            // iterating through each row
      for (int j = 0; j < farm[i].length; j++) {      // iterating each space in the row
        if (farm[i][j] != 0 ){                        // if this row of crop is not empty 
          farm[i][j] += 1;                            // raising this space of crop value by 1
        }
      }
    }
  }

  public static void fertilizeFarm(double [][] farm) {
    // Turn the growth rate multipler of the current crops from 1 to 2
    growthRate = 2;                             // setting the growth rate to two
    hunger -= hungerCostFertilize;              // hunger the user loses for fertilizing
    money -= costFertilize;                     // money the user loses for fertilizing
    System.out.println("\nAfter fertilizing your crops for the day, you feel that you are growing, just like your crops. You feel thirsty and decide to head in for a nice cup of water...");       // confirmation message to the user that they have fertlized their crops
    fertilizedFarm = true;          // indicate that the farm has been fertilized
  }


  public static void harvestFarm(double [][] farm) {
    // Iterates through each space in the farm, adds it to the crop storage, and resets its value to 0
    int sumCrops = 0;                               // variable for the sum of crops from this patch
    for (int i = 0; i < farm.length; i++) {         // iterating through each farm row
      for (int j = 0; j < farm[i].length; j++) {    // iterating through each column in the farm row
        sumCrops += farm[i][j];                  // adding the crop value to the storage
        farm[i][j] = 0;                             // resetting the crop value to 0
      }
    }
    totalCropsHarvested += sumCrops;                // adding the crops from this patch to the total harvested
    cropStorage += sumCrops;                        // adding the crops from this patch to the storage
    if (sumCrops != 0) {            // if there was harvested crop
      System.out.println("\nYou have emptied your farm of crops. In your storage, you have " + cropStorage + " amounts of crop.");      // telling the user their farm is empty and giving them the amount in their storage
    } else {
      System.out.println("\nProductively, you spent your entire day by picking at an empty ground. When you see the sun sets, you realize that you have no idea what you are doing. You pack up your harvest (nothing) and head in for the day...");      // message for if the user harvests an empty field
    }
    growthRate = 1;                             // resets the growth rate of crops to 1
    hunger -= hungerCostHarvest;                // hunger the user loses for harvesting
    fertilizedFarm = false;                     // indicate that the farm has been reset
  }


  public static void business() {
    // displays three options of people to trade with, user picks trader, user proposes how much to trade, exchange is made
    int traderID;                   // variable for storing the ID of the trader that the user wants
    System.out.println("\n---------\nBusiness\n---------\n");   // displaying title formatting for business menu
    System.out.println("You head to your local farmer's market, bringing all that you need with you. After you walking around, you found three interesting clients you would be interested in selling to. If you recall correctly, these were the details of each of them were...");    // telling the user the details of the scenario
    for (int i = 0; i < traderNames.length; i++) {    // iterating through each trader
        System.out.printf("\n%s: $%.2f/crop", traderNames[i], traderRatios[i]);   // displaying information about each trader
      if (virus) {      // if there is a virus in this simulation
        System.out.print(" - " + traderVirusChances[i] + "% virus"); // tells the user of catching the virus from this trader
      }
    }
    System.out.print("\n\nCrops: " + cropStorage);          // displaying the crop storage
    System.out.print("\n\nWho would you like to trade with?\na. " + traderNames[0] + "\nb. " + traderNames[1] + "\nc. " + traderNames[2] + "\nPlease make a selection: ");   // prompting the user to pick a trader
    response = input.nextLine();    // receiving the user's input

    if (response.equalsIgnoreCase("a")) {           // if they picked the first trader
      traderID = 0;                                 // setting the trader ID 
    } else if (response.equalsIgnoreCase("b")) {    // if they picked the second trader
      traderID = 1;                                 // setting the trader ID
    } else if (response.equalsIgnoreCase("c")) {    // if they picked the third trader
      traderID = 2;                                 // setting the trader ID
    } else {                                        // if they entered an invalid input
      traderID = -1;                                // setting the trader ID to a null value
      System.out.println("\nYou couldn't decide on who to talk to. Before you realize it, the day is over and it's time to pack it up. You take the walk of shame home...");        // sell fail message 
    }   

    if (traderID > 0) {                                        // if they picked a trader
      System.out.print("\nHow much crop would you like to trade?: ");   // prompting the user for how much crop they would like to trade
      tradeOffer = input.nextInt();                            // getting the amount of crop the user wants to trade
      input.nextLine();                                        // preventing nextInt/nextLine error   
      if (tradeOffer >= 1 && tradeOffer <= cropStorage) {      // if the user proposes a valid trade
        cropStorage -= tradeOffer;                             // subtracts the crop from the user's storage
        profit = tradeOffer * traderRatios[traderID];          // calculates the profit made from the exchange
        if (virus) {                                           // if the virus exists in this runthrough
            if (rand.nextInt(100) <= traderVirusChances[traderID]) {    // if user catches chance of virus
              caughtVirus = true;                              // user has caught the virus
              System.out.printf("\nFrom talking to %s you find that you are feeling a bit sick afterwards. After considering your symptoms for a bit, you realize you have caught the virus.\n", traderNames[traderID]);                          // message to user saying they caught the virus
            }
        }
        if (traderID == 0) {                                   // if the user wants to trade with the first trader
          System.out.print("\nTalking with " + traderNames[0] + " made you think about what it means to be a nice person. They were very humble and graciously accepted your trade.\n");  // message desciption about your experience with the trader
        } else if (traderID == 1) {                            // if the user wants to trade with the second trader
          System.out.print("\nYou had a fine time with " + traderNames[1] + ". You wouldn't mind doing business with them again another time.\n"); // message desciption about your experience with the trader
        } else if (traderID == 2) {                            // if the user wants to trade with the third trader        
          System.out.print("\nThis wasn't the most pleasant of business exchanges you've done, but you got what you wanted from " + traderNames[2] + ".\n"); // message desciption about your experience with the trader
        }
        tradedTrader[traderID] = true;                       // the user has traded with this trader 
        money += profit;             // adding the user's profit to their wallet
        System.out.printf("\nDuring this exchange you gave %d crop and received $%.2f. After a long day of business, you pack up shop and return home for a good ol' rest...\n", tradeOffer, profit);     // telling the user their profit and how much crop they sold
      } else if (tradeOffer > cropStorage) {                                            // if the user proposes more than what they have
        System.out.println("\nYou search through your sacks of crop to try and find the amount of crop you promised " + traderNames[traderID] + ". After losing interest, " + traderNames[traderID] + " loses interest and walks away. By the time you realize you can't fulfill your part of the deal, dusk is approaching and it's time to pack it up. You take the walk of shame home...");    // if the user tries to trade more than what they have message
      } else {
        System.out.println("\nDisappointed with your proposed trade, " + traderNames[traderID] + " loses interest and walks away. After staring at the ground in shame, you soon come to the realization that it is almost nighttime and it's time to pack it up. You take the walk of shame home...");     // if the user tries to trade less than 1 crop
      }
    }
    hunger -= hungerCostBusiness;     // subtracting the hunger cost of business
  }           


  public static boolean checkTraded(boolean [] traded) {
    // checks if all the values in the list of people traded with are true
    for (int i = 0; i < traded.length; i++) { // iterating through the list
      if (!traded[i]) {       // if they have not traded with this person
        return false;         
      }
    }
    return true;
  }


  public static void rest() {
    // Allows the user to rest for that day
    System.out.println("\nYou decide to rest for the day.");  // message telling the user they have rested
    wellRested = true;                     // confirming the user has rested
    hunger -= hungerCostRest;              // subtracting the cost of resting from the user's hunger
    timesRested += 1;                      // adding 1 to the number of times rested
  }


  public static void eat() {
    // Allows the user to restore their hunger level by consuming their crops
    if (hunger == 100) {          // if the user's hunger is at the max value
      System.out.println("\nYou cannot eat. You are full.");    // telling the user they cannot eat because they are at 100 hunger
    } else if (cropStorage < 1) {
      System.out.println("\nYou do not have any food to eat. Grow and harvest some from your farm to have something to eat.");      // telling the user they do not have any food to eat
    } else {                      // if the user can still eat
      System.out.print("Hunger: " + hunger + "\nCrops: " + cropStorage + "\nHow much would you like to eat: ");   // prompting the user for how much they would like to eat
      wantEat = input.nextInt();    // receiving how much the user would like to eat
      input.nextLine();             // preventing nextInt/nextLine error
      while (wantEat > cropStorage) {
        System.out.print("\n\nUnfortunately, you do not have enough food to nourish your hunger. Please choose a different number: ");   // telling the user they do not have the amount of food that they want
        wantEat = input.nextInt();  // receiving how much the user would like to eat
        input.nextLine();           // preventing nextInt/nextLine error
      } 
      if (wantEat * hungerPerCrop + hunger > 100) {   // if the user attempts to go over the max hunger value by overeating
        hunger = 100;       // setting hunger to the max value
        System.out.print("\nYou dig in, eating all you can.\nHunger: " + hunger + "\nUnfortunately, you had no where to put the excess food, so you're forced to dispose of it.");    // telling the user they are at the maximum hunger level and that the excess they ate is gone
      } else if (wantEat * hungerPerCrop > 0) {
        hunger += wantEat * hungerPerCrop;    // adding how much the user eats to their hunger level
        cropStorage -= wantEat;       // subtracting how much crop the user has eaten
        System.out.print("\nYou have a satisfactory meal.\n\nHunger: " + hunger + "\n\nYou feel better.\n");  // telling the user they have ate, showing the hunger level
      }
    }
  }


  public static void endDay(double [][] farm) {
    // This function calculates everything that happens at the end of the day which include day number, crop values, crop death chances, virus death chances
    int deadCrops = 0;
    day += 1;         // increases the day number by 1

    // determining if the crops rot, if not, adding values to the crops in the users farm, simulating crops growing and possibly rotting
    for (int i = 0; i < farm.length; i++) {         // iterating through each row in the farm
      for (int j = 0; j < farm[i].length; j++) {    // iterating through each space in the row
        if (farm[i][j] != 0) {                      // if the space is not empty
          if (fertilizedFarm) {                     // if the farm is fertilized
            if (rand.nextInt(100) <= fertilizedDeathChance) {   // if the crop dies of chance
              farm[i][j] = 0;                   // simulating the crops death
              deadCrops += 1;                   // adding 1 to the dead crops count
            } else {                            // if the crop lives
              farm[i][j] += growthRate;         // increasing the value by the growth rate
            }
          } else {
            if (rand.nextInt(100) <= cropDeathChance) {   // if the crop dies of chance
              farm[i][j] = 0;                   // simulating the crops death
              deadCrops += 1;                   // adding 1 to the dead crops count
            } else {                            // if the crop lives
              farm[i][j] += growthRate;         // increasing the value by the growth rate
            }
          }

        }
      }
    }
    if (deadCrops > 0) {            // if any of the user's crops died
      System.out.println("\n While you were asleep, " + deadCrops + " of your crops died last night. Turns out they withered away and what is left in that space is now just empty farmland.");    // message to the user letting them know their crops died
    }
    if (caughtVirus) {                                  // if the user caught the virus
      if (wellRested) {                                 // if the user has rested for the day
        virusRecoveryChance += restRecoveryBoost;       // adding the percent boost to recovering
      }
      if (rand.nextInt(100) <= virusRecoveryChance) {   // determining if the user recovers
        System.out.println("\nAfter waking up to some nice morning sunshine, you feel much better than better. Your nose is no longer stuffy and your throat is all better now. You have recovered from the virus!");     // telling the user they recovered from the virus
        caughtVirus = false;
        recoveredVirus = true;
      } else if (rand.nextInt(100) <= virusDailyDeathChance) {    // determining if the user dies
        System.out.println("\nYou knew this would come eventually. You've breathed your last breath. As if it were sand, you feel your soul slowly pour your body and you lose a grasp of your senses. The virus got to you.");       // virus death message
        alive = false;            // turning the user dead
      }
      if (alive) {                    // if the user is still alive
        if (hunger <= 0) {            // if the user starves to death
        System.out.println("\nAs you hear your stomach's last few cries for help, your legs, or rather, your entire body has given up on you.");       // anecdotal death message
        System.out.println("\nY O U   D I E D");    // telling the user that they have passed away
        alive = false;          // breaking out of the simulation loop
        }
      }
    }
    virusRecoveryChance -= restRecoveryBoost;     // resetting the virus recovery chance
  }

  // Simulation method 
  public static void simulation() {
    
    // option A:
    // A: initializing variables - farm 2d array, day #, boolean virus, people (w/wo virus%), virus recovery %, money 
    double [][] farm = {                     // 2-D array for user's farm
        {0, 0, 0},          // first row of farm
        {0, 0, 0},          // second row of farm
        {0, 0, 0}           // third row of farm
    };
    day = 1;                        // setting the day to 1
    
    // using random number generation to generate a 0 or 1, determines if there is a virus outbreak
    if (rand.nextInt(2) == 1) { // if the number is 1
      virus = true;             // set virus to true, indicating there is a virus outbreak
    } 

    // A: tell user about the introduction to simulation and instructions/controls of the simulation
    intro();

    // A: while loop - condition: while not dead or day # < 30
    while (alive && day <= 30) {                  // while the user is alive and has not reached day 30, keep running the simulation
      // A: - provide them with options for action, - prompt, get input, if statement - could create farm, business, and rest methods
      System.out.print("\n-------------------\n");
      System.out.print("Day " + day);           // displaying the day number so the user knows what day it is
      if (day == 1) {                           // if it is the first day      
        System.out.print(" - The Beginning"); // displays unique message for first day
      } else if (day == 30) {                   // if it is the 30th (final) day
        System.out.print(" - The End");       // displays unique message for final day
      }
      System.out.print(" - Hunger: " + hunger); // displaying the user's hunger level
      System.out.print("\n-------------------\n");    // printing a break line and newline characters for formatting

      System.out.print("a. Farm\nb. Business\nc. Rest\nd. Help\ne. Eat\nf. Exit\n");      // displaying the possible Actions the user can perform
      System.out.print("What would you like to do: ");        // prompting the user to select an Action
      response = input.nextLine();                            // receiving the user's input for choice of Action
      System.out.println("You have chosen " + response); // temp temp temp temp temp temp
      // A: - Help, provide information for each Action
      if (response.equalsIgnoreCase("d")) {                   // if the user wants help
        System.out.print("\n---------\nHelp\n---------\na. Farm\nb. Business\nc. Rest\nd. Hunger/Nutrition Level\nr. Return to menu");         // prompting the user for what part they want Help with
        do {                    // running a while loop until the user no longer needs help
          System.out.print("\nWhat would you like help with: ");  // prompting the user for what they need help with
          response = input.nextLine();                          // receiving the user's input
          System.out.print("\n");                             // printing a newlinw for formatting
          if (response.equalsIgnoreCase("a")) {                 // if the user wants help with farm
            System.out.println("---------\nFarm\n---------\n");    // title formatting for farm information
            System.out.println("As a farmer, you own a 3x3 grid of farmland. This farm starts off with spaces of land with initial values of 1. Inside each space, there will be a numerical value. This value indicates the weight/worth of that particular crop and new crops will start as a value of 1. Your crops will grow by a value of 1 each day. However, crops have a small chance to instantly go bad and be rendered as waste, leaving the space empty and resetting the crop's value to 0 (empty).\n\nFor an Action, you can choose between tending to, fertilizing, or harvesting your crops. Tending to your crops gives a +1 bonus for the growth value for that day. In order to fertilize crops, you must pay $50 your crops. By fertilizing crops, the value growth is multiplied by 2 until harvested. You cannot fertilize the same crops more than once.\n\nIf you wish to harvest your crops, all your crops (the sum of their values) will be added to your storage and the values of all crops will return to 0. You can then choose how many rows of crop to plant back, each row costing $25.");     // giving the user information about the farming mechanic
          } else if (response.equalsIgnoreCase("b")) {          // if the user wants help with business
            System.out.println("---------\nBusiness\n---------\n"); // title formatting for business information
            System.out.println("You can check your balance in the business menu. By indulging in business, you will be able to select 1 out of the 3 people to talk to. Each person has their own crop/$ trade ratio. Some people may have better ratios than others, though that doesn't mean you should just stick to one person.");                      // giving the user information about the farming mechanic
            if (virus) {                                        // if the virus exists in this simulation runthrough
              System.out.println("\nConsidering that there is a viral outbreak, you will have to watch out for people who may have higher chances of carrying this disease. It turns out that the people with better deals are confronted more often, meaning more chance of catching the virus from speaking with them. If you happen to catch the virus, you will not be able to work on the farm until you have recovered."); // telling the user information about how the virus can spread from these people
            }
          } else if (response.equalsIgnoreCase("c")) {          // if the user wants help with rest
            System.out.println("---------\nResting\n---------\n");  // title formatting for resting information
            System.out.println("If you do not wish to do any other actions, you can choose to rest for the day. If you have contracted the virus, resting gives you a better chance of recovering.");      // telling the user information about how resting works
          } else if (response.equalsIgnoreCase("d")) {          // if the user wants help with hunger
            System.out.println("---------\nHunger Level\n---------\n"); // title formatting for the hunger information
            System.out.println("You will have a level of hunger they must maintain. If this level were to ever meet 0, the simulation ends and you 'starve'. You can eat before performing any time-consuming Actions. The max hunger level you can reach is 100. You can choose to eat your own crops, eating 1 crop will give you 3 hunger. *The purpose of this is to show the difficulty of facing hunger. The growth rate of crops and eating rate of this farmer and his family may not accurately reflect those that are in the real world.\n\nHere is the hunger per action:\n- Tending crops  : 0\n- Fertilizing    : 15\n- Planting       : 5 per row\n- Harvesting     : 15\n- Doing business : 10\n- Resting        : 5");                       // telling the user information about how the hunger level works and short inaccuracy disclaimer
          } else if (response.equalsIgnoreCase("r")) {     // if the user wants to leave the help menu
            System.out.println("Returning to menu.");      // short message telling the user they left the help menu
            continue;   // resetting the day
          } else {                                              // if the user entered an invalid input
            System.out.println("You did not enter a valid input.");   // telling the user they did not enter a valid input
          }
        }
        while (!response.equalsIgnoreCase("r"));      // while they do not want to exit the help menu
      continue;     // makes sure that the loop does not continue this iteration and starts a new one
      
      // A: - farm, while loop, ask if they want to plant, fertilize or harvest crops, cannot change to another action
      }
      if (response.equalsIgnoreCase("a")) {           // if the user chose to tend to the farm
        System.out.print("\n---------\nFarm\n---------\n");  // title formatting for farm menu
          System.out.print("a. Check farm\nb. Check crop storage\nc. Plant crops\nd. Tend to crops\ne. Fertilize crops\nf. Harvest crops\nr. Return to menu\nPlease enter make a selection: ");      // displaying the options the user has for actions
        response = input.nextLine();              // receiving the user's input
        while (response.equalsIgnoreCase("a") || response.equalsIgnoreCase("b")) {  // while the user wants to check their farm or storage
          if (response.equalsIgnoreCase("a")) {
            showFarm(farm);                         // shows the user their farm
          } else {
            System.out.println("\nYou walk over to your crop storage to check how it's doing.\n\nCrops: " + cropStorage); // output to the user telling them how much crop they have stored
          }
          System.out.print("\n---------\nFarm\n---------\n");  // displaying title formatting for farm menu again
          System.out.print("a. Check farm\nb. Check crop storage\nc. Plant crops\nd. Tend to crops\ne. Fertilize crops\nf. Harvest crops\nr. Return to menu\nPlease enter make a selection: ");      // displaying the options the user has for actions again
          response = input.nextLine(); // receiving the user's input again
        }
        if (response.equalsIgnoreCase("r")) {   // if the user wants to return to the menu
          System.out.println("Returning to menu.");   // short message telling the user they left the help menu
          continue; // makes sure that the loop does not continue this iteration and starts a new one
        } else {      // if the user entered an invalid input
          if (!caughtVirus) {
            if (response.equalsIgnoreCase("c")) {         // if the user wants to plant more crops  
              if (money >= 25) {                          // if the user has enough money to plant one row
                plantCrops(farm);                           // letting the user plant crops
                continue;                                   // makes sure that the loop does not continue this iteration and starts a new one
              } else {                                      // if the user doesn't have enough money
                System.out.println("\nYou do not have enough money to plant a new row of crops. Sell some of your crops before you try planting crops again.");     // message to the user telling them they don't have enough money to plant
              }
          
            } else if (response.equalsIgnoreCase("d")) {  // if the user wants to tend to their farm
              if (!checkFarmEmpty(farm)) {                      // checks if the farm is empty
                tendFarm(farm);                           // tending to the farm method
              } else {      // if farm is empty
                System.out.println("\nYou cannot tend to crops you haven't planted. Plant some crops before you start tending.");  // message to user telling them they cannot tend any crops
              }
            } else if (response.equalsIgnoreCase("e")) {  // if the user wants to fertilize their farm  
              if (money > 50) {                           // if the user has enough money for fertilizing
                fertilizeFarm(farm);                      // fertilize farm method
              } else {
                System.out.println("\nYou can't afford any fertilizers to feed your crops. Trade with business people to get enough money before you try fertilize your crops again.");             // telling the user they do not have enough money
                continue;       // makes sure that the loop does not continue this iteration and starts a new one
              }
            } else if (response.equalsIgnoreCase("f")) {  // if the user wants to harvest their crops
              harvestFarm(farm);                          // harvest crops method
              System.out.print("\nWould you like to plant new crops? (y/n): ");    // prompting the user to see if they want to replant their crops
              response = input.nextLine();                // receiving the user's input 
              if (response.equalsIgnoreCase("y")) {       // if the user wants to replant
                plantCrops(farm);                         // letting the user plant crops
              } else if (!response.equalsIgnoreCase("n")) {         // if the user enters an inva
                System.out.println("\nYou couldn't make up your mind in time. The idea and decision of replanting was so difficult to put into words that nighttime came around. It is officially nighttime and you head inside to rest until tomorrow...");        // telling the user they did not replant
              }
            } else {
              System.out.println("You did not enter a valid input"); // telling the user they did not enter a valid input
          continue; // makes sure that the loop does not continue this iteration and starts a new one
            }
          } else {
            System.out.println("\nYou feel too exhausted and sick to be doing anything in the farm right now. Maybe you should get some rest...");     // telling the user they can't do farm work because of their virus
            continue; // makes sure that the loop does not continue this iteration and starts a new one, resets the day
          }
        }
        
      // A: - business, while loop, ask who they want to trade with, cannot change to another action
      } else if (response.equalsIgnoreCase("b")) {    // if the user chose to do business
        System.out.print("\n---------\nBusiness\n---------\n");     // title formatting for business menu
        System.out.print("a. Do business\nb. Check wallet\nr. Return to menu\nPlease make a selection: ");      // displaying the options the user has for actions
        response = input.nextLine();        // receiving the user's input
        while (response.equalsIgnoreCase("b")) {    // while loop for looping the business menu
          System.out.printf("\nWallet: $%.2f\n", money);    // displaying the user's balance
          System.out.print("\n---------\nBusiness\n---------\n");     // title formatting for business menu
          System.out.print("a. Do business\nb. Check wallet\nr. Return to menu\nPlease make a selection: ");      // displaying the options the user has for actions again
          response = input.nextLine();        // receiving the user's input
        }
        if (response.equalsIgnoreCase("a")){
          if (cropStorage >= 1) {              // if they have crops to sell
            business();      // business method
          } else {          // if the user does not have enough crop to sell at the market
          System.out.println("\nYou do not have enough crop to bring with you to the market. You can't sell crops you don't have. Come back with at least 1 crop in your storage before trying to do business.");        // telling the user they don't have enough crops to sell at the market
          continue;   // makes sure that the loop does not continue this iteration and starts a new one
          }
        } else if (response.equalsIgnoreCase("r")) {
          System.out.println("\nReturning to menu...");     // telling the user they are returning to the main menu
          continue;   // makes sure that the loop does not continue this iteration and starts a new one
        } else {        // if the user entered an invalid input
          System.out.println("You did not enter a valid input");  // telling the user they did not enter a valid input
        continue; // makes sure that the loop does not continue this iteration and starts a new one
        }
        
      } else if (response.equalsIgnoreCase("c")) {    // if the user chose to rest
        rest();          // rest method
      } else if (response.equalsIgnoreCase("e")) {    // if the user chose to eat
        eat();  
        continue;  // makes sure that the loop does not continue this iteration and starts a new one
      } else if (response.equalsIgnoreCase("f")) {    // if the user wanted to exit the simulation
        System.out.println("\nYou feel your vision starts to slowly fade into darkness.\n\nYou wake up in what appears to be a laboratory with technology too advanced your brain hurts by looking at it.\n\nA little after waking up, you hear a robotic voice stating, \"The simulation has been terminated.\"");         // exit message for the user
        break;          // breaking out of the simulation loop
      } else {      // if the user entered an invalid input
        System.out.println("You did not enter a valid input");  // telling the user they did not enter a valid input
        continue; // makes sure that the loop does not continue this iteration and starts a new one
      }

      // A: - add value to crops and day number
      endDay(farm);
      }
    if (alive) {          // if the user survived throught the simulation
      System.out.println("\nCongratulations! You survived through a hard 30 days of living as a farmer. That wasn't too hard right?");      // congratulatory message
      if (hunger > 80 && money > 100) {
        System.out.println("\nYou finished off the 30 days pretty good. You get an A.");    // congratulatory message for user
      } else if (hunger > 60 && money > 75) {
        System.out.println("\nYou did pretty decent. Could've been better, but well done. B+");   // ending message for user
      } else if (hunger > 25 && money > 25) {
        System.out.println("\nI mean, you survived. Good job. C+");    // ending message for user
      } else if (hunger == 5 && money == 0) {
        System.out.println("\nYou barely made it out of this alive. On the dawn of the 31st day, you crawl out of your home with pride over your face. You have passed the simulation.");    // ending message for the user
      }
      // checking the user has received any achievements
      if (hunger == 100 && money > 1000) {            // successful ending
        System.out.println("\nBy the end of the 30 days, your stomach was just as full as your wallet. You have achieved the \"Godlike Survivor\" title.");     // achievement for user for having a very good run
        achievementsGotten += 1;      // adding one to the gotten achievements
      } 
      if (totalCropsHarvested > 500) {          // if they have harvested over 500 crops
        System.out.println("\nYou grew over 500 crops in just 30 days! You have achieved the \"Godlike Farmer\" title.");   // congratulatory message
        achievementsGotten += 1;      // adding one to the gotten achievements
      }
      if (checkTraded(tradedTrader)){       // if the user traded with every trader
        System.out.println("\nYou traded with everyone you could. You built so many networks and your name as a farmer is known across the lands. You have achieved the \"Godlike Networker\" title.");        // congratulatory message
        achievementsGotten += 1;      // adding one to the gotten achievements
      }
      if (recoveredVirus) {                 // if the user recovered from the virus
        System.out.println("\nYou managed to overcome the nuisance of the virus from the business people. You have achieved the \"Godlike Immune System\" title.");
        achievementsGotten += 1;      // adding one to the gotten achievements
      }
      if (timesRested >= 10) {              // if the user rested more than 10 times in one runthrough
        System.out.println("\nAs a farmer, you loved your bed more than your own crops. You have achieved the \"Godlike Sleepyhead\" title...");
        achievementsGotten += 1;      // adding one to the gotten achievements
      }
      System.out.printf("\nYou got %d/%d of the possible achievements. ", achievementsGotten, achievements);         // telling the user how many achievements they got
      if (achievementsGotten == 0) {
        System.out.print("Hm. It seems you didn't get any achievements this runthrough. Maybe you can find one if you play again?");    // telling the user they didn't get any achievements
      } else if (achievementsGotten == achievements) {    // if the user got all the achievements
        System.out.print("You really managed to get all the achievements, huh. Nice job kiddo. This is it. This is the true ending. Hope you had fun. :)");     // congratulatory message for getting all the achievements
      } else {                                            // if the user has at least 1 achievement
        System.out.print("Nice job with the achievements, but you still haven't gotten them all. Maybe try being godlike at everything...?");   // telling the user they still have some achievements to go
      }
    }
  }

  public static void main(String[] args) {
    /*
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
    */ 
    
    do {
      // displaying the start menu of the program, with three options, start simulation and details about the issue, and exit the program.
      System.out.print("Welcome to Hunger Simulator.\na. Run simulation\nb. Details\nc. Exit\nPlease make a selection: ");
      response = input.nextLine(); // receiving the user input and assigning the value to the user input variable
      System.out.print("\n"); // putting a newline character for formatting

      // simulation start menu, while loop thing, option A: start, option B: details, option C: exit
      if (response.equalsIgnoreCase("a")) {
        // start simulation while
        simulation();
      } else if (response.equalsIgnoreCase("b")) {
        // display information and details surrounding the issue and this program
        System.out.println("\nOver the past few decades, the number of people who suffer from hunger has been slowly declining. However, this number has been slowly increasing again in 2015. Additionally, the COVID-19 pandemic could now double that number, causing another 130 million people at risk of suffering hunger by the end of 2020.\n\nThis program will serve as a simulation, simulating the life of a farmer who has a family of 4 that lives in the farmlands of a developing country. As a farmer, you will have to manage your crops, hunger/nutrition level, and business.\n");

      } else if (response.equalsIgnoreCase("c")) {
        // exit the main while loop program
        break;            
      } else {
        // prompt for a valid value
        System.out.println("Please enter a valid input: ");   // prompting the user to enter a different variable
      }
    } while (!(response.equalsIgnoreCase("c"))); 
  }
} 