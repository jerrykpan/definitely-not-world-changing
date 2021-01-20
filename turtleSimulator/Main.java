/**
* This program will allow the user to run a simulation where they play as a turtle
* trying to survive through the increasingly difficult climate conditions that arise
* as they advance. 
*
* @author Anson Huang & Jerry Pan
* @version 8.0
* @since 2020-10-07
*
*/

import java.util.Scanner;     // importing the Scanner class
import java.util.Random;      // importing the Random class

class Main {
  static Scanner input = new Scanner( System.in );      // creating a global Scanner object
  static Random rand = new Random();                    // creating a global Random object
  static int hp = 100;                                  // creating the global Health Points variable
  static int yearCounter = 0;                           // creating a counter for years passed
  // Region A: Asia, Region B: North America
  static String region = "B";                           // creating a variable for the region the turtle is in
  static int eggsAlive = 0;                             // creating a counter for how many eggs are alive
  static int hatchlingsCount = 0;                       // creating a counter for how many hatchlings hatched successfully for one breeding term
  static int totalHatchlings = 0;                       // creating a counter for total number of hatchlings
  static int hurricaneDmg = 40;                         // variable for how much damage the hurricane deals
  static int tsunamiDmg = 30;                           // variable for how much damage the tsunami deals
  static int layEggCooldown;                            // setting the cooldown variable for laying eggs
  static String response;              // creates the variable for holding the user's choice


/*
MAIN METHOD

This is the main method ran by the compiler.

Inside this method is the main menu the user will see. They will make a choice of 3 given options. Run the simulation, details about the global issue, or exit the program.
*/
  public static void main(String[] args) {
    do {
      System.out.print("\nWelcome to Turtle Simulator\na. Run simulation\nb. Details about the issue\ne. Exit\nPlease make a selection: ");         // presenting the start menu to the user
      response = input.nextLine();  // read user input
      if (response.equalsIgnoreCase("a")){              // if the user selects the simulation opption
        simulation();                                   // run simulation
      } 
      else if (response.equalsIgnoreCase("b")) {        // if the user selects the details option
        aftermath();   // present details
      } else if (!(response.equalsIgnoreCase("e"))) {   // if they entered an invalid input
        System.out.println("Invalid input.");                     // tell the user they entered an invalid input
      }
    } while (!(response.equalsIgnoreCase("e")));
    System.out.println("Farewell.");                          // wishing the user goodbye 
  }

  /**
  * This method is used to output a message telling turtle how much damage it has taken. 
  * The method will take in 2 parameters, the damage inflicted on the turtle and the type
  * of natural disaster. Then, the method outputs a string saying the turtle has taken
  * the indicated amount of damage due to the respective natural disaster. 
  *
  * @param dmg        This is the first parameter of the showDamage method. It stores an int showing how much damage the turtle has taken from the most recent natural disaster.
  * @param disaster   This is the second parameter of the showDamage method. It stores a string which indicates the most natural disaster that has damaged the turtle.
  * @author           Anson Huang
  */
  public static String showDamage(int dmg, String disaster) {
    String output = "\nYou took " + dmg + " damage from " + disaster + ".";  // statement of telling user they how much damage they have taken.
    return output;                                                          // return statement
  }
  
  /**
  * This method is used to calculate the damage the user takes from a certain source and output an appropriate message 
  * based on the damage source. It also calls on the showDamage() method to display an appropriate message to 
  * the user. If the user has died from this damage, it will display an appropriate death message, based on the 
  * cause of death.
  *
  * @param dmg      The first parameter is the amount of damage that the user will receive in health points.
  * @param source   The second parameter is the source of where the damage is coming from
  * @author Jerry Pan
  */


  public static void calcDmg(int dmg, String source) {
    hp -= dmg;                      // subtracting the damage from the user's health points
    System.out.println(showDamage(dmg, source));        // displaying the damage the user has taken
    if (hp < 0) {                   // if the user has no more health
      System.out.print(" You have died from " + source);   // telling the user what they have died from the source
    }
  }

