import java.io.Serializable;        // imports the Serializable class/interface
/**
* Represents a task the user created. Each task contains a label, completion status, and a blank date. 
* The user may choose to include a date for the task.
*
*/
class Task implements Serializable{
  // attributes - privates, finals, statics
  private String label;                                      // label/name of Task
  private boolean isComplete;                                // boolean state of completion
  private String date;                                       // date in string format of --/--/----
  private static final String DEFAULT_LABEL = "Task ";       // default value for label
  private static final boolean DEFAULT_IS_COMPLETE = false;  // default value for completion
  private static final String DEFAULT_DATE = "";             // default value for date may change to --/--/---- or mm/dd/yyyy or " "
  private static int numberOfTasks;                          // static attribute for number of task objects
  private static int nextTaskID = 1;                         // used for default label of task

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
  */
  public Task(String label){
    this.setLabel(label);                             // assigning the String argument of label
    this.setIsComplete(DEFAULT_IS_COMPLETE);            // assigning the default value of completion
    this.setDate(DEFAULT_DATE);                       // assigning the default blank value of date
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

  /**
  * This is a mutator method for the date attribute.
  * @param            This value is assigned to the date attribute.
  */
  public void setDate(String date) {
    this.date = date;                                 // assigning the String argument value of date
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
  * This is an accessor method for the date attribute.
  * @return String          This is the value of the date attribute.
  */
  public String getDate(){
    return this.date;                              // returning the String value of date
  }
  // possibly static or other methods (misc)
}   