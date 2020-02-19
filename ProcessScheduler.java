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
import java.util.Scanner;

/**
 * Main class that creates the process schedule
 * 
 * @author Taran Bedi
 *
 */
public class ProcessScheduler {

  private int currentTime; // stores the current time after the last run
  private int numProcessesRun; // stores the number of processes run so far
  private WaitingProcessQueue queue; // this processing unit’s queue

  /**
   * creates ProcessScheduler constructor and initializes all the variables
   * 
   */
  public ProcessScheduler() {
    currentTime = 0;
    numProcessesRun = 0;
    queue = new WaitingProcessQueue();
  }



  /**
   * This method inserts the given process in the WaitingProcessQueue queue.
   * 
   * @param process - process to be run
   */
  public void scheduleProcess(CustomProcess process) {
    queue.insert(process);
    numProcessesRun++;
  }


  /**
   * runs the ready processes already scheduled in this processScheduler’s queue
   * 
   * @returns - a string representation of a log of processes
   */
  public String run() {
    String x = "";
    if (queue.size() == 1) {
      x += "Starting " + queue.size() + " process\n\n";
    } else {
      x += "Starting " + queue.size() + " processes\n\n";
    }
    System.out.print(queue.size());
    // makes sure to go through the entire array and remove each element
    while (queue.size() != 0) {
      CustomProcess temp = (CustomProcess) queue.removeBest();
      x += "Time " + currentTime + " : Process ID " + temp.getProcessId() + " Starting.\n";
      currentTime += temp.getBurstTime();
      x += "Time " + currentTime + " : Process ID " + temp.getProcessId() + " Completed.\n";
    }

    x += "\nTime " + currentTime + " : All scheduled processes completed.\n";
    // returns string representation of all the processes being made
    return x;
  }

  /**
   * Helper method that prints out the introduction to the program
   */
  private static void introHelperMethod() {
    System.out.println("Enter command:\n" + "[schedule <burstTime>] or [s <burstTime>]\n"
        + "[run] or [r]\n" + "[quit] or [q]");

  }

  /**
   * Helper method that prints out the last statement when exiting the program
   */
  private static void quitHelperMethod() {
    System.out.print("Goodbye!\n");
  }


  /**
   * Driver method for the program and creates the process schedule
   * 
   * @param args
   */
  public static void main(String[] args) {
    ProcessScheduler x = new ProcessScheduler();
    Scanner sc = new Scanner(System.in);
    // call the intro help method to set up introduction
    introHelperMethod();
    String input = "";
    input = sc.next();
    // makes sure user can keep inserting calls as long as they don't quit the program
    while (!(input.equals("q") || input.equals("quit"))) {
      if (input.equals("r") || input.equals("run")) {
        System.out.println(x.run());
      } else if (input.equals("Schedule") || input.equals("s")) {
        String input2 = "";
        input2 = sc.next();
        try {
          int a = Integer.parseInt(input2);
          CustomProcess t = new CustomProcess(a);
          WaitingProcessQueue test = new WaitingProcessQueue();
          test.insert(t);
          x.queue = test;
          x.currentTime += a;
          x.numProcessesRun++;
          System.out.println("Process ID " + t.getProcessId() + " scheduled. Burst Time = "
              + t.getBurstTime() + "\n");

        } catch (NumberFormatException e) {
          // ensures that the input for schedule was an integer
          System.out.println("WARNING: burst time MUST be an integer!\n");
        }
      }
      input = sc.next();
    }
    System.out.println(x.numProcessesRun + " processes run in " + x.currentTime
        + " units of time!\n" + "Thank you for using our scheduler!");
    quitHelperMethod();
  }
}
