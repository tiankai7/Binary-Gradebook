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

public class Gradebook implements Iterable<StudentRecord>{
  
  /**
   * Name of this course
   */
  public final String course;
  /**
   * Minimum passing grade for this course
   */
  public final double PASSING_GRADE;
  /**
   * Root node of the BST
   */
  private BSTNode<StudentRecord> root;
  /**
   * Total number of StudentRecords stored in this Gradebook
   */
  private int size;
  /**
   * Indicates whether the passing grade iterator is enabled (meaning equals true if this 
   * Gradebook is set to iterate through passing grades only).
   */
  private boolean passingGradeIteratorEnabled;
  
  /**
   * Constructs an empty Gradebook for a given course and define its passing grade. We assume that 
   * that this gradebook iterates through every stored grade (meaning that the passingGradeIterator 
   * is not enabled by default).
   * @param course name of the course
   * @param passingGrade  passing grade of the course
   */
  public Gradebook(String course, double passingGrade) {
    if(course == null || course.isBlank()) {
      throw new IllegalArgumentException("course name cannot be null");
    }
    if(passingGrade < 0.0 || passingGrade > 100.0) {
      throw new IllegalArgumentException("passing grade must between 0 and 100");
    }
    this.course = course;
    this.PASSING_GRADE = passingGrade;
    this.root = null;
    this.size = 0;
    this.passingGradeIteratorEnabled = false;
  }
  
