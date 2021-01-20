import java.util.Scanner;             // importing the Scanner class
import java.util.ArrayList;           // importing the ArrayList class
import java.io.IOException;           // importing the IOException
import java.io.Serializable;          // importing the Serializable class/interface
import java.io.FileOutputStream;      // importing the FileOutputStream
import java.io.FileInputStream;       // importing the FileInputStream
import java.io.ObjectOutputStream;    // importing the ObjectOutputStream
import java.io.ObjectInputStream;     // importing the ObjectInputStream
import java.io.FileNotFoundException; // importing the FileNotFoundException

class Main {
  static Scanner input = new Scanner ( System.in );    // creating the scanner object for user input
  static String response;                             // declaring the String variable for the user's input

  public static void main(String[] args) {
    System.out.println("This is Tasker, the place where you can handle all your tasks. With this, maintaining good health and well-being has never been easier!\n");   // introduces/presents the app
    ArrayList<Task> taskList;                                // declaring the ArrayList of tasks
    taskList = deser();                                                 // deserializes any existing .ser file for ArrayList Object
    do {                                                     // runs the main program loop
      System.out.println("In Tasker, you can choose from...\na. Check task list\nb. Add a task\nc. Remove a task\nd. Mark a task as complete\ne. Change a task's date\nf. Save & Exit Tasker");             // options menu 
      System.out.print("Please select an option: ");    // prompting the user
      response = input.nextLine();                      // receiving the user's input
      System.out.print("\n");                           // printing a newline for formatting
      if (response.equalsIgnoreCase("a")) {             // if the user choes to look at task list
        printTaskList(taskList);                        // printing the task list to the user
      } else if (response.equalsIgnoreCase("b")) {      // if the user wants to add a task
        addTask(taskList);                              // allows the user to add a task
      } else if (response.equalsIgnoreCase("c")) {      // if the user wants to remove a task
        delTask(taskList);                              // allows the user to remove a task
      } else if (response.equalsIgnoreCase("d")) {      // if the user wants to mark a task as complete
        markComplete(taskList);                         // allows the user to mark a task as complete
      } else if (response.equalsIgnoreCase("e")) {      // if the user wants to change a task's date
        promptDate(taskList);                           // allows the user to change the date of a task
      } else if (!response.equalsIgnoreCase("f")){
        System.out.println("Invalid input.");           // telling the user they entered an invalid input
      }
    } while (!response.equalsIgnoreCase("f"));          // while the user does not want to exit                   
    ser(taskList);                                      // serializes the ArrayList of the task objects the user created
  }

  /**
  * This method prints out the user's task list in a formatted manner. 
  *
  * @param tasks            This is the ArrayList of Task objects the user created
  */
  public static void printTaskList(ArrayList<Task> taskList){
    if (taskList.size() != 0) {
      Task task;                    // declaring the temporary Task variable
      System.out.printf("%-11s%-5s%-22s%s\n", "Completed", "No.", "Task", "Date");   // printing the column headers
      for (int i = 0; i < taskList.size(); i++) {        // iterating over each task in the user's task list
        task = taskList.get(i);                          // getting the current task in the list
        System.out.print("  [ ");                     // formatting 
        if (task.getIsComplete()) {                   // if the task is complete
          System.out.print("✓");                      // marking the task as complete
        } else {
          System.out.print(" ");                      // marking the task as incomplete
        }
        System.out.print(" ]    ");                   // formatting
        System.out.printf("%-5d", i + 1);             // printing the task number
        System.out.printf("%-22s", task.getLabel());  // printing the label of the task
        System.out.printf("%s", task.getDate());      // printing the date of the task
        System.out.print("\n");                       // printing a newline for formatting
      }
    } else {
      System.out.println("You do not have any tasks. Be sure to check your task list and add a task."); // telling the user they do not have any tasks
    }
    System.out.print("\n");                           // printing a newline for formatting
    pause();                                          // pauses the screen
  }

  /** 
  * This method allows the user to add a task to their task list. This is done by 
  * using a do while loop to ask the user for a task label within 20 characters. Once the program 
  * receives the label from the user, it instantiates an object of the Task class and adds it 
  * to the ArrayList of taskList.
  *
  * @param tasks          This is the ArrayList of Task objects the user created
  */
  public static void addTask(ArrayList<Task> taskList) {
    // String response;           // String variable for user's input
    Task task;                    // declaring the temporary task variable for the user's task
    do {    // do while loop for preventing the user from entering a label longer than 20 characters
      System.out.print("Task (max 20 char.): ");    // prompting the user for task label
      response = input.nextLine();                  // receiving user input
    } while (response.length() > 20);               // runs until they enter a valid input
    
    if (response.equals("") || response.equals(" ")) {    // if they left the label blank
      task = new Task();
    } else {
      task = new Task(response);
    }
    System.out.print("Task added.");                // tells the user that adding the task was successful
    System.out.println("\n");                       // prints a newline for formatting
    taskList.add(task);                             // adds task to the task list
    pause();                                        // pauses the screen
  }

