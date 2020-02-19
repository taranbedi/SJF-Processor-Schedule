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
 * Priority Waiting Queue class that implements WaitingQueueADT
 * 
 * @author Taran Bedi
 *
 */
public class WaitingProcessQueue implements WaitingQueueADT {
  private static final int INITIAL_CAPACITY = 20; // the initial capacity of this
  // waiting process queue
  private CustomProcess[] data; // min heap-array storing the CustomProcesses
  // inserted in this WaitingProcessQueue.
  // data is an oversize array
  private int size; // number of CustomProcesses stored in this WaitingProcessQueue

  /**
   * Creates WaitingProcessQueue constructor and initializes all the variables
   */
  public WaitingProcessQueue() {
    if (INITIAL_CAPACITY == 0) {
      throw new IllegalArgumentException();
    } else {
      data = new CustomProcess[INITIAL_CAPACITY];
      size = 0;
    }
  }

  /**
   * helper method that helps percolate up the min heap array when trying to insert a node
   * 
   * @param index - the index at which to start percolating up
   */
  private void minHeapPercolateUp(int index) {
    int parentIndex;
    // algorithim to percolate up the tree
    while (index > 0) {
      parentIndex = (index - 1) / 2;
      if (data[index].compareTo(data[parentIndex]) >= 0) {
        return;
      } else {
        CustomProcess x = data[index];
        data[index] = data[parentIndex];
        data[parentIndex] = x;
        index = parentIndex;
      }

    }
  }

  /**
   * helper method that helps percolate down the min heap array when trying to remove a node
   * 
   * @param index - the index at which to start percolating down
   */
  private void minHeapPercolateDown(int index) {
    int child = 2 * index + 1;
    CustomProcess value = data[index];
    int currentIndex = index;
    while (child < size) {
      CustomProcess min = value;
      int minIndex = -1;
      // checks both the left child and the right child of the node
      for (int i = 0; i < 2 && i + child < size; i++) {
        if (data[i + child].compareTo(min) < 0) {
          min = data[i + child];
          minIndex = i + child;
        }
      }
      // base case
      if (min == value) {
        return;
      } else {
        CustomProcess x = data[currentIndex];
        data[currentIndex] = data[minIndex];
        data[minIndex] = x;
        currentIndex = minIndex;
        child = currentIndex * 2 + 1;
      }
    }
  }

  /**
   * inserts a node into the min heap array
   * 
   * @param newObject - object to be inserted in the min heap array
   * @throws - NullPointerException of argument is null
   */
  @Override
  public void insert(Comparable newObject) {
    // makes sure object being inserted is valid
    if (newObject == null) {
      throw new NullPointerException();
    }
    // doubles the length of the array when array has run out of space
    if (size >= data.length) {
      CustomProcess temp[] = new CustomProcess[data.length * 2];
      for (int i = 0; i < data.length; i++) {
        temp[i] = data[i];
        data = temp;
      }
    }
    data[size] = (CustomProcess) newObject;
    // calls percolateup helper method to find out correct place to insert newObject
    minHeapPercolateUp(size);
    size++;
  }

  /**
   * removes and returns the element with the highest priority.
   * 
   * @return the removed element
   * @throws java.util.NoSuchElementException with a descriptive error message if this waiting queue
   *         is empty
   */
  public Comparable<CustomProcess> removeBest() {
    // makes sure list isn't empty
    if (isEmpty()) {
      throw new NoSuchElementException();
    } else {
      // create a temp value to store the root
      CustomProcess temp = data[0];
      data[0] = data[size - 1];
      data[size - 1] = null;
      size--;
      // readjust the min heap array after we remove the root
      minHeapPercolateDown(0);
      return temp;
    }
  }

  /**
   * returns without removing the element with the highest priority.
   * 
   * @return the element with the highest priority
   * @throws java.util.NoSuchElementException with a descriptive error message if this waiting queue
   *         is empty
   */
  public Comparable<CustomProcess> peekBest() {
    // makes sure list isn't empty
    if (isEmpty()) {
      throw new NoSuchElementException();// TODO Auto-generated method stub
    } else {
      return data[0];
    }

  }


  /**
   * returns size of priority queue
   * 
   * @return the size of priority queue
   */
  public int size() {
    return size;
  }

  /**
   * checks whether this waiting queue is empty or not.
   * 
   * @return true if this waiting queue is empty, false otherwise
   */
  public boolean isEmpty() {
    if (data[0] == null) {
      return true;
    } else {
      return false;
    }
  }


}
