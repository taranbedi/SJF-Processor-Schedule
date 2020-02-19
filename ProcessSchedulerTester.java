//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: (P10 SJF Process Scheduler)
// Files: (CustomProcess.java, WaitingProcessQueue.java, ProcessScheduler.java, ProcessSchedulerTester.java)
// Course: (CS 300, fall, 2019)
//
// Author: (Taran Bedi)
// Email: (tbedi@wisc.edu)
// Lecturer's Name: (Mouna Kacem)
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: (None)
// Online Sources: (None)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.util.NoSuchElementException;

/**
 * Test class to ensure all methods work properly
 * 
 * @author Taran Bedi
 *
 */
public class ProcessSchedulerTester {



  /**
   * checks the correctness of the insert operation implemented in the WaitingProcessQueue class
   * 
   * @return true if working properly, else false
   */
  public static boolean testInsertWaitingProcessQueue() {
    WaitingProcessQueue test = new WaitingProcessQueue();
    CustomProcess a = new CustomProcess(5);
    CustomProcess b = new CustomProcess(3);
    CustomProcess c = new CustomProcess(7);
    try {
      test.insert(null);
      return false;
    } // ensures NullPointerException is thrown if argument is null
    catch (NullPointerException e) {
      test.insert(a);
      test.insert(b);
      test.insert(c);
      // ensures that elements were inserted properly and size was updated
      if (test.peekBest().equals(b) && test.size() == 3) {
        return true;
      } else {
        return false;
      }
    }
  }


  /**
   * checks the correctness of the removeBest operation implemented in the WaitingProcessQueue class
   * 
   * @return true if works properly, else false
   */
  public static boolean testRemoveBestWaitingProcessQueue() {
    WaitingProcessQueue test = new WaitingProcessQueue();
    CustomProcess a = new CustomProcess(5);
    CustomProcess b = new CustomProcess(3);
    CustomProcess c = new CustomProcess(7);
    try {
      test.removeBest();
      return false;
    } // ensures remove method throws an exception when trying to remove from an empty list
    catch (NoSuchElementException e) {
      test.insert(a);
      test.insert(b);
      test.insert(c);
      // ensures remove method removes the smallest value and size is updated
      if (test.removeBest().equals(b)) {
        if (test.size() == 2) {
          return true;
        } else {
          return false;
        }
      } else {
        return false;
      }
    }
  }

  /**
   * Checks the correctness of the isEmpty method in WaitingProcessQueue
   * 
   * @return true if working properly, else false.
   */
  public static boolean testIsEmpty() {
    WaitingProcessQueue test = new WaitingProcessQueue();
    CustomProcess b = new CustomProcess(3);
    // ensures isEmpty works properly
    if (test.isEmpty()) {
      test.insert(b);
      if (test.isEmpty() == false) {
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }

  /**
   * Checks the correctness of the size method in WaitingProcessQueue
   * 
   * @return true if works properly, else false
   */
  public static boolean testSize() {
    WaitingProcessQueue test = new WaitingProcessQueue();
    CustomProcess a = new CustomProcess(5);
    CustomProcess c = new CustomProcess(7);
    if (test.size() == 0) {
      test.insert(a);
      test.insert(c);
      test.removeBest();
      // checks to see size is updating while inserting and removing nodes
      if (test.size() == 1) {
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }

  /**
   * Checks the correctness of the compareTo method in the CustomProcess class
   * 
   * @return true if works properly, else false
   */
  public static boolean testCompareTo() {
    CustomProcess a = new CustomProcess(5);
    CustomProcess b = new CustomProcess(3);
    // ensures that when comparing a CustomProcess with a higher burst time to one with a lower
    // burst
    // time we receive a number greater than 0
    if (a.compareTo(b) > 0) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Checks the correctness of the peekBest method in WaitingProcessQueue
   * 
   * @return true if works properly, else false
   */
  public static boolean testPeekBest() {
    WaitingProcessQueue test = new WaitingProcessQueue();
    CustomProcess a = new CustomProcess(5);
    CustomProcess b = new CustomProcess(3);
    CustomProcess c = new CustomProcess(7);
    try {
      test.peekBest();
      return false;
    } // ensures that method throws an exception when trying to remove from an empty list
    catch (NoSuchElementException e) {
      test.insert(a);
      test.insert(b);
      test.insert(c);
      // ensures that we return the smallest node but don't remove it
      if (test.peekBest().equals(b)) {
        if (test.size() == 3) {
          return true;
        } else {
          return false;
        }
      } else {
        return false;
      }
    }
  }


  /**
   * Driver method to make sure are test methods pass
   * 
   * @param args
   */
  public static void main(String[] args) {
    if (testInsertWaitingProcessQueue()) {
      System.out.println("Pass");
    } else {
      System.out.println("Failed");
    }
    if (testRemoveBestWaitingProcessQueue()) {
      System.out.println("Pass");
    } else {
      System.out.println("Failed");
    }
    if (testPeekBest()) {
      System.out.println("Pass");
    } else {
      System.out.println("Failed");
    }
    if (testCompareTo()) {
      System.out.println("Pass");
    } else {
      System.out.println("Failed");
    }
    if (testSize()) {
      System.out.println("Pass");
    } else {
      System.out.println("Failed");
    }
    if (testIsEmpty()) {
      System.out.println("Pass");
    } else {
      System.out.println("Failed");
    }
  }

}
