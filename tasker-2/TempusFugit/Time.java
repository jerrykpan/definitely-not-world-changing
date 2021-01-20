package TempusFugit;         // packaging this class
import java.io.Serializable;      // importing the Serializable interface
/**
* This class represents a time that the user would assign
* for one of their tasks. A time consists of a minute and hour value.
*/
public class Time implements Serializable{
  // attributes, 
  private int minute;                     // value for the minute of time for task
  private int hour;                       // value for the hour of time for task
  private static final int DEFAULT_MINUTE = 0;   // default value for the minute of time for task
  private static final int DEFAULT_HOUR = 0;     // default value for the hour of time for task

  // Constructors
  /**
  * This is an overloaded version of a constructor. This version
  * does not have any parameters and uses explicit constructor
  * invocation to call on another constructor, using default attributes
  * for arguments.
  */
  public Time() {
    this(DEFAULT_MINUTE, DEFAULT_HOUR);     // using ECI to assign default values
  }

  /**
  * This is the main overloaded version of the constructor for this
  * class. This constructor assigns the value of the arguments to its 
  * respective attributes.
  */
  public Time(int minute, int hour) {
    this.setMinute(minute);           // calls the mutator for the minute attribute 
    this.setHour(hour);               // calls the mutator for the house attribute
  }

  /**
  * This is an accessor method for the minute attribute.
  * 
  * @return int       This is the minute value of the time
  */
  public int getMinute() {
    return this.minute;         // returns the minute value of the time
  }

  /**
  * This is an accessor method for the hour attribute.
  *
  * @return int       This is the hour value of the time
  */
  public int getHour() {
    return this.hour;           // returns the hour value of the time
  }

  /**
  * This is the mutator method for the minute attribute.
  *
  * @param int m      This is the minute value of the time
  */
  public void setMinute(int m) {
    this.minute = m;            // assigns the int argument value to the minute attribute
  }

  /**
  * This is the mutator method for the hour attribute
  *
  * @param int h      This is the hour value of the time
  */
  public void setHour(int h) {
    this.hour = h;              // assigns the int argument value to the hour attribute
  }

  /**
  * This method returns a String representation of a Time object.
  *
  * @return String        This is the String representation of the Time object
  */
  public String toString() {
    String output = "";                     // initializing an empty string for output
    output += String.format("%02d:%02d", this.hour, this.minute); // formatting the stirng
    return output;   // the String representation of the Time object
  }
}