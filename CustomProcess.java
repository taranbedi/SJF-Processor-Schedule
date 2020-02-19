
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
/**
 * This class implements the comparable interface
 * 
 * @author Taran Bedi
 *
 */
public class CustomProcess implements Comparable<CustomProcess> {
  private static int nextProcessId = 1; // stores the id to be assigned to the next process
  // to be created
  private final int PROCESS_ID; // unique identifier for this process
  private int burstTime; // time required by this process for CPU execution


  /**
   * initializes a constructor
   * 
   * @param burstTime - time it takes to store this process
   */
  public CustomProcess(int burstTime) {
    // checks if burstTime is a valid input
    if (burstTime <= 0) {
      throw new IllegalArgumentException();
    } else {
      this.burstTime = burstTime;
      PROCESS_ID = nextProcessId;
      nextProcessId = PROCESS_ID + 1;
    }
  }

  /**
   * Overrides the compareTo method in comparable
   */
  public int compareTo(CustomProcess o) {
    // compares customProcesses by burstTime
    if (burstTime < o.getBurstTime()) {
      return -1;
    } else if (burstTime > o.getBurstTime()) {
      return 1;
    } else {
      // if burstTimes are equal than we compare processId
      if (PROCESS_ID < o.getProcessId()) {
        return -1;
      } else if (PROCESS_ID > o.getProcessId()) {
        return 1;
      } else {
        return 0;
      }
    }
  }

  /**
   * Returns a String representation of this CustomProcess
   * 
   * @return a string representation of this CustomProcess
   */
  public String toString() {
    return "p" + this.PROCESS_ID + "(" + this.burstTime + ")";
  }

  /**
   * Gets process Id
   * 
   * @return the ProcessId
   */
  public int getProcessId() {
    return PROCESS_ID;
  }

  /**
   * gets burst time
   * 
   * @returns the burst time
   */
  public int getBurstTime() {
    return burstTime;
  }
}
