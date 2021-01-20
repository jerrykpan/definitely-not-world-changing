import java.util.Scanner;             // importing the Scanner class
// importing classes for serialization/file IO
import java.util.ArrayList;           // importing the ArrayList class
import java.io.IOException;           // importing the IOException
import java.io.Serializable;          // importing the Serializable class/interface
import java.io.FileOutputStream;      // importing the FileOutputStream
import java.io.FileInputStream;       // importing the FileInputStream
import java.io.ObjectOutputStream;    // importing the ObjectOutputStream
import java.io.ObjectInputStream;     // importing the ObjectInputStream
import java.io.FileNotFoundException; // importing the FileNotFoundException
// importing my classes for the types of tasks the user can enter
import reminder.Task;                 // importing my class for normal tasks
import reminder.GroupTask;            // importing my class for a group task
import reminder.Event;                // importing my class for an event on a date
import reminder.TimedEvent;           // importing my class for an event at a time
// importing my classes related to the time and date
import TempusFugit.Date;              // class for handling dates
import TempusFugit.Time;              // class for handling times

public class Main {
  static Scanner input = new Scanner ( System.in);    // creating the scanner object for user input
  static String response;                             // declaring the String variable for the user's input

  public static void main(String[] args) {
    System.out.println("This is Tasker, the place where you can handle all your tasks. With this, maintaining good health and well-being has never been easier!\n");   // introduces/presents the app
    ArrayList taskList;                                // declaring the ArrayList of tasks
    taskList = deser();                                                 // deserializes any existing .ser file for ArrayList Object
    do {                                                     // runs the main program loop
      System.out.println("In Tasker, you can choose from...\na. Check task list\nb. Check specific task\nc. Add a task\nd. Remove a task\ne. Mark a task as complete\nf. Change a task's date\ng. Save & Exit Tasker");             // options menu 
      System.out.print("Please select an option: ");    // prompting the user
      response = input.nextLine();                      // receiving the user's input
      System.out.print("\n");                           // printing a newline for formatting
      if (response.equalsIgnoreCase("a")) {             // if the user choes to look at task list
        printTaskList(taskList);                        // printing the task list to the user
      } else if (response.equalsIgnoreCase("b")) {      // if the user wants to check a specific task
        displayDetails(taskList);                       // displaying a certain task
      } else if (response.equalsIgnoreCase("c")) {      // if the user wants to add a task
        addTask(taskList);                              // allows the user to add a task
      } else if (response.equalsIgnoreCase("d")) {      // if the user wants to remove a task
        delTask(taskList);                              // allows the user to remove a task
      } else if (response.equalsIgnoreCase("e")) {      // if the user wants to mark a task as complete
        markComplete(taskList);                         // allows the user to mark a task as complete
      } else if (response.equalsIgnoreCase("f")) {      // if the user wants to change a task's date
        changeDate(taskList);                           // allows the user to change the date of a task
      } else if (!response.equalsIgnoreCase("g")){
        System.out.println("Invalid input.");           // telling the user they entered an invalid input
      }
    } while (!response.equalsIgnoreCase("g"));          // while the user does not want to exit                   
    ser(taskList);                                      // serializes the ArrayList of the task objects the user created
  }

  /**
  * This method prints out the user's task list in a formatted manner.
  * The only details about each task that are printed are the label, completion
  * status, and date.
  *
  * @param tasks            This is the ArrayList of Task objects the user created
  */
  public static void printTaskList(ArrayList taskList){
    if (taskList.size() != 0) {
      Task task;                    // declaring the temporary Task variable
      System.out.printf("%-5s%-11s%-22s%s\n", "No.", "Completed", "Task", "Date");   // printing the column headers
      for (int i = 0; i < taskList.size(); i++) {        // iterating over each task in the user's task list
        System.out.printf("%-5d", i + 1);             // printing the task number
        System.out.print(taskList.get(i));                       // printing the task info
        // System.out.printf();  // printing the label of the task
        System.out.print("\n");                       // printing a newline for formatting
      }
    } else {
      System.out.println("You do not have any tasks. Be sure to check your task list and add a task."); // telling the user they do not have any tasks
    }
    System.out.print("\n");                           // printing a newline for formatting
    pause();                                          // pauses the screen
  }

  /**
  * This method prints out the details of a specific task to the user. 
  * This prints out all the details of a particular task.
  * 
  * @param ArrayList tasks     This is the ArrayList of Task objects the user created
  */
  public static void displayDetails(ArrayList taskList) {
    String prompt = "see the details of";           // initializing the type of prompt to the user
    Task task;
    int index = promptIndex(taskList, prompt);     // prompting, receiving, and error checking the user's input
    if (index != -1) {
      task = (Task) taskList.get(index);
      System.out.print("\n");       // prints newline for formatting
      task.printDetails();         // prints the details of that specific task
    }
    System.out.print("\n");                       // printing a newline for formatting
    pause();                                      // pausing the screen
  }

