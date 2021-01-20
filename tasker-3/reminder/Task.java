package reminder;                      // packaging this class
import java.io.Serializable;        // imports the Serializable class/interface
/**
* Represents a task the user created. Each task contains a label and a completion status. 
*/
public class Task implements Serializable{
  // attributes - privates, finals, statics
  private String label;                                      // label/name of Task
  private boolean isComplete;                                // boolean state of completion
  protected static final String DEFAULT_LABEL = "Task ";       // default value for label
  protected static final boolean DEFAULT_IS_COMPLETE = false;  // default value for completion
  private static int numberOfTasks;                          // static attribute for number of task objects
  protected static int nextTaskID = 1;                         // used for default label of task

  // constructors
  /**
  * This is an overloaded constructor where there are no parameters and all the attributes are assigned
  * to their default values.
  */
  public Task(){
    this(DEFAULT_LABEL + nextTaskID);           // calling another constructor with the default value of label
    nextTaskID++;                               // incrementing the task ID number
  }

  /***
  * This is an overloaded constructor where there is a String parameter called label.
  * This constructor assigns the value of the argument to its respective attribute and the other attributes
  * are given their default values.
  *
  * @param String label       This is the label of the task
  */
  public Task(String label){
    this.setLabel(label);                             // assigning the String argument of label
    this.setIsComplete(DEFAULT_IS_COMPLETE);            // assigning the default value of completion
  }

  // Mutators (setters)
  /**
  * This is a mutator method for the label attribute.
  *
  * @param label      This value is assigned to the label attribute
  */
  public void setLabel(String label){                 
    this.label = label;                               // assigning the String argument value of label
  }

  /**
  * This is a mutator method for the isComplete attribute
  *
  * @param            This value is assigned to the isComplete attribute
  */
  public void setIsComplete(boolean isComplete){
    this.isComplete = isComplete;                     // assigning the boolean argument value of completion
  }

  // Accessors (getters)
  /**
  * This is an accessor method for the label attribute.
  * @return String          This is the value of the label attribute
  */
  public String getLabel(){
    return this.label;                                // returning the String value of label
  }

  /**
  * This is an accessor method for the isComplete attribute.
  * @return boolean          This is the value of the isComplete attribute
  */
  public boolean getIsComplete(){
    return this.isComplete;                           // returning the boolean value of isComplete
  }

  /**
  * This method is for printing out all the attributes for a Task object 
  * in a readable fashion.
  */
  public void printDetails() {
    System.out.printf("Task:       %s\n", this.getLabel());   // printing the label of the tast
    // printing the completion status
    if (this.getIsComplete()) {                 // if the user completed the task
      System.out.print("Complete?:  [ ✓ ]\n");  // print checkmark for completion
    } else {                                    // if the user has not completed the task
      System.out.print("Complete?:  [   ]\n");   // print empty
    }
  }

  /**
  * This method returns the String representation of the object.
  * This will include information such as the label and completion.
  *
  * @return String          This is the String representation of the object
  */
  public String toString() {
    String output = "";             // initializing the formatted String
    if (this.getIsComplete()) {     // if the task is complete
      output += "  [ ✓ ]    ";      // marking the task as complete
    } else {                        // if the task is not complete
      output += "  [   ]    ";      // marking the task as incomplete
    }
    output += String.format("%-22s", this.getLabel());    // adding the label of the task
    return output;                  // returning the String representation of the task
  }
}   