//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P09 Binary Gradebook
// Course:   CS 300 Spring 2024
//
// Author:   Tiankai Tong
// Email:    ttong24@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
// 
// Partner Name:    (name of your pair programming partner)
// Partner Email:   (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons:         (identify each by name and describe how they helped)
// Online Sources:  (identify each by URL and describe how it helped)
//                 https://cs300-www.cs.wisc.edu/sp24/p09/doc/package-summary.html
//
///////////////////////////////////////////////////////////////////////////////
import java.util.Iterator;
import java.util.NoSuchElementException;

public class GradebookIterator implements Iterator<StudentRecord>{
  private StudentRecord current;
  private Gradebook gradebook;
  
  public GradebookIterator(Gradebook gradebook) {
    this.gradebook = gradebook;
    this.current = this.gradebook.getMin();
  }

  @Override
  public boolean hasNext() {
    // TODO Auto-generated method stub
    return this.current != null;
  }

  @Override
  public StudentRecord next() {
    if(this.current == null) {
      throw new NoSuchElementException("No more elements");
    }
    StudentRecord toReturn = this.current;
    this.current = gradebook.successor(current);
    return toReturn;
  }

}
