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
import java.util.NoSuchElementException;

public class PassingGradeIterator extends GradebookIterator {
  private StudentRecord next;
  private double passingGrade;
  
  public PassingGradeIterator(Gradebook gradebook) {
    super(gradebook);
    this.passingGrade = gradebook.PASSING_GRADE;
    advanceToNextPassingGrade();
  }

  private void advanceToNextPassingGrade() {
    while(super.hasNext()) {
      StudentRecord candidate = super.next();
      if(candidate.getGrade() >= this.passingGrade) {
        this.next = candidate;
        return;
      }
    }
    this.next = null;  
  }
  
  public boolean hasNext() {
    return this.next != null;
  }
  
  public StudentRecord next() {
    if(this.next == null) {
      throw new NoSuchElementException("No more elements");
    }
    StudentRecord current = this.next;
    advanceToNextPassingGrade();
    return current;
  }

}