  /**
  * This method removes a task from the user's task list (ArrayList). This is done by 
  * using the remove() method with a user inputted index number.
  *
  * @param taskList           This is the ArrayList of Task objects the user created
  */
  public static void delTask(ArrayList<Task> taskList) {
    // String response;                         // String variable for the user's input
    String prompt = "delete";                   // initializing the type of prompt to the user
    int index = promptIndex(taskList, prompt);     // prompting, receiving, and error checking the user's input
    if (index != -1) {                          // if there was not an invalid index value
      taskList.remove(index);                   // remove the element at this index
    }
    System.out.print("\n");                     // printing a newline for formatting 
    pause();                                    // pausing the screen
  }

  /**
  * This method marks a task as complete. This is done by receiving the user's input for which task
  * using the mutator method to alter the isComplete attribute's value. 
  *
  * @param taskList         This is the ArrayList of Task objects the user created
  */
  public static void markComplete(ArrayList<Task> taskList) {
    String prompt = "mark as complete";                      // initializing the type of prompt to the user
    Task task;
    int index = promptIndex(taskList, prompt);               // prompting, receiving, and error checking the user's input
    if (index != -1) {                                  // if the index exists
      task = taskList.get(index);                         // changing the pointer to this object in the ArrayList
      if (!task.getIsComplete()) {                        // if the task is not already marked as complete
        System.out.print("Would you like to remove the completed task? (Y/N): ");   // prompting the user if they want to remove the completed task
        response = input.nextLine();                      // receiving the user's input
        if (response.equalsIgnoreCase("Y")) {             // if the user wants to remove the task
          taskList.remove(index);                         // removes the task element from the ArrayList
        } else if (response.equalsIgnoreCase("N")) {      // if the user does not want to
          task.setIsComplete(true);                       // marks the task as complete
          System.out.println("Task marked as complete."); // telling the user that the marking was successful
        } else {                                          // if the user entered an invalid value
          System.out.println("Invalid input. Task was not removed.");   // telling the user they entered an invalid input
        } 
      } else {
        System.out.println("You have already marked this task as complete."); // telling the user 
      }
    }
    System.out.print("\n");                     // printing a newline for formatting
    pause();                                    // pausing the screen
  }

  /**
  * This method changes the String date attribute of a Task object. 
  *
  * @param taskList           This is the ArrayList of Task objects the user created
  */
  public static void promptDate(ArrayList<Task> taskList) {
    String prompt = "change the date of";                 // initializing the prompt to the user
    Task task;                                            // declaring the task variable for date changing
    int index = promptIndex(taskList, prompt);            // prompting, receiving, and error checking the user's input
    if (index != -1) {                                    // if the index exists
      task = taskList.get(index);                         // assigning the variable pointer to the task at this index
      // declaring the integer variables for the month, day, and year numbers
      int month;                                          // month number
      int day;                                            // day number
      int year;                                           // year number
      // month prompting and error checking
      do {                                                // error checking for correct month value
        System.out.print("Enter valid month #: ");      // prompting the user for month number
        month = input.nextInt();                          // receiving the user's input for month
        input.nextLine();                                 // preventing the nextInt/nextLine error
      } while (month < 1 || month > 12);                  // while the user does not enter a valid input for month
      // year prompting and error checking
      do {
        System.out.print("Enter valid year #: ");       // prompting the user for year number
        year = input.nextInt();                           // receiving the user's input for year
        input.nextLine();                                 // preventing the nextInt/nextLine error
      } while (year < 0);                                 // while the user does not enter a valid input for day
      // day prompting and error checking
      do {                                                // error checking for correct day value
        System.out.print("Enter valid day #: ");        // prompting the user for day number
        day = input.nextInt();                            // receiving the user's input for day
        input.nextLine();                                 // preventing the nextInt/nextLine error
      } while (day > checkDaysInMonth(month, year) || day <= 0); // while the user does not enter a valid input for day
      String formattedDate = String.format("%02d/%02d/%04d", month, day, year);  // formatting the date into String format of mm/dd/yyyy
      task.setDate(formattedDate);                        // setting the date of the Task object
    } 
    System.out.print("\n");       // printing a newline for formatting
    pause();                      // pausing the screen
  }

