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
public class StudentRecord implements Comparable<StudentRecord> {
  
  public final String name;
  public final String email;
  private double grade;
  
  public StudentRecord(String name, String email, double grade) {
    if (name == null || name.isBlank() || email == null || email.isBlank()) { 
      throw new IllegalArgumentException("Name and email must not be null or blank."); 
    }
    if (grade < 0.0 || grade > 100.0) {
      throw new IllegalArgumentException("Grade must be between 0.0 and 100.0.");
    }
    this.name = name;
    this.email = email;
    this.grade = grade;
    
  }
  
  public double getGrade() {
    return this.grade;
  }
  
  public void setGrade(double grade) {
    this.grade = grade;
  }
  
  @Override
  public String toString() {
    String output = "";
    output += this.name + " (" + this.email + "): " + this.grade;
    return output;
  }
  
  @Override
  public int compareTo(StudentRecord other) {
    // TODO Auto-generated method stub
    
    return this.email.compareTo(other.email);
  }
  
  public boolean equals(Object o) {
    if(o instanceof StudentRecord) {
      StudentRecord newStudent = (StudentRecord) o;
      if(newStudent.email.equals(this.email)) return true;
    }
    return false;
  }

}
