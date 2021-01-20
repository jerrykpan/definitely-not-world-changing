import java.util.Scanner; // importing the Scanner class
class Main {
  public static void main(String[] args) {
    /*
    The purpose of this program is to address the issue of poverty around the world, 
    particularly in developing countries. By describing the lifestyles of families
    suffering from poverty, this would ideally spread the awareness of the poverty issue
    to the everyday person and possibly catalyze some action/help by people and governments.

    This program will act as a mini version of the Dollar Street and start with a 'Home' menu, where the user can choose from a selection of 3 families. The user will use their
    keyboard to enter their selection or exit the program.

    The user then enters an 'Aspect' menu and chooses an aspect of the family's lifestyle such as food, housing,clothing, and access to water. After receiving the user's input, the program will provide a description for whatever aspect the user chose. Once the user is finished reading about that aspect, they can return to the 'Aspect' menu and choose another one to look at.
    
    Once the user is done with looking at a family, the user can return to the 'Home' menu,
    where they can pick another family to look at. The user can exit the program from the
    'Home' menu if they enter "exit".
    ------------------------------------------------------------------
    Notes:
    - Can add a small quiz about the general poverty issue and how the pandemic has 
      affected the issue
    - Can add info on electricity in lighting info
    - Can add Thread.sleep() to delay program
    - Choose whether or not to loop Aspect Menu

    - Butoyi: https://www.gapminder.org/dollar-street/families/butoyi
    - Mubaiwa: https://www.gapminder.org/dollar-street/families/mubaiwa
    - Vanto: https://www.gapminder.org/dollar-street/families/vanto
    ------------------------------------------------------------------
    */

    String response = ""; // initializing the user's response variable
    Scanner input = new Scanner ( System.in ); // creating the Scanner object
    int familyNum; // declaring the variable that will hold the family number the user is looking at

    // initializing each array to hold information on a certain aspect of all the families

    // information about the bathroom of each family, stored in a list
    String [] bathroomInfo = {"They use a hole in the ground for defecating. The most they have for walls around is bushes/hedges and short trees.\n", "This family uses the surrounding forest/nature for open defecation. For walls, there is only the trees and bushes around for privacy.\n", "This family shares a wooden outhouse for defecation with 100 other households. The overall condition of the outhouse is not anywhere perfect, with its door missing all of the doorknob components. The inside of the outhouse is not shown.\n"};

    // information about the bed of each family, stored in a list
    String [] bedInfo = {"The bed of this family is made from what looks like hay. They have a singular bed sheet. In the photo, there are many clothes and/or towels laying on the bed.\n", "This family has no proper bed. The photo simply shows the floor of their home, along with tools around the walls. The floor appears to be dusty and not clean.\n", "This family seems to have a proper bed. A bedframe, several blankets and pillows as well as a teddy bear are all visible within the photo.\n"};

    // information about the house of each family, stored in a list
    String [] houseInfo = {"The walls of this family’s home appears to be made of dirt. The home has square holes in the walls for windows. The roof looks like it is made from tree leaves. The door to this home does not appear to be visible.\n", "This family’s home is made out of bricks. This home has two wooden doors and used to have glass windows. The windows also have sheets of cloth for curtains. The roof is comprised of a huge metal sheet.\n", "This home is comprised of metal sheets stuck together to form walls. The door to the house also seems to be made of metal. There is also a small glass window by the door. The home itself seems to be crammed with a lot of other houses nearby.\n"};

    // information about the lighting in each family's home, stored in a list
    String [] lightingInfo = {"In the kitchen area of this home, this family uses a fire to keep the light the room. There doesn’t appear to be any other sources of light such as candles and bare light bulbs.\n", "This family uses a candle to light their kitchen area. There appears to be multiple wicks resting on the candle stand, possiblying indicating how much this family reused this candle stand.\n", "This family uses what looks like a glass light lamp. Low to middle income homes use bare light bulbs to light up their homes.\n"};

    // information about the shoes each family, stored in a list
    String [] shoeInfo = {"This family owns two pairs of footwear. One being a black pair of shoes and the other being a pair of flip flops. Both pairs seem to be quite covered in dirt.\n", "This family owns a pair of flip flops. This pair seems to be decently clean, with a few dirty spots.\n", "This family owns a pair of shoes. This pair seems to be decently clean, with dirt spots around the upper of the shoe.\n"};

    // information about the water each family gets, stored in a list
    String [] waterInfo = {"This family gets their water by filling up a plastic jug. The water they receive comes out of a pipe that pokes out of a wall. The photo seems to show that there is a level of water on the floor, possibly indicating that not all the water from the pipe is collected. It takes this family 40 minutes to collect water.\n", "Just like many rural poor communities, this family has to walk their water source. To collect the water, they use a bucket they carry on their head on the way back home. The water source is not shown in the picture, but it takes the family 30 minutes to collect this water, which is not even safe to drink.\n", "Just like many rural poor communities, this family has to walk to their water source. To collect the water, they use a bucket that they carry on their head on the way back home. The water source is not shown in the picture. The family spends 2.5 hours a week collecting water.\n"};
    
    // information about the poverty issue and where this information comes from, stored in a list
    String [] detailInfo = {
      "In the last few decades, the percentage of people living in extreme poverty has decreased by 26% from 1990 to 2015. However, the COVID-19 pandemic and its aftermath risks reverting this progress.\n", // details surrounding the poverty issue
      "By allowing people to see the perspectives of these peoples' lives, this will help spread awareness of this urgent issue and ideally bring it into the light of governments in order to slowly solve this problem.\n", // call to action/purpose of program
      "All the information in this program comes from the photos and descriptions from Dollar Street (https://www.gapminder.org/dollar-street/) of the Gapminder organization. If you would like to view more about each family, here are their respective web links.\n", // message about where this information is coming from 
      "Butoyi: https://www.gapminder.org/dollar-street/families/butoyi\n", // link to family 1
      "Mubaiwa: https://www.gapminder.org/dollar-street/families/mubaiwa\n", // link to family 2
      "Vanto: https://www.gapminder.org/dollar-street/families/vanto\n" // link to family 3
      }; 

    // Instructions for the user so they know what the program does and how to use it
    System.out.println("Welcome to Buck Road. To get a better perspective on poverty, you can see how these three families live their lives. By choosing a family, you will then be able to read about an aspect of their lifestyle. If you wish to look at another family, you can return to the Home Menu.\n");
    
    // This while loop is used as the main program ('Home' menu) where the user will make a keyboard input to make their menu selection 
    while (!(response.equalsIgnoreCase("e"))) {   // while the user does not want to exit the program
      // 'Home' menu output
      System.out.println("~~~~~~~~~~~~ Home Menu ~~~~~~~~~~~~\n"); // printing the 'Home' menu title
      
      // Displaying the options the user can select from
      System.out.println("a. The Buyoti Family - Burundi - Monthly Income: $27"); // Family #1 option
      System.out.println("b. The Mubaiwa Family - Zimbabwe - Monthly Income: $74"); // Family #2 option
      System.out.println("c. The Vanto Family - South Africa - Monthly Income: $149"); // Family #3 option
      System.out.println("d. Details"); // extra information for the user regarding the poverty issue
      System.out.println("e. Exit"); // option for exiting the program
      System.out.print("Please make a selection (enter a letter): "); // prompting the user for a selection
      response = input.nextLine(); // reading the user's input for their selection
      System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"); // creating a line for formatting

      // checking the user's selection and providing the appropriate output/processing
      if (response.equalsIgnoreCase("e")) {   // checking to see if the user wants to exit the program
        System.out.println("Exiting the Home Menu."); // displays exiting message
        break;            // exits the while loop program
      } else if (response.equalsIgnoreCase("d")) {    // checking to see if user wants to see details
        for (int i = 0; i < detailInfo.length; i++) {
          System.out.println(detailInfo[i]); // displaying each piece of information in the detailInfo list
        }
      } else {
        // checking to see which family the user wants to look at
        if (response.equalsIgnoreCase("a")) { // if the user chooses family 1
          familyNum = 0;                        // assigning the family number as 0
        } else if (response.equalsIgnoreCase("b")) { // if the user chooses family 2
          familyNum = 1;                        // assigning the family number as 1
        } else if (response.equalsIgnoreCase("c")) { // if the user chooses family 3
          familyNum = 2;                        // assigning the family number 2
        } else {                                  // if the user entered something else
          System.out.println("Invalid input."); // tell user they entered invalid input
          continue;
        }

        // This while loop is used as the 'Aspect' menu where the user will make a keyboard input to make their menu selection
        while (!(response.equalsIgnoreCase("r"))) { // while the user does not want to return to the main program
          System.out.println("~~~~~~~~~~~~ Aspect Menu ~~~~~~~~~~~~"); // printing the 'Aspect' menu title
          System.out.println("a. Bathroom");    // option for bathroom info
          System.out.println("b. Bed");         // option for bed info
          System.out.println("c. House");       // option for house info
          System.out.println("d. Lighting");    // option for lighting info
          System.out.println("e. Footwear");    // option for shoes info
          System.out.println("f. Water");       // option for water info
          System.out.println("r. Return to Home Menu"); // option for returning to the home menu
          System.out.print("Please make a selection (enter a letter): "); // prompting the user for a selection
          response = input.nextLine(); // reading the user's input for their selection
          System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"); // creating a line for formatting
          
          if (response.equalsIgnoreCase("r")) {           // if the user wants to return to the Home Menu
            System.out.println("Returning to Home Menu...\n"); // displaying returning to home menu message
            break;    // breaking out of the Aspect Menu loop
          } else if (response.equalsIgnoreCase("a")) {  // if the user chooses the bathroom
            System.out.println(bathroomInfo[familyNum]); // display bathroom description
          } else if (response.equalsIgnoreCase("b")) {  // if the user chooses the bed option
            System.out.println(bedInfo[familyNum]);      // display the bed description
          } else if (response.equalsIgnoreCase("c")) {  // if the user chooses the house option
            System.out.println(houseInfo[familyNum]);    // display the house description
          } else if (response.equalsIgnoreCase("d")) {  // if the user chooses the lighting option
            System.out.println(lightingInfo[familyNum]); // display the lighting description
          } else if (response.equalsIgnoreCase("e")) {  // if the user chooses the shoe option
            System.out.println(shoeInfo[familyNum]);     // display the shoe description
          } else if (response.equalsIgnoreCase("f")) {  // if the user chooses the water option
            System.out.println(waterInfo[familyNum]);    // display the water description
          } else {
            System.out.println("Invalid input.\n");    // telling the user their input was invalid
          }
        }
      }
    }
  input.close(); // closing the Scanner object
  }
}

  
