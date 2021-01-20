package reminder;        // packaging this class
import TempusFugit.Date; // importing the Date class I made
/**
* This class is a subclass of Task. This class represents an event 
* of the user that is on a specific date. This class adds a Date attribute.
*/
public class Event extends Task{
  private Date date;                                      // this is the date associated with the task
  protected static final Date DEFAULT_DATE = new Date();    // default date value of Date class

  /**
  * This is an overloaded constructor with no parameters. All the attributes
  * are assigned to their default values.
  */
  public Event(){
    this(DEFAULT_LABEL + nextTaskID, DEFAULT_DATE);
  }

  /**
  * This is an overloaded constructor with two parameters, one for each of 
  * the object's attributes (including inherited ones).
  *
  * @param String label    This is the label of the task
  * @param Date date       This is the date associated with the task
  */
  public Event(String label, Date date) {
    super(label);               // assigning the value of label to the attribute
    this.setDate(date);         // assigning the value of date to the attribute
  }

  /**
  * This is an accessor method for the date attribute.
  *
  * @return Date           This is the date associated with the task
  */
  public Date getDate() {
    return this.date;          // returns the date associated with the task
  }

  /**
  * This is a mutator method for the date attribute.
  * 
  * @param Date date      This is the date associated with the task
  */
  public void setDate(Date date) {
    this.date = date;         // assigns the date value to the date attribute
  }

  /**
  * This method prints out all the attributes of an Event object in
  * a readable fashion.
  */
  @Override
  public void printDetails() {
    super.printDetails();                             // uses its superclass version of the method to print the basic attributes
    System.out.print("Date:       " + this.getDate() + "\n"); // printing the date of the event
  }

  /**
  * This methods returns a String representation of the event.
  * This will include information such as label, completion, and date.
  *
  * @return String          This is the String representation of the Event object
  */
  @Override
  public String toString() {
    String output = "";             // initializing the formatted String
    if (this.getIsComplete()) {     // if the task is complete
      output += "  [ ✓ ]    ";      // marking the task as complete
    } else {
      output += "  [   ]    ";      // marking the task as incomplete
    }
    output += String.format("%-22s", this.getLabel());    // adding the label of the task

    output += this.date;            // adding the date

    return output;                  // returning formatted String
  }
}