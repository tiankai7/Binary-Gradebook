/**
 * Demo class for Gradebook
 */
public class Demo {
    /**
     * Runs a demo of the Gradebook class
     */
    public static void demo() {
        Gradebook gradebook = new Gradebook("cs300", 60.0);
        gradebook.addStudent(new StudentRecord("Noah", "noah@wisc.edu", 75.0));
        gradebook.addStudent(new StudentRecord("Samuel", "samuel@wisc.edu", 96.0));
        gradebook.addStudent(new StudentRecord("Mason", "mason@wisc.edu", 59.0));
        gradebook.addStudent(new StudentRecord("Isabella", "isabella@wisc.edu", 68.0));
        gradebook.addStudent(new StudentRecord("Sophia", "sophia@wisc.edu", 98.0));
        gradebook.addStudent(new StudentRecord("Alexander", "alexander@wisc.edu", 97.0));
        gradebook.addStudent(new StudentRecord("Lucas", "lucas@wisc.edu", 72.0));
        gradebook.addStudent(new StudentRecord("Henry", "henry@wisc.edu", 55.0));
        gradebook.addStudent(new StudentRecord("Ava", "ava@wisc.edu", 80.0));
        gradebook.addStudent(new StudentRecord("Olivia", "olivia@wisc.edu", 88.0));

        System.out.println("List of student grades:");
        System.out.println(gradebook);
        System.out.println();

        System.out.println("Visualization of BST:");
        System.out.println(gradebook.prettyString());
        System.out.println();

        System.out.println(gradebook.lookup("olivia@wisc.edu"));
        System.out.println();

        System.out.println(gradebook.lookup("alexander@wisc.edu"));
        System.out.println();

        System.out.println(gradebook.lookup("zak@wisc.edu"));
        System.out.println();

        System.out.println(gradebook.checkPassingCourse("sophia@wisc.edu"));
        System.out.println();

        System.out.println(gradebook.checkPassingCourse("mason@wisc.edu"));
        System.out.println();

        gradebook.removeStudent("isabella@wisc.edu");
        gradebook.removeStudent("alexander@wisc.edu");
        gradebook.removeStudent("noah@wisc.edu");

        System.out.println("BST after removal:");
        System.out.println(gradebook.prettyString());
        System.out.println();

        System.out.println("GradebookIterator traversal:");
        for (StudentRecord r : gradebook) {
            System.out.println(r);
        }
        System.out.println();

        gradebook.enablePassingGradeIterator();
        System.out.println("PassingGradeIterator traversal:");
        for (StudentRecord r : gradebook) {
            System.out.println(r);
        }
    }

    /**
     * main method
     *
     * @param args input arguments
     */
    public static void main(String[] args) {
        demo();
    }

}