  /** 
  * This method allows the user to add a task to their task list. This is done by 
  * using a do while loop to ask the user for a task label within 20 characters. Once the program 
  * receives the label from the user, it instantiates an object of the Task class and adds it 
  * to the ArrayList of taskList.
  *
  * @param tasks          This is the ArrayList of Task objects the user created
  */
  public static void addTask(ArrayList taskList) {
    boolean run = true;           // loop variable
    System.out.println("What kind of task would you like to add?: ");       // prompting user for type of task
    System.out.println("a. Task\nb. Group task\nc. Event\nd. Timed event"); // displaying the options to the user
    do {
      System.out.print("Please make a selection: ");                        // prompting the user to choose 
      response = input.nextLine();                                          // receiving the user's input 
      if (response.equalsIgnoreCase("a")) {                                 // if the user wants to add a regular task
        response = promptLabel();                                           // prompts, receives, and checks the user's entered label
        if (response.equals("") || response.equals(" ")) {        // if they left the label blank
          taskList.add(new Task());                               // adds task with default label to the task list
        } else {                                                  // if the user entered a non-blank value
          taskList.add(new Task(response));                       // adds task to the task list
        }
        run = false;                                              // breaking the loop
      } else if (response.equalsIgnoreCase("b")) {                // if the user wants to add a group task
        response = promptLabel();                                 // prompts, receives, and checks the user's entered label
        int num;                                                  // declaring the number 
        do {
          System.out.println("How many people are involved?: ");  // prompting the user for number of people involved with the task
          num = input.nextInt();                                  // receiving the user's input
          input.nextLine();                                       // preventing the nextInt/nextLine error
        } while (num <= 0);                                       // runs until the user enters a valid number
        String [] people = new String[num];                       // declaring an array with size of number of people
        for (int i = 0; i < num; i++) {
          System.out.print("Enter person's name: ");              // prompting for each person's name
          people[i] = input.nextLine();                           // receiving and assigning the user's input to the index of array of people
        }
        if (response.equals("") || response.equals(" ")) {        // if the user left the label blank
          taskList.add(new GroupTask(people));                    // adding the task with default label to the list
        } else {                                                  // if the user entered a non-blank value
          taskList.add(new GroupTask(response, people));          // adding the task to the list
        }
        run = false;                                              // breaking the loop
      } else if (response.equalsIgnoreCase("c")) {                // if the user wants to add an event
        response = promptLabel();                                 // prompts, receives, and checks the user's entered label
        System.out.println("Please enter the date of the event: ");   // prompting the user for the date
        Date eventDate = promptDate();                                // receiving the user's input for the date
        taskList.add(new Event(response, eventDate));                 // adding task to the task list
        run = false;                                                  // breaking the loop
      } else if (response.equalsIgnoreCase("d")) {                // if the user wants to add a timed event
        response = promptLabel();                                 // prompts, receives, and checks the user's entered label
        System.out.println("Please enter the date of the event: ");   // prompting the user for the date
        Date eventDate = promptDate();                                // receiving the user's input for the date
        Time eventTime = promptTime();                                // receiving the user's input for the time
        taskList.add(new TimedEvent(response, eventDate, eventTime)); // adding task to the task list
        run = false;
      }
    } while (run);

    System.out.print("Task added.");                // tells the user that adding the task was successful
    System.out.println("\n");                       // prints a newline for formatting
    pause();                                        // pauses the screen
  }