  /**
  * This method is used to determine if the user gets attacked by predator. If the attack
  * succeeds, then it calculates the damage the user takes in health points. This chance  
  * lowers for every year that the turtle lives because of worse climate conditions. Since,
  * living conditions will be worse, there will be less predators on the hunt for the user.
  * If the user dies (resulting health points is 0 or less), then the program prints a message telling
  * the user that they have died. This method has no parameters.
  *
  * @author Jerry Pan
  */
  public static void predator() {
    double predatorChanceScalingFactor = 0.5 * yearCounter; // determining how much the chance of the predator attack decreases by
    double predatorChance = 25 - predatorChanceScalingFactor; // subtracting that decreased chance from the base chance
    int predatorRegionAExtraChance = 5;                 // extra chance to run into predators in region Actions
    if (region.equals("a")) {
      predatorChance += predatorRegionAExtraChance;      // adds the extra chance of running into a predator in Region A
    }

    if (hp > 0) {                                             // if the user is still alive  
      if (rand.nextInt(100) < predatorChance) {               // if the attack suceeds
        hp = 0;                                               // sets the user's health points to zero
        System.out.print("\nYou have been eaten by a predator. You have died."); // printing death message
      }
    }
  }
  
  /**
  * This method is used to determine if a tsunami occurs at a given instance/year. 
  * It calculates the chance of a tsunami happening by using an RNG with a scaling 
  * factor modifier. The scaling factor is determined based on years passed because every
  * year, the global temperature continues to rise which means natural disasters are more
  * likely to happen. The method accounts for an extra chance (higher chance) for a tsunami
  * to happen in Region B. If a tsunami has happened, the turtle will be inflicted damage on
  * its health points. It then calls upon the showDamage() method to output a message
  * telling the user the turtle has taken damage. 
  * This method has no parameters and does not return anything. 
  *
  * @author Anson Huang
  */
  public static void tsunami() {
    double tsunamiChanceScalingFactor = 0.00125 * yearCounter;          // calculate scaling factor of the chance of a tsunami happening (based on climate)
    double tsunamiCurrentChance = 5 + tsunamiChanceScalingFactor;       // calculate chance of a tsunami happening (based on scaling factor)
    int tsunamiRegionBExtraChance = 5;                                  // extra chance of tsunami happening
    if (region.equalsIgnoreCase("b")){
      tsunamiCurrentChance += tsunamiRegionBExtraChance;                // extra chance of tsunami happening in Region B
    }
    if (hp > 0){                                                        // if turtle is alive...
      if (rand.nextInt(100) < tsunamiCurrentChance) {                   // use RNG to determine if tsunami happens
        calcDmg(tsunamiDmg,"tsunami");                                  // reduce hp by tsunami's damage
        migrate("tsunami");                                             // forcing the user to migrate
      }
    }
  }
  
  /**
  * This method is used to determine if an earthquake occurs at a given instance/year.
  * It calculates the chance of an earthquake happening by using an RNG with a scaling factor modifier. 
  * This scaling modifier increases over the years due to rapidly changing global climate.
  * If it happens, all eggs currently laid are no more; shattered shells. 
  * This method has no parameters and does not return anything.
  *
  * @author Anson Huang  
  */
  public static void earthquake() {
    double earthquakeChanceScalingFactor = 0.00375 * yearCounter;           // calculate scaling factor of the chance of an earthquake (based on climate)
    double earthquakeCurrentChance = 10 + earthquakeChanceScalingFactor;   // calculate chance of an earthquake happening (based on scaling factor)
    if (rand.nextInt(100) < earthquakeCurrentChance) {                      // use RNG to determine if earthquake happens
      System.out.println("Oh No! A massive earthquake has happened and unfortunately, all your unhatched eggs have died.");
      eggsAlive = 0;                                                        // no eggs survive earthquake
    } 
  }  
  