  /**
   * Enables the passing grade iterator
   */
  public void enablePassingGradeIterator(){
    this.passingGradeIteratorEnabled = true;
  }
  /**
   * Disables the passing grade iterator
   */
  public void disablePassingGradeIterator() {
    this.passingGradeIteratorEnabled = false;
  }
  /**
   * Checks whether this Gradebook is empty
   * @return true if this Gradebook is empty and false otherwise
   */
  public boolean isEmpty() {
    return this.size == 0;
  }
  /**
   * Returns the size of this Gradebook
   * @return the total number of StudentRecord objects stored in this Gradebook
   */
  public int size() {
    return this.size;
  }
  /**
   * Adds a new StudentRecord to this Gradebook. This method tries to add record to this tree and
   *  updates its size accordingly. Be sure to update the root to the BSTNode returned by the 
   *  addStudentHelper() method.
   * @param record to be added to this Gradebook
   */
  public void addStudent(StudentRecord record) {
    this.root = addStudentHelper(record, this.root);
    this.size++;
  }
  /**
   * Recursive helper method to add a record to the subtree rooted at node
   * @param record new Student to add
   * @param node root of a subtree
   * @return the new root of this BST after adding the record to this tree
   */
  protected static BSTNode<StudentRecord> addStudentHelper(StudentRecord record, BSTNode<StudentRecord> node){
    // base case
    if(node == null) { 
      
      return new BSTNode<>(record);
      
    }
    if(node.getData().equals(record)) {
      throw new IllegalStateException("already exist record");
    }
    // recursion
    else if(record.compareTo(node.getData()) < 0) {
      node.setLeft(addStudentHelper(record, node.getLeft()));
    }
    else {
      node.setRight(addStudentHelper(record, node.getRight()));
    }
    return node;
    
  }
  /**
   * Finds a StudentRecord given the associated email address
   * @param email email address of a student
   * @return the Student associated with the email argument if there is a match, or null otherwise
   */
  public StudentRecord lookup(String email) {
    StudentRecord current = new StudentRecord(email, email, 0);
    return lookupHelper(current, this.root);
  }
  /**
   * Recursive helper method which looks for a given StudentRecord given in the BST rooted at node
   * @param target the StudentRecord to search in the subtree rooted at node
   * @param node root of a subtree of this BST
   * @return the StudentRecord which matches the one passed as input if a match is found in the
   *  subtree rooted at node, or null if no match found
   */
  protected static StudentRecord lookupHelper(StudentRecord target, BSTNode<StudentRecord> node) {
    // base case
    if(node == null) {
      return null;
    }
    if(target.compareTo(node.getData()) == 0) {
      return node.getData();
    }
    // recursion
    else if(target.compareTo(node.getData()) < 0) {
      return lookupHelper(target, node.getLeft());
    }
    else {
      return lookupHelper(target, node.getRight());
    }
  }
  /**
   * Searches for the StudentRecord associated with the provided input email in this BST and checks 
   * whether it has a passing grade for this course. The student with the provided email passes the 
   * course if their grade is greater or equal to this Gradebook's passingGrade data field. 
   * Returns: "No match found." if no match found with email in this Gradebook If a matching 
   * StudentRecord is found, this method returns: matchingStudent.toString() + ": PASS" if the 
   * student has a passing grade matchingStudent.toString() + ": FAIL" if the student does not 
   * have a passing grade For instance, "Charlie (charlie@wisc.edu) 85: PASS" 
   * "Andy (andy@wisc.edu) 56: FAIL"
   * @param email the email of the StudentRecord to find
   * @return A String indicating whether the student having the input email has a passing or failing grade.
   */
  public String checkPassingCourse(String email) {
    // if not found
    if(lookup(email) == null) {
      return "No match found.";
    }
    StudentRecord matchingStudent = lookup(email);
    // if pass
    if(matchingStudent.getGrade() >= this.PASSING_GRADE) {
      return matchingStudent.toString() + ": PASS";
    }
    // if fail
    else {
      return matchingStudent.toString() + ": FAIL";
    }
  }
  /**
   * Returns the StudentRecord with the lexicographically smallest email in this BST, 
   * or null if this Gradebook is empty.
   * @return the StudentRecord with the lexicographically smallest email in this BST
   */
  protected StudentRecord getMin(){
    if(this.root == null) {
      return null;
    }
    else {
      return getMinHelper(root);
    }
  }
  /**
   * Returns the smallest StudentRecord (with respect to the result of Student.compareTo() method) 
   * in the subtree rooted at node
   * @param node root of a subtree of a binary search tree
   * @return the smallest Student in the subtree rooted at node, or null if the node is null
   */
  protected static StudentRecord getMinHelper(BSTNode<StudentRecord> node) {
    // base case
    if(node.getLeft() == null) {
      return node.getData();
    }
    // recursion
    else {
      return getMinHelper(node.getLeft());
    }
  }
  /**
   * Returns the successor of a target StudentRecord (smallest value in the BST that is larger than 
   * the target), or returns null if there is no successor in this Gradebook.
   * @param target  the StudentRecord to find the successor of
   * @return the successor of the target in the Gradebook, or null if none exists
   */
  protected StudentRecord successor(StudentRecord target) {
    if(root == null) {return null;}
    return successorHelper(target, root);
  }
  /**
   * Returns the successor of a target StudentRecord within the subtree (smallest value in the 
   * subtree that is larger than the target), or returns null if there is no successor in this 
   * subtree.
   * @param target the StudentRecord to find the successor of
   * @param node the subtree to search for a successor to the target
   * @return the successor of the target in the subtree rooted at node, or null if none exists
   */
  protected static StudentRecord successorHelper(StudentRecord target, BSTNode<StudentRecord> node) {
    if(node == null) {
      return null;
    }
    
    if(target.compareTo(node.getData()) < 0) {
      StudentRecord current = node.getData();
      StudentRecord next = successorHelper(target, node.getLeft());
      if(next != null) {
        return next;
      }
      else {
        return current;
      }
    }
    else {
      return successorHelper(target, node.getRight());
    }
  }
  /**
   * Deletes a StudentRecord from this Gradebook given their email, or throws a 
   * NoSuchElementException if there is no StudentRecord with the given email.
   * @param email the email of the student to delete
   */
  public void removeStudent(String email) {
    StudentRecord toDrop = new StudentRecord(email, email, 0);
    if(lookup(email) == null || this.size == 0) {
      throw new NoSuchElementException("no matching record");
    }
    root = removeStudentHelper(toDrop, root);
    this.size--;
  }
  /**
   * Deletes the matching StudentRecord with toDrop if it is found within this tree, or otherwise 
   * throws a NoSuchElementException.
   * @param toDrop the StudentRecord to be removed from this tree
   * @param node the root of the subtree to remove the student from
   * @return the new root of the subtree after removing the matching StudentRecord
   */
  protected static BSTNode<StudentRecord> removeStudentHelper(StudentRecord toDrop, BSTNode<StudentRecord> node){
    // base case 
    // reach to the bottom
    if(node == null) {
      return null;
    }
    // find match
    if(toDrop.compareTo(node.getData()) == 0) {
      // node with two children
      if(node.getRight() != null && node.getLeft() != null) {
        StudentRecord successorNode = successorHelper(node.getData(), node);
        node.setData(successorNode);
        node.setRight(removeStudentHelper(successorNode, node.getRight()));
      }
      // node with one or zero child
      else if(node.getRight() != null) {
        node = node.getRight();
      }
      else {
        node = node.getLeft();
      }
    }
    // recursion
    // find left
    else if(toDrop.compareTo(node.getData()) < 0) {
      node.setLeft(removeStudentHelper(toDrop, node.getLeft()));;
    }
    // find right
    else {
      node.setRight(removeStudentHelper(toDrop, node.getRight()));
    }
    
    return node;
  }
  /**
   * Returns a String representation of the contents of this Gradebook in increasing order
   * @return an in-order String representation of this Gradebook
   */
  @Override
  public String toString() {
    return toStringHelper(root);
  }
  /**
   * Returns a String representation of the subtree rooted at node in increasing order
   * @param node root of a subtree
   * @return an in-order String representation of the subtree rooted at node
   */
  protected static String toStringHelper(BSTNode<StudentRecord> node) {
    if (node == null) {
      return ""; 
    }
    String leftSubtree = toStringHelper(node.getLeft());
    String rightSubtree = toStringHelper(node.getRight());
    return leftSubtree 
          + node.getData().toString() + "\n"
          + rightSubtree;
  }

