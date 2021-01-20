package reminder;             // packaging this class
import TempusFugit.Date;      // importing the classes related to the time and date
import TempusFugit.Time;      // importing the class related to time
/**
* This class is a subclass of Event. This represents an event of the user
* that happens at a specific time and date. This class adds a Time attribute.
*/
public class TimedEvent extends Event{
  // declaring attributes
  private Time time;                                      // time attribute for the associated task
  protected static final Time DEFAULT_TIME = new Time();  // default time attribute value

  // constructors
  /**
  * This is an overloaded constructor with no parameters. With no parameters,
  * this will assign default values to all the class attributes.
  */
  public TimedEvent() {
    this(DEFAULT_LABEL + nextTaskID, DEFAULT_DATE, DEFAULT_TIME);     // assigning default values
  }

  /**
  * This is an overloaded constructor with three parameters, one for each
  * attribute (including inherited ones).
  *
  * @param String label           This is the label of the task
  * @param Date date              This is the date associated with the task
  * @param Time time              This is the time associated with the task
  */
  public TimedEvent(String label, Date date, Time time) {
    super(label, date);           // using the constructor of its superclass
    this.setTime(time);           // assigns the time argument value to the attribute
  }

  /**
  * This is a mutator method for the time attribute.
  *
  * @param Time time              This is the time associated with the task
  */
  public void setTime(Time time) {
    this.time = time;             // assigns the time argument value to the time attribute
  }

  /**
  * This is an accessor method for the item attribute.
  *
  * @return Time                  This is the time associated with the task
  */
  public Time getTime() {
    return this.time;             // returns the time associated with the task
  }

  /**
  * This method prints out all the attributes of a TimedEvent object in
  * a readable fashion.
  */
  @Override
  public void printDetails() {
    super.printDetails();                             // uses its superclass version of the method to print the basic attributes
    System.out.print("Time:       " + this.getTime()); // printing the time of the event
  }
}