  /**
  * This method is used to determine if an underwater volcanic eruption occurs at 
  * a given instance/year. If it does happen, it instantly obliterates turtle from 
  * face of planet. This method also checks if turtle is alive prior to explosion.
  * Chances of dying are low, but never zero. This method has no parameters 
  * and does not return anything.
  *
  * @author Anson Huang  
  */
  public static void volcanoEruption() {
    double VolcanicEruptionChance = 1;                                      // initialize chance of underwater volcanic eruption
    if (hp > 0){
      if (rand.nextInt(100) < VolcanicEruptionChance) {                       // use RNG to determine if underwater volcanic eruption happens
        hp = 0;                                                               // turtle is dead if underwater volcanic eruption happens
        System.out.println("Oh no, a massive underwater volcanic eruption occured, and you along with 1/4 of the residents of the Pacific Ocean have been obliterated.");
      }
    }
  }  
  /**
  * This method is used to determine if a hurricane occurs during this iteration/year. The chance of a hurricane 
  * increases with every year that comes because of worsening climate conditions. As more water vapour is evaporated
  * into the atmosphere, this allows more powerful disasters to arise. The chance of getting hit by a hurricane
  * is greater in region A. If the user gets caught in the hurricane (% chance), then the program will call the 
  * calcDmg() method to calculate the damage dealt.
  * This method has no parameters.
  *
  * @author Jerry Pan
  */
  public static void hurricane() {
    double hurricaneChanceScalingFactor = 0.00625 * yearCounter;  // determining how much the chance of the hurricane increases by (due to climate conditions)
    double hurricaneChance = 5 + hurricaneChanceScalingFactor;    // adding that increased chance to the base chance
    int hurricaneRegionAExtraChance = 5;                          // extra chance of running into a hurricane
    if (region.equals("a")) {
      hurricaneChance += hurricaneRegionAExtraChance;             // adding the extra chance of hurricane in region A
    }
    if (hp > 0) {                                                 // if the user is alive
      if (rand.nextInt(100) < hurricaneChance) {                  // if the user gets hit by the hurricane
        calcDmg(hurricaneDmg, "hurricane");                       // does all the damage calculation
        migrate("hurricane");                                     // forcing the user to migrate
      }
    }
  }


  /**
  * This method is used when the user selects the option of breeding. The chance of finding a mate decreases with 
  * every year that comes because of worsening climate conditions. As living conditions worsen, the chance of finding
  * a mate will decrease. If the user finds a mate, the program will calculate how many eggs the user will lay 
  * based on the average of 110 eggs per nest and they have 2 to 8 nests per year (number of nests will be determined
  * by RNG). This method has no parameters.
  *
  * @author Jerry Pan
  */
  public static void breed() {
    double matingChanceScalingFactor = 1.125 * yearCounter;         // determining how much the chance of finding a mate decreases by (due to climate conditions)
    double matingChance = 50 - matingChanceScalingFactor;         // subtracting the decreased chance from the base chance
    int minNumOfNests = 2;                                        // minimum number of nests the user will lay
    int numOfNests = 0;                                           // total number of nests the user will lay this year
    int eggsPerNest = 110;                                        // number of eggs laid per nest
    if (rand.nextInt(100) < matingChance) {                       // determining if the user found a mate
      System.out.print("\nYou managed to find a mate! ");        // telling the user they found a mate
      numOfNests += minNumOfNests + rand.nextInt(7);              // determines how many nests the user will lay
      eggsAlive += numOfNests * eggsPerNest;                      // calculating the number of eggs that were laid
      System.out.printf("\nYou managed to lay %d eggs this year. Just how many will survive...?\n", eggsAlive); // tells the user how many eggs they laid
      layEggCooldown = 2;                                        // adding a cooldown for laying eggs
    } else {
      System.out.print("\nThis was just an unlucky year. No mates to find. Sorry for your loss.\n");  // telling the user they did not find a mate
    }
  }


  /**
  * This method is used a year after the user laid eggs and is used to determine 
  * how many of all the eggs laid hatch successfully (and assume they reach adulthood). 
  * It then outputs a message to the user about the result.
  *
  * @author Anson Huang 
  */
  public static void determineIfHatchEgg() {
    int hatchChance = 1;                        // chance of successful hatch
    hatchlingsCount = 0;                        // resetting the value of this counter
    for (int i = 0; i < eggsAlive; i++) {        // check each egg see if hatch
      if (rand.nextInt(100) < hatchChance) {    // use RNG to check if the egg hatches successfully
        hatchlingsCount += 1;                   // add 1 to count of successful hatches
      }
    }
    eggsAlive = 0;                              // resetting the counter for number of eggs laid
  }