  /**
   * Returns a String representation of the structure of this BST. The String should print the 
   * StudentRecords in decreasing order (largest-to-smallest), and each StudentRecord should have 
   * an indentation (space from the left side of the screen to the student names) that increases by 
   * four (4) spaces for each level of depth in the tree. For instance, the root has no indentation, 
   * the root's left subtree has an indentation of 4 spaces, and the root's left subtree's right 
   * child has an indentation of 8 spaces.
   * @return a String representation of the structure of this BST
   */
  public String prettyString() {
    return prettyStringHelper(root,0);
  }
  /**
   * Returns a decreasing-order String representation of the structure of this subtree, 
   * indented by four spaces for each level of depth in the larger tree.
   * @param node  current subtree within the larger tree
   * @param depth depth of the current node within the larger tree
   * @return a String representation of the structure of this subtree
   */
  protected static String prettyStringHelper(BSTNode<StudentRecord> node, int depth) {
    if (node == null) {     
      return ""; 
    }
    String indent = " ".repeat(depth*4); 
    return prettyStringHelper(node.getRight(), depth + 1) + indent + node.getData().name
        + "\n" + prettyStringHelper(node.getLeft(), depth + 1);
  }
  /**
   * Returns true if this BST has an identical layout (all subtrees equal) to the given tree.
   * @param node tree to compare this Gradebook to
   * @return true if the given tree looks identical to the root of this Gradebook
   */
  public boolean equalBST(BSTNode<StudentRecord> node) {
    return root == node || (root != null && root.equals(node));
  }
  

  /**
   * Returns an iterator over the student records in this gradebook in the increasing order. 
   * If the passing grade iterator is enabled, this method returns an iterator that iterates 
   * through records with passing grades only while skipping the ones that fail to pass.
   * @return an Iterator over the elements in this gradebook in proper sequence.
   */
  @Override
  public Iterator<StudentRecord> iterator() {
    if(this.passingGradeIteratorEnabled) {
      return new PassingGradeIterator(this);
    }
    else {
      return new GradebookIterator(this);
    }
  }
  

}
