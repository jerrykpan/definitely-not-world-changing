package reminder;        // packaging this class
/**
* This class is a subclass of the Task class. This class represents 
* a task the user has to complete that involves one or more people.
*/
public class GroupTask extends Task{
  // declaring attributes
  private String [] people;                           // array of people the task is associated with
  protected static final String [] DEFAULT_PEOPLE = {}; // default array of people the task is associated with

  /**
  * This is an overloaded constructor that has no parameters. All attributes are
  * assigned their default values.
  */
  public GroupTask(){
    this(DEFAULT_LABEL + nextTaskID, DEFAULT_PEOPLE);     // assigning the default values
  }

  /**
  * This is an overloade constructor that has a parameter for the people involved. The label 
  * attribute is assigned its default value
  *  
  * @param String [] people   This is an array of people associated with the task
  */
  public GroupTask(String [] people) {
    this(DEFAULT_LABEL + nextTaskID, people);             // assiging the default value for label
  }

  /**
  * This is an overloaded constructor that has two parameters, each one
  * for each attribute of this class (including inherited attributes).
  *
  * @param String label       This is the label for the task
  * @param String [] people   This is an array of people associated with the task
  */
  public GroupTask(String label, String [] people) {
    super(label);                 // assigns the value of the label to the attribute
    this.setPeople(people);       // assigns the value of people to the attribute
  }

  /**
  * This is an accessor method for the people attribute.
  *
  * @return String []         This is the array of people associated with the task
  */
  public String [] getPeople() {
    return this.people;            // this is the array of people associated with the task
  }

  /**
  * This is a mutator method for the people attribute.
  *
  * @param String []          This is the array of people associated with the task
  */
  public void setPeople(String [] people) {
    this.people = people;         // assigning the value of people to the object's attribute
  }

  /**
  * This method prints out all the attributes of a GroupTask object in
  * a readable fashion.
  */
  @Override
  public void printDetails() {
    super.printDetails();       // uses its superclass version of the method to print the basic attributes
    System.out.print("People:     "); // printing the subheading for attribute
    for (int i = 0; i < this.getPeople().length; i++) {
      if (i != this.getPeople().length - 1) {               // iterating through each name in the people array
        System.out.print(this.getPeople()[i] + ", ");       // printing each name, separated by a comma and space
      } else {
        System.out.print(this.getPeople()[i]);              // printing the last name
      }
    }         
  }

  /**
  * This method returns a String representation of the GroupTask object.
  *
  * @return String        This is the String representation of the GroupTask object
  */
  @Override
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