  /**
  * This method is used when the user wants to migrate to the other region. 
  * If turtle is in Region A, they will now go to Region B, and vice versa.
  * This method has no parameters.
  *
  * @author Anson Huang
  */
  public static void migrate() {
    System.out.println("\nAlong the way, you gaze upon beautiful scenery of what makes the ocean. However, over the years, you've noticed this beauty degrade over time from climate. Oh how sad...");  // telling the user they migrated
    if (region.equalsIgnoreCase("a")) {   // check if currently turtle is in Region A
      region = "b";                       // if turtle is in Region A, change region to B
    } else {                              // if turtle is not in region A (currently in Region B)
      region = "a";                       // change egion to A.
    }
    volcanoEruption();                    // roll for chance of volcanic eruption.
  }


  /**
  * This is an overloaded version of the migrate() method (no parameters).
  * This method is used when a disaster forces the turtle (user) to move to another region. This is done by calling 
  * the migrate() (no parameters) method to change the region the user is in. The user has a chance to still get caught by 
  * the disaster as they are fleeing from it. This is done by using RNG to determine if the calcDmg method gets called
  * for that particular disaster (to avoid stacking chances). The String parameter would signify what disaster the turtle is 
  * fleeing from and an if statement is used to check this parameter.
  * to call the appropriate method.
  *
  * @param The type of disaster the turtle is migrating from
  * @author Jerry Pan
  */
  public static void migrate(String disaster) {
    int disasterAftermathChance = 10;       // the chance of the user getting caught in the aftermath of the disaster
    // changing the user's region
    migrate();
    System.out.println("\nDue to an incoming natural disaster, you attempt to flee this region safely and head to the other region.");  // message to the user telling them they were forced to flee
    // determining if the user gets caught in the aftermath of the disaster
    if (rand.nextInt(100) < disasterAftermathChance) {      // if the chance happens
      System.out.print("\nIn the attempt to escape the disaster happening in this region, you still wounded up getting hit by it.\n");  // telling the user they still got hit by the disaster
      if (disaster.equalsIgnoreCase("tsunami")) {           // if the disaster was a tsunami
        calcDmg(tsunamiDmg, "tsunami");                     // calculating tsunami damage
      } else if (disaster.equalsIgnoreCase("hurricane")) {  // if the disaster was a hurricane
        calcDmg(hurricaneDmg, "hurricane");                 // calculate if they get hit by the hurricane
      }
    }
  }
  

  /**
  * This method determines if the user has found sufficient levels of food during this year. The chance of finding
  * sufficient food for the year decreases because of worse climate conditions. As the global temperature rises, 
  * their sources of food (seaweed & algae) become more limited as the plants' environment degrades. There is a slightly 
  * higher chance to find food in region B. If the user does not pass the RNG chance of finding sufficient food, the program 
  * will calculate the damage they take. The damage the user takes is from RNG ranging from 1-6. It then calls the
  * calcDmg() method to calculate the damage dealt to the user.
  *
  * @author Jerry Pan
  */
  public static void findFood() {
    double findFoodChanceScalingFactor = 1.125 * yearCounter;     // determining how much the chance of finding sufficient food decreases by (due to climate change)
    double findFoodChance = 90 - findFoodChanceScalingFactor;   // subtracting the decreased chance from the base chance
    int findFoodRegionBExtraChance = 10;                        // extra chance of finding food in region B
    int minHungerDmg = 1;                                       // the minimum damage the user can take from minHungerDmg
    int hungerDmg = 0;
    if (region.equals("b")) {
      findFoodChance += findFoodRegionBExtraChance;             // adding that extra chance of finding food in region B 
    }
    if (hp > 0) {                                               // if the user has not died yet
      if (!(rand.nextInt(100) < findFoodChance)) {              // determines if the user has NOT found food
        hungerDmg += rand.nextInt(6) + minHungerDmg;            // determines how much hp the user will lose
        System.out.println("\nThis year was a difficult one for food. All the kelp you ate tasted bland and felt nutrition-less in the stomach.\n");    // telling the user they did not find sufficient food
        calcDmg(hungerDmg, "hunger");                           // calculating the damage dealt to the user because of 
      }
    }
  }