  /**
  * This method serializes the ArrayList of the user's tasks into a file called 'taskList.ser'.
  * This is used to save the user's task list 
  * 
  * @param taskList
  */
  public static void ser(ArrayList<Task> taskList) {
    try {
      FileOutputStream fileOut = new FileOutputStream("taskList.ser");  // create a space for serializing the ArrayList object
      ObjectOutputStream out = new ObjectOutputStream(fileOut);         // object for serializing the ArrayList object
      out.writeObject(taskList);                                        // writing/serializing the ArrayList object (has all the user's task variables)
      out.close();                                                    // closing the serializing object
      fileOut.close();                                                // closing the serialized file
      System.out.println("Tasks saved.");                               // printing that the save was successful
    } catch (IOException ioe) {   
      System.err.println("Java IO Exception: " + ioe);
    }
  }

  /**
  * This method tries deserializes the 'tasks.ser' file (if it exists) and returns an 
  * ArrayList of Task objects the user created.
  *
  * @return ArrayList<Task>          This is the task list that the user created that was serialized
  */
  public static ArrayList<Task> deser() {
    ArrayList<Task> taskList;                     // declaring the tasks ArrayList variable
    taskList = new ArrayList<Task>();             // giving this object a temporary pointer
    try {
      FileInputStream fileIn = new FileInputStream("taskList.ser"); // searching for the file to deserialize
      ObjectInputStream in = new ObjectInputStream(fileIn);         // object used for deserializing 
      taskList = (ArrayList) in.readObject();                       // reading the serialized ArrayList object and assigning it to tasks
      in.close();                                                   // closing the object for deserializing
      fileIn.close();                                               // closing the file thats being read
    } catch (FileNotFoundException fnfe) {                          // if the file cannot be found
      // System.out.println("You have not created any tasks yet.");    // tells the user they have not created any tasks
    } catch (IOException ioe) {                                             // if an IO exception gets thrown
      // System.err.println("Java IO Exception: " + ioe);                      // prints an error message
    } catch (ClassNotFoundException cnfe) {                                 // if the class is not recognized
      // System.err.println("Class of object not found. Cannot deserialize."); // prints an error 
    }
  return taskList;
  }

  /** 
  * This method is used for prompting, receiving, and checking the user's input. This method
  * checks to see if the user's entered number is in range of the ArrayList fed to the method. This is 
  * done with a do while loop and the prompt parameter and returns a valid user inputted index.
  *
  * @param arrList         This is the ArrayList to check for the user's inputted index
  * @param prompt          This is the String value for the prompt to the user
  */
  public static int promptIndex(ArrayList<Task> arrList, String prompt) {
    int index;                        // index for task the user would like to remove
    if (arrList.size() == 0) {        // if their task list is empty 
      System.out.println("You do not have any tasks. Be sure to add a task to your task list."); // telling the user their task list is empty
    } else {                          // if the task list is not empty
      do {                            // using a do while to make sure the user enters a valid list index
      System.out.print("Which task would you like to " + prompt + "?: ");     // prompting the user for input
      index = input.nextInt();                                        // receiving the input from the user
      input.nextLine();                                               // avoiding the nextInt/nextLine error
      } while (index <= 0 || index > arrList.size());                  // while they do not enter a valid index

      return index - 1;                   // returning the valid user inputted index 
    }
    return -1;                        // in case the user cannot enter a valid value
  }

  /**
  * This method checks the number of days in the fed argument month. This is done by using if and 
  * else if statements, along with some math and logical operators. The method will return 0 if
  * the month number is invalid.
  *
  * @param m                  This is the number value of the month
  * @return int               This is the number of days in that month
  */
  public static int checkDaysInMonth(int m, int y) {
    if (m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12) { // if it is a long month
      return 31;      // the month has 31 days
    } else if (m == 4 || m == 6 || m == 9 || m == 11) {                        // if it is a short month
      return 30;      // the month has 30 days
    } else if (m == 2) {                                                        // if the month is February
      if (y % 4 == 0 && y % 100 != 0 || y % 400 == 0) {                // if it is a leap year
        return 29;    // the month has 29 days
      } else {        // if it is not a leap year
        return 28;    // the month has 28 days
      }
    }
    return 0;         // if the month number is invalid
  }

  /**
  * This method clears the terminal window. This is purely for formatting purposes and to reduce
  * clutter on the terminal window.
  */
  public static void clear(){
    System.out.print("\033[H\033[2J");
    System.out.flush();                 // flushes the stream
  }

  /**
  * This method pauses the app until the user presses the enter key. This is to allow the user 
  * to read what is displayed on the terminal until it gets flushed by the clear() method.
  */
  public static void pause(){
    System.out.print("Enter anything to continue: ");   // prompts the user to enter anything
    input.nextLine();                                   // receiving the user's input, but not storing it
    clear();                                            // clears the screen
  }
}