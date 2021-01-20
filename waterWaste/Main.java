import java.util.Scanner; // importing Scanner for user input
// import java.util.concurrent.TimeUnit; // import time delay package
class Main {
  public static void main(String[] args) throws InterruptedException {
    /*
    The purpose of this program is to spread awareness of excessive water usage to the user. This will be done by telling and visually showering the user how much water they use when they take a shower. 
    The program will prompt the user for how many minutes they shower for. If the user does not enter a positive number, a while loop will be used to continue asking them for a valid value. By multiplying the number of minutes by the average number of litres of water used in a shower, the program will calculate the number of litres used by the user in a shower.
    When the number of litres has been calculated, a for loop will be used to display a number of symbols to represent how many litres the user uses in a shower.
    Towards the end, there will be an if statement that first determines if the user showers for the recommended time. If they do not, the excess minutes in the shower and wasted water of the excess time will be calculated by subtracting 5 from the time and multiplying that time by the same number of litres used in the shower per minute respectively.
    The next message to the user will vary depending on if they shower for 1 excess minute,
    up to 10 excess minutes, or if they shower for even more than that, with each message getting harsher as more water is wasted.
    **********************
    Could add an idea of asking the user how long they plan to live and calculate how much water they'd used in their lifetime's worth of showers
    **********************
    pls do not hate me for trying to exception handle. I just wanted to delay the program, that's it I promise. 
    */

    Scanner minInput = new Scanner ( System.in ); // creating the Scanner object

    int min;  // declaring the number of minutes the user showers for
    int litres; // declaring the number of litres the user uses in a shower
    int litresPerMin = 8; // initializing the litres used by a shower per minute

    System.out.print("Please enter the average number of minutes you shower for: "); // prompting the user for the number of minutes they shower for

    min = minInput.nextInt(); // assigning the input value 

    // running a while loop if user enters an invalid value (negative time)
    while (min < 0) { 
      System.out.print("Please enter a positive integer: "); // telling the user to enter a valid input 
      min = minInput.nextInt(); // asking the user for another input
    }

    litres = min * litresPerMin; // calculating the number of litres used based on minutes and litres/min

    System.out.println("\nYou use " + litres + " litres of water in a single shower.\n"); // telling the user the calculated number of litres they use in a shower

    Thread.sleep(2000); // delaying the program for 2 seconds

    System.out.println("To put that into perspective...\n"); // another message to the user

    Thread.sleep(5000); // delaying the program for 5 seconds

    // looping lines of water drops depending on how long the user showers for, each line represents 8L of water or 1 minute of showering, each drop of water represents 1L of water
    for (int i = 0; i < min; i++){    // looping for each minnute the user showers for
      System.out.println("💧 💧 💧 💧 💧 💧 💧 💧");  // printing the water drops
    }

    System.out.println("Each drop of water here represents 1L of water that you use in a shower.\n"); // telling the user that the drops represents L of water

    Thread.sleep(2000); // delaying the program 

    // checks and tells the user how many excessive minutes they shower for and how much water that wastes
    if ( min == 0 ) { // if they do not shower
      System.out.println("Not showering is quite unhealthy. At least you're not using water..."); // message to user regarding their number of minutes they shower for and water usage

    } else if ( min <= 5 ) { // if they shower for 5 or less minutes
      System.out.println("You showered in a good amount of time! You did not waste much water."); // congratulatory message

    } else { // if they over-shower
      int excessMin = min - 5;  // excess minutes they shower for
      int wastedWater = excessMin * 8; // water wasted by over-showering

      if ( excessMin == 1 ){ // if they over-shower for one excess minute
        System.out.println("You take a lot of time in the shower. You shower for " + excessMin + " excessive minute. You waste a little of " + wastedWater + "L of water every shower."); // how much they over-shower and how much water they waste
        
      } else if ( excessMin <= 10 ) { // if they over-shower a little (between 1 to 10 minutes)
        System.out.println("You take a lot of time in the shower. You shower for " + excessMin + " excessive minutes. You waste " + wastedWater + "L of water every shower."); // how much they over-shower and how much water they waste

      } else { // if they over-shower a lot (over 10 minutes)
        System.out.println("You take TOO much time in the shower. Imagine how much water you waste. Wait no, don't imagine. You waste " + wastedWater + "L of water. Now ain't that a bit much?"); // how much they over-shower and how much water they waste
      }
    }
  }
}