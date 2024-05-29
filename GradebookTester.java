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

public class GradebookTester {
  public static boolean constructorTester() {
    Gradebook gb = new Gradebook("A", 50.0);
    if(gb.PASSING_GRADE != 50.0 || !gb.course.equals("A")) {
      return false;
    }
    return true;
  }
  public static boolean isEmptySizeAddTester() {
    Gradebook gb = new Gradebook("B", 60.0);
    if(!gb.isEmpty()) {
      return false;
    }
    StudentRecord record1 = new StudentRecord("Mike", "mike@email.com", 70.0);
    gb.addStudent(record1);
    if(gb.isEmpty() || gb.size() != 1) {
      return false;
    }
    try {
      StudentRecord record2 = new StudentRecord("Mike", "mike@email.com", 70.0);
      gb.addStudent(record2);
      return false;  
    } catch (IllegalStateException e) {
      if (gb.size() != 1) {
          return false;   
      }
    }
    return true;
  }
  public static boolean lookupTester() {
    Gradebook gb = new Gradebook("B", 60.0);
    gb.addStudent(new StudentRecord("Bob", "bob@email.com", 65));
    gb.addStudent(new StudentRecord("Mike", "mike@email.com", 60.0));
    gb.addStudent(new StudentRecord("Alice", "alice@email.com", 70));
    if(gb.lookup("alice@email.com") == null) {
      return false;
    }
    if(gb.lookup("bob@email.com") == null) {
      return false;
    }
    if(gb.lookup("mike@email.com") == null) {
      return false;
    }
    if(gb.lookup("null@email.com") != null) {
      return false;
    }
    return true;
  }
  public static boolean toStringTester() {
    Gradebook gb = new Gradebook("A", 70.0);
    String empty = gb.toString();
    if(!empty.equals("")) {
      return false;
    }
    gb.addStudent(new StudentRecord("Mike", "mike@email.com", 60.0));
    gb.addStudent(new StudentRecord("Bob", "bob@email.com", 80.0));
    gb.addStudent(new StudentRecord("Alice", "alice@email.com", 70));
    String result = gb.toString();
    String expect = "Alice (alice@email.com): 70.0" + "\n"  
        + "Bob (bob@email.com): 80.0" + "\n" 
        + "Mike (mike@email.com): 60.0" + "\n";
    if(!result.equals(expect)) {
      return false;
    }
    return true;
  }
  public static boolean prettyStringTester() {
    Gradebook gb = new Gradebook("A", 70.0);
    gb.addStudent(new StudentRecord("Cole", "cole@email.com", 60.0));
    gb.addStudent(new StudentRecord("Alice", "alice@email.com", 70));
    gb.addStudent(new StudentRecord("Bob", "bob@email.com", 80.0));
    gb.addStudent(new StudentRecord("Mike", "mike@email.com", 70));
    String result = gb.prettyString();
    String expect = "    " + "Mike" + "\n" + "Cole" + "\n" + "        Bob\n" + "    Alice\n";
    if(!result.equals(expect)) {
      return false;
    }
    return true;
  }
  public static boolean getMinTester() {
    Gradebook gb = new Gradebook("A", 70.0);
    StudentRecord smaller = new StudentRecord("Alice", "alice@email.com", 70);
    StudentRecord larger = new StudentRecord("Cole", "cole@email.com", 60.0);
    gb.addStudent(larger);
    gb.addStudent(smaller);
    if(!gb.getMin().equals(smaller)) {
      return false;
    }
    return true;
  }
  public static boolean successorTester() {
    Gradebook gb = new Gradebook("A", 70.0);
    if(gb.successor(new StudentRecord("Alice", "alice@email.com", 70)) != null) {
      return false;
    }
    StudentRecord alice = new StudentRecord("Alice", "alice@email.com", 85);
    StudentRecord bob = new StudentRecord("Bob", "bob@email.com", 95);
    StudentRecord charlie = new StudentRecord("Charlie", "charlie@email.com", 75);
    StudentRecord dana = new StudentRecord("Dana", "dana@email.com", 65);
    gb.addStudent(bob);
    gb.addStudent(charlie);
    gb.addStudent(alice);
    gb.addStudent(dana);
    if(gb.successor(dana) != null || !gb.successor(charlie).equals(dana) || 
        !gb.successor(bob).equals(charlie) || !gb.successor(alice).equals(bob)) {
      return false;
    }
    return true;
  }
  public static boolean removeStudentTester() {
    // empty tree
    Gradebook empty = new Gradebook("Empty Course", 50.0);
    try {
        empty.removeStudent("email@notexist.com");
        return false;
    } catch (NoSuchElementException e) {
    }
    // one node tree
    Gradebook oneNodeGB = new Gradebook("One Node Course", 50.0);
    oneNodeGB.addStudent(new StudentRecord("Single", "single@node.com", 100));
    oneNodeGB.removeStudent("single@node.com");
    if(!oneNodeGB.isEmpty()) {
      return false;
    }
    // non exist node
    Gradebook gb = new Gradebook("A", 70.0);
    StudentRecord alice = new StudentRecord("Alice", "alice@email.com", 85);
    StudentRecord bob = new StudentRecord("Bob", "bob@email.com", 95);
    StudentRecord charlie = new StudentRecord("Charlie", "charlie@email.com", 75);
    StudentRecord dana = new StudentRecord("Dana", "dana@email.com", 65);
    gb.addStudent(bob);
    gb.addStudent(charlie);
    gb.addStudent(alice);
    try {
      gb.removeStudent("dana@email.com");
      return false;
    } catch (NoSuchElementException e) {    
   
    }
    // remove a leaf
    gb = new Gradebook("Leaf Removal", 60.0);
    gb.addStudent(new StudentRecord("Root", "root@bst.com", 75));
    gb.addStudent(new StudentRecord("Leaf", "leaf@bst.com", 65)); // Leaf node
    gb.removeStudent("leaf@bst.com");
    if (gb.lookup("leaf@bst.com") != null || gb.size() != 1) {
        return false;
    }
    // remove a node has one child
    gb = new Gradebook("One Child Removal", 70.0);
    gb.addStudent(new StudentRecord("Parent", "parent@bst.com", 80));
    gb.addStudent(new StudentRecord("Child", "child@bst.com", 70)); // Node with one child
    gb.removeStudent("parent@bst.com");
    if (gb.lookup("parent@bst.com") != null || gb.size() != 1 || gb.lookup("child@bst.com") == null) {
        return false;
    }
    // remove a node has two children
    gb = new Gradebook("Two Children Removal", 65.0);
    gb.addStudent(new StudentRecord("Parent", "parent@bst.com", 85));
    gb.addStudent(new StudentRecord("Left Child", "left@bst.com", 75));
    gb.addStudent(new StudentRecord("Right Child", "right@bst.com", 95)); // Node with two children
    gb.removeStudent("parent@bst.com");
    String result = gb.prettyString();
    String expect = "Right Child" + "\n" +
    "    Left Child" + "\n";
    if (gb.lookup("parent@bst.com") != null || gb.size() != 2 || gb.lookup("right@bst.com") == null 
        || gb.lookup("left@bst.com") == null || !result.equals(expect)) {
        return false;
    }
    return true;
  }
  public static boolean iteratorTester() {
    Gradebook gb = new Gradebook("A", 50.0);
    gb.addStudent(new StudentRecord("Alice", "alice@email.com", 90));
    gb.addStudent(new StudentRecord("Bob", "bob@email.com", 70));
    gb.addStudent(new StudentRecord("Charlie", "charlie@email.com", 85));

    Iterator<StudentRecord> it = gb.iterator();

    if (!it.next().name.equals("Alice") || !it.next().name.equals("Bob") || 
        !it.next().name.equals("Charlie")) { 
      return false;
    } 
    if (it.hasNext()) { 
      return false;
    }
    return true;
  }
  public static boolean passingIteratorTester() {
    Gradebook gb = new Gradebook("B", 75.0);
    gb.addStudent(new StudentRecord("Alice", "alice@email.com", 74.0));
    gb.addStudent(new StudentRecord("Bob", "bob@email.com", 76.0));
    gb.addStudent(new StudentRecord("Charlie", "charlie@email.com", 80.0));

    gb.enablePassingGradeIterator();
    Iterator<StudentRecord> passingIterator = gb.iterator();

    if (!passingIterator.hasNext()) {
      return false;
    }
    if(!passingIterator.next().name.equals("Bob") || !passingIterator.next().name.equals("Charlie")) {
      return false;
    }
    return true;
  }
  public static void main(String[] args) {
    System.out.println("Constructor Test: " + constructorTester());
    System.out.println("IsEmpty/Size/Add Test: " + isEmptySizeAddTester());
    System.out.println("Lookup Test: " + lookupTester());
    System.out.println("ToString Test: " + toStringTester());
    System.out.println("PrettyString Test: " + prettyStringTester());
    System.out.println("GetMin Test: " + getMinTester());
    System.out.println("Successor Test: " + successorTester());
    System.out.println("Remove Student Test: " + removeStudentTester());
    System.out.println("Iterator Test: " + iteratorTester());
    System.out.println("Passing Iterator Test: " + passingIteratorTester());

  }

}