  /**
  * This method randomly selects a comical desciption out of a list and prints it out to the user.
  * This comical description depicts what has happened during this year of the turtle's life. This purely
  * serves as entertainment. This is the default action the fish can take if they do not want to migrate or 
  * mate.
  * Descriptions 1-3: Jerry Pan
  * Description 4: Anson Huang
  * @author Jerry Pan and Anson Huang
  */
  public static void live() {
    String [] descriptions = {
      "\nAfter years of long sessions underwater, you realized that you take this sacred liquid known as water for granted. It's done a lot for you, gave you a means of transportation and has quenched your thirst for what seems like infinite times. \n\nThough you have just recognized it, you remembered that water is a precious resource, not only to you, but to all life on the planet. This is why we need to take care of it. Your life continues...\n",       // possible description #1
      "\nEarlier in the year, you suddenly gained the sudden craving for a special type of algae. Though not clearly, you remember one of your friends suggesting that you try this delicious plant for a bite and that it is very rare due to its very specific living requirements. You head out on the hunt for this craving, asking everyone you come across if they had heard of this mystical delicacy. \n\nAfter months of gathering information and desperate searching, you finally come across a similar looking plant. However, this one looks discolored and it seems like this particular bunch has been dead for a long while. A strange fellow fish sees your curiousity and approaches you. He tells you that this and many more species of algae can no longer survive due to the extreme climate conditions. Enveloped in disappointment, your day has been ruined and you decide to head home for the day. Your life continues...\n",      // possible description #2
      "\nThroughout the year, you notice that you are not seeing as many of your friends and peers as you did last year. You give them the benefit of the doubt, speculating that everyone must have migrated to another region because of harsher climates. You decide to forget about this for the time being\n\nOn a casual stroll around a typically busy current, something along the sea floor catches your eye. As you slowly creep closer to investigate what you're looking at, you realize that this is a corpse of your friends! Panicked, you continue scurrying along the ocean floor, only to discover more of corpses of loved ones.\n\nYou come across a strange fish fellow holding a shovel and appeared to be what seemed like digging a grave. You ask him what in the world has been going on. They explain how the global temperature rises have caused habitats of many fishes to become inhabitable, causing the fish to eventually pass away. With a tear rolling down your gill (despite being underwater), you decide to return home for the day. Your life continues...\n", // possible description #3 
      "\nThis was a special year. You see kelps and corals in places where you have not in previous years. This reminds you of when you were not lonely. You wonder how long you are going to live for, but you don't know. All you know is you must pass on your genetics to the next generation. However, the weather gets worse and worse, both above and underwater. You realize how fragile your life is in comparision to the full wrath of Mother Nature. You swim through the abyss, risking the chance of being eaten, to continue to look for food. \n\nBut then you were disappointed to find nothing. After 25 voyages to the abyss, you get fed up due to not finding literally any food there, so you return back to your resting ground. Your life continues...\n"     // possible description #4
      };
      
    System.out.print(descriptions[rand.nextInt(descriptions.length)]);  // prints a random comical description
  }


  /**
  * This method is used after the turtle has died in the simulation or 
  * if the user chooses understand about the issue of global climate change. 
  * It outputs a message that spreads awareness and reflects about 
  * the journey of the turtle. This message will address climate change as a whole. 
  * This method has no parameters and does not return anything.
  *
  * @author Anson Huang
  */
  public static void aftermath() {
    System.out.println("Turtles, among billions of other living things, are devastated by global climate changes. Entire ecosystems of life can evaporate due to the rise in global temperatures. In this simulation, it demonstrates how slim of a chance a turtle has when it comes to surviving through the extremes of Mother Nature and when the global climate has been altered by a significant factor. The increased chance of major natural disasters also impact humans. It is worth remembering that humans are too part of the global ecosystem. We all live on the same planet. We are just a bunch of slightly superior apes. Hundreds of millions of people will be displaced from their homes, and their lives will be forever changed. \n\n Hopefully, you've learned something from using today's simulation. It is never too late to take action!");
  }

