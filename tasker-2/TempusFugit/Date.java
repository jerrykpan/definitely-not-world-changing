package TempusFugit;         // packaging this class 
import java.io.Serializable;    // importing the Serializable interface
/**
* This class represents a date that the user would assign 
* for one of their tasks. A date consists of a month, day, and
* year value.
*/
public class Date implements Serializable{
  // attributes
  /**
  * Declaring the day, month, and year values, along with their 
  * default counterparts.
  */
  private int month;                            // date month
  private int day;                              // date day
  private int year;                             // date year
  private static final int DEFAULT_MONTH = 1;   // default month
  private static final int DEFAULT_DAY = 1;     // default day
  private static final int DEFAULT_YEAR = 0;    // default year
  /**
  * Creates number counter of date objects
  */
  private static int numberOfDates;             // number of date object created

  /**
  * Creating lists of which months are long and short
  */
  private static final int [] LONG_MONTHS = {1, 3, 5, 7, 8, 10, 12}; // months with 31 days
  private static final int [] SHORT_MONTHS = {4,6,9,11};             // months with 30 days

  // constructors
  /**
  * Creates a default date if given no arguments
  */
  public Date(){
    this(DEFAULT_DAY, DEFAULT_MONTH, DEFAULT_YEAR);   // calls an overloaded constructor with default values
  }

  /**
  * Creates a date with the given arguments of day, month, and year.
  * 
  * @param month      This is the month value
  * @param day        This is the day value
  * @param year       This is the year value
  */
  public Date(int day, int month, int year){
    this.setMonth(month);     // sets the month 
    this.setDay(day);         // sets the day
    this.setYear(year);       // sets the year
    numberOfDates++;          // increments the number of dates created
  }

  /**
  * If appropriate, assigns the argument value to the object's day
  *
  * @param d    This is the day fed to the method
  */
  public void setDay(int d){
    // checks if the fed argument is a valid day
    if (d <= checkDaysInMonth(this.month, this.year) && d > 0){
      this.day = d;     // changes the day value
    } else {  
      System.err.println("Invalid day: " + d + ". Nothing changed!");  // error message
    }
  }

  /*
  * If user enters a valid year, set object's month to value of argument.
  *
  * @ param m   This is the month entered to method
  */
  public void setMonth(int m){
    // checks if the fed argument is a valid month
    if (m >= 1 && m <= 12){
      this.month = m;    // changes the day value
    } else {
      System.err.println("Invalid month: " + m + ". Nothing changed!"); // error message
    }
  }

  /**
  * If appropriate, assigns the argument value to the object's year.
  * @param y    This is the year fed to the method
  */
  public void setYear(int y){
    // checks if the fed argument is a valid year
    if (y >= 0){
      this.year = y;  // changes the year value
    } else {
      System.err.println("Invalid year: " + y + ". Nothing changed!"); // error message
    }
  }

  /**
  * Returns the day value of the object to the user
  * @return int   This is the numerical day value
  */
  public int getDay(){
    return this.day;    // returns the date day value
  }

  /*
  * Returns the value of object's month to user.
  * 
  * @return int   This is the numerical month value
  */  
  public int getMonth(){
    return this.month;  // returns the date month value
  }

  /*
  * Returns the year value of the object to the user
  * @return int     This is the numerical year value
  */
  public int getYear(){
    return this.year;   // returns the date year value
  }

  /**
  * Return the number of date objects instantiated
  * @return int     The number of date objects instantiated
  */
  public int getNumberOfDates(){
    return numberOfDates;   // return the number of date objects created 
  }

  /*
  * Returns a String representation of the Date object.
  *
  * @return String    This is the string representation of object
  */
  public String toString(){
    String output = "";
    output += String.format("%02d/%02d/%04d", this.getMonth(), this.getDay(), this.getYear()); // return the date in the format mm/dd/yyyy
    return output;   
  } 

  /**
  * Calculates and returns the number of days in the month fed into this 
  * method.
  *
  * @param m     This is the month to calculate for
  * @param y     This is the year of the date
  * @return int  This is the number of days in the month
  */
  public static int checkDaysInMonth(int m, int year){
    // iterate through the LONG_MONTHS array to check if month fed is a long month (31 days)
    for (int i = 0; i < LONG_MONTHS.length; i++){
      // if the month fed is a long month
      if (m == LONG_MONTHS[i]){
        // return 31 days
        return 31;
      }
    }
    // iterate through the SHORT_MONTHS array to check if month fed has 30 days; short month.
    for (int i = 0; i < SHORT_MONTHS.length; i++){
      // if the month fed is a short month
      if (m == SHORT_MONTHS[i]){
        // return 30 days
        return 30;
      }
    }

    // if the month is Feburary
    if (m == 2) {
      // calculating if it is a leap year
      if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
        // return 29 days in Feburary if it is a leap year
        return 29;
        // if not leap year
      } else {
        // return 28 days
        return 28;
      }
    }
  // if fed an invalid month, return 0 as an error output.
  return 0;
  }
}