  /**
  * This method removes a task from the user's task list (ArrayList). This is done by 
  * using the remove() method with a user inputted index number.
  *
  * @param taskList           This is the ArrayList of Task objects the user created
  */
  public static void delTask(ArrayList taskList) {
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
  public static void markComplete(ArrayList taskList) {
    String prompt = "mark as complete";                      // initializing the type of prompt to the user
    Task task;
    int index = promptIndex(taskList, prompt);               // prompting, receiving, and error checking the user's input
    if (index != -1) {                                  // if the index exists
      task = (Task) taskList.get(index);                         // changing the pointer to this object in the ArrayList
      if (!task.getIsComplete()) {                        // if the task is not already marked as complete
        System.out.print("Would you like to remove the completed task? (Y/N): ");   // prompting the user if they want to remove the completed task
        response = input.nextLine();                      // receiving the user's input
        if (response.equalsIgnoreCase("Y")) {             // if the user wants to remove the task
          taskList.remove(index);                         // removes the task element from the ArrayList
        } else if (response.equalsIgnoreCase("N")) {      // if the user does not want to
          task.setIsComplete(true);                       // marks the task as complete
          System.out.println("Task marked as complete."); // telling the user that the marking was successful
        } else {                                          // if the user entered an invalid value
          task.setIsComplete(true);                       // marks the task as complete
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
  * This method prompts, error checks, and receives a user's input. Once they enter a valid date
  * a Date object is created with those values and is assigned to the event.
  *
  * @return Date               This is the date the user entered
  */
  public static Date promptDate() {
    // declaring the integer variables for the month, day, and year numbers
    int month;                                        // month number
    int day;                                          // day number
    int year;                                         // year number
    // month prompting and error checking
    do {                                                        // error checking for correct month value
      System.out.print("Enter valid month #: ");                // prompting the user for month number
      month = input.nextInt();                                  // receiving the user's input for month
      input.nextLine();                                         // preventing the nextInt/nextLine error
    } while (month < 1 || month > 12);                          // while the user does not enter a valid input for month
    // year prompting and error checking
    do {
      System.out.print("Enter valid year #: ");                 // prompting the user for year number
      year = input.nextInt();                                   // receiving the user's input for year
      input.nextLine();                                         // preventing the nextInt/nextLine error
    } while (year < 0);                                         // while the user does not enter a valid input for day
    // day prompting and error checking
    do {                                                        // error checking for correct day value
      System.out.print("Enter valid day #: ");                  // prompting the user for day number
      day = input.nextInt();                                    // receiving the user's input for day
      input.nextLine();                                         // preventing the nextInt/nextLine error
    } while (day > checkDaysInMonth(month, year) || day <= 0);  // while the user does not enter a valid input for day
    return new Date(day, month, year);                          // returns a Date object with the user entered value
  }

  /**
  * This method prompts, error checks, and receives the user's input. Once
  * they enter a valid time, a Time object is created with those values
  * and is assigned to the timed event.
  *
  * @return Time          This is the time the user entered 
  */
  public static Time promptTime() {
    int minute;                                       // minute number
    int hour;                                         // hour number (24-hour clock)
    // minute error-checking
    do {                                              // error checking for valid minute value
      System.out.print("Enter valid minute value: "); // prompting the user for minute value
      minute = input.nextInt();                       // receiving the user's input for minute
      input.nextLine();                               // preventing the nextInt/nextLine error
    } while (minute < 0 || minute >= 60);              // runs while the user does not enter a valid input for minute
    // hour error-checking
    do {
      System.out.print("Enter valid hour value (24-hr clock): "); // prompting the  user for hour value
      hour = input.nextInt();                         // receiving the user's input for hour
      input.nextLine();                               // preventing the nextInt/nextLine error
    } while (hour < 0 || hour > 23);                  // runs while the user does 
    return new Time(minute, hour);                    // returns a Time object with the values entered
  }

  /**
  * This method changes the date of an event the user created. The user will input which task they would like
  * to change the date of. If it is a valid task, it prompts the user for a new date and sets it to that date.
  * If not, the program tells the user they did not select a valid task.
  *
  * @param ArrayList          This is the list of the user's tasks
  */
    public static void changeDate(ArrayList taskList) {
    String prompt = "change the date of";                 // initializing the prompt to the user
    Task task;                                            // declaring the task variable for date changing
    int index = promptIndex(taskList, prompt);            // prompting, receiving, and error checking the user's input
    if (index != -1) {                                    // if the index exists
      Class eventCls = new Event().getClass();            // getting the name of the Event class
      if (eventCls.isInstance(taskList.get(index))) {     // checking to see if it a type of event
        Event e = (Event) taskList.get(index);            // declaring the temporary event placeholder
        e.setDate(promptDate());                          // setting the Date of the event to a new value
      } else {
        System.out.println("The task you selected is not an event or timed event.");    // telling the user that they did not select an event
      }
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
  public static void ser(ArrayList taskList) {
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
  * @return ArrayList          This is the task list that the user created that was serialized
  */
  public static ArrayList deser() {
    ArrayList taskList;                     // declaring the tasks ArrayList variable
    taskList = new ArrayList();             // giving this object a temporary pointer
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
  public static int promptIndex(ArrayList arrList, String prompt) {
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
  * This method prompts, receives, and checks the user's input for a task label. This method makes sure
  * that the user enters a label that has a max length of 20 characters.
  *
  * @return String            This is the label for the task the user wants to create.
  */
  public static String promptLabel(){
    do {    // do while loop for preventing the user from entering a label longer than 20 characters
      System.out.print("Task (max 20 char.): ");    // prompting the user for task label
      response = input.nextLine();                  // receiving user input
    } while (response.length() > 20);               // runs until they enter a valid input
    return response;                                // returns the label String for the task
  }

  /**
  * This method checks the number of days in the fed argument month. This is done by using if and 
  * else if statements, along with some math and logical operators. The method will return 0 if
  * the month number is invalid.
  *
  * @param m                  This is the number value of the month
  * @param y                  This is the number value of the year
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