  /**
  * This method is for printing out a selection menu for the user to pick what they want the turtle to do. 
  * This menu displays how years have passed, the user's health points, and the region they are located in.
  * It presents the appropriate options based on the region they are located in. Once the user makes a selection,
  * the method returns that value.
  * 
  * @return response - This is the option the user selected 
  *
  * @author Jerry Pan
  */
  public static String printMenu() {
    System.out.printf("\n%d years since 2010 - HP: %d - Region: ", yearCounter, hp);   // displaying how many years into the simulation they are in
    if (region.equals("a")) {     // if they are in region A
      System.out.print("Asia");   // print the region they're in
    } else {                      // if they are in region B
      System.out.print("North America");  // print the region they're in
    }
    if (layEggCooldown == 1) {
      determineIfHatchEgg();          // determines how many eggs successfully hatched
      totalHatchlings += hatchlingsCount;         // adding to the total sum of hatchlings
      System.out.printf("\n%d of your eggs hatched successfully", hatchlingsCount); // telling the user how many eggs hatched successfully
    }
    System.out.printf("\nTotal successful hatches: %d", totalHatchlings); // printing the total number of hatchlings the user has
    System.out.println("\n---------------------------\n");  // breakline for formatting
    System.out.print("a. Live life\nb. Migrate");       // presenting the standard options
    if (region.equals("a")) {
      System.out.print("\nc. Breed");                     // presenting the breed option, only if they are in region A
    }
    System.out.print("\nPlease make a selection: ");    // prompting the user to select an option
    response = input.nextLine();  // prompting the user for a selection
    return response;    // returning their selection
  }

  /**
  * This method runs the entirety of the simulation. It runs a while loop as long as
  * the user's hp is above 0 and they haven't finished the 40 years of surviving. 
  * It calls the printMenu() method to receive the user's input on what they want to do.
  * Depending on their input, the appropriate method is called.
  *
  * @author Anson Huang & Jerry Pan
  */
  public static void simulation() {
    hp = 100;                       // resetting hp
    region = "b";                   // resetting region
    yearCounter = 0;                // resetting year number
    totalHatchlings = 0;            // resetting total sucessful hatchlings
    hatchlingsCount = 0;            // resetting the number of soon to be successfully hatched eggs
    System.out.println("\nYou are a turtle living in 2010. As humans have polluted this planet with excessive amounts of greenhouse gases, what you once called home is slowly becoming inhabitable. With rising sea levels and global temperatures increasing, each year in this simulation will make it progressively harder to survive. The goal of this simulation is to survive for 40 years and successfully hatch 100 eggs. Good luck, you're gonna need it.\n");     // introductory message to user
    System.out.println("You start in the region of North America, but your natural breeding area is in the southern region of Asia. In order to reach that breeding spot,you will have to migrate. In this region, you will have to try and find a mate.\n"); 
    // continues to loop the simulation while they have not finished or are still alive
    while (yearCounter <= 40 && hp > 0) {
      response = printMenu();       // calling the printMenu() method and also receiving the user's input
      if (region.equals("a")) {                 // if the user is in region A
        if (response.equalsIgnoreCase("c")) {   // if the user chose to breed
          if (layEggCooldown == 0) {            // if they haven't laid eggs in 2 years
            breed();                            // call the breed method
          } else {                              // if they have laid eggs recently
            System.out.println("You have already breeded in the past 2 years. You cannot breed.");     // telling the user about the cooldown on laying eggs
            continue;     // resetting the iteration (for redisplaying the menu)
          }
        }
      }
      if (response.equalsIgnoreCase("a")) {   // if the user chose to live life
        live();                               // run the live() method
      } else if (response.equalsIgnoreCase("b")) {    // if the user chose to migrate
        migrate();                                    // run the migrate method
      } else if ((!response.equalsIgnoreCase("a") && !response.equalsIgnoreCase("b") && region.equals("b"))) {    // if they pick an unavailable option
        System.out.println("Please enter a valid input:");  // telling the user to input a valid value
        continue;  
      }

      // checking for each natural disaster/event
      earthquake();        // checking if earthquake occurs
      tsunami();           // checking if tsunami occurs
      hurricane();         // checking if hurricane occurs
      predator();          // checking if predator occurs
      volcanoEruption();      // checking if volcanic eruption occurs
      yearCounter += 1;                 // advances time, adds 1 to count of years
      if (layEggCooldown > 0) {         // if laying eggs is on cooldown
        layEggCooldown -= 1;            // reduce cooldown by 1
      }
    }
  }
}


  