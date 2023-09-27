
/**
 * Below written code is a simple program that reads students' marks froma given text file, calculates total marks for each student, 
 * prints the total marks less than a certain threshold, prints the top 5 students with highest marks and top students with lowest total marks.
 * And finally there will be a simple menu system to allow users to select and execute each functions.
 * @author (Gagan Shrestha)
 * @student id number (24253736)
 * @version (23/09/2023)
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Student {
    // Student class to store student information
    private String unitName;
    private String studentID;
    private String name;
    private double[] marks;

    public Student(String unitName, String studentID, String name, double[] marks) {
        this.unitName = unitName;
        this.studentID = studentID;
        this.name = name;
        this.marks = marks;
    }

    public String getUnitName() {
        return unitName;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public double[] getMarks() {
        return marks;
    }
}

public class StudentMarks {

    public static ArrayList<Student> readStudentData(String filename) {
        ArrayList<Student> studentList = new ArrayList<>();
        //Functional requirement 1: Reading the student data from file
        try {
            // Open the file for reading
            File file = new File(filename);
                   
            if (file.exists()) {
                System.out.println("File found. Reading data from " + filename + "...Successful");
                Scanner scanner = new Scanner(file);

                // Read the unit name and move to the next line
                String unitName = scanner.nextLine().trim();
                scanner.nextLine();  // Skip header line
                
                // Read Student data from file
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(",");
                    String lastName = parts[0].trim();
                    String firstName = parts[1].trim();
                    String studentID = parts[2].trim();

                    double[] marks = new double[3];
                    for (int i = 0; i < 3; i++) {
                        if (i + 3 < parts.length && !parts[i + 3].trim().isEmpty()) {
                            marks[i] = Double.parseDouble(parts[i + 3].trim());
                        } else {
                            marks[i] = -1.0;  // Mark as -1 if empty
                        }
                    }

                    studentList.add(new Student(unitName, studentID, lastName + ", " + firstName, marks));
                }
                //Close the Scanner
                scanner.close();
            }else{
                System.out.println("File not found: " + filename);
            }
        } catch (FileNotFoundException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }

        return studentList;
    }
    
    //Functional Requirement 2: Print Student data and calculated total marks of student
    public static void calculateTotalMarksAndDisplay(ArrayList<Student> studentList) {
        for (Student student : studentList) {
            double totalMarks = 0;

            for (double mark : student.getMarks()) {
                if (mark != -1.0) {
                    totalMarks += mark;
                }
            }

            System.out.println("Name: " + student.getName() +
                    ", Student ID: " + student.getStudentID() +
                    ", Marks: " + java.util.Arrays.toString(student.getMarks()) +
                    ", Total Mark: " + totalMarks);
        }
    }
    
    //Functional Requirement 3:  Displays the list of students with total marks less than a certain threshold
    public static void printStudentsBelowThreshold(ArrayList<Student> studentList, double threshold){
        for(Student student : studentList){
            double totalMarks = 0;
            
            for(double mark : student.getMarks()){
                 if(mark != -1.0){
                    totalMarks += mark;
                }
            } 
            
            if(totalMarks < threshold){
                System.out.println("Name: " + student.getName() +
                    ", Student ID: " + student.getStudentID() +
                    ", Marks: " + java.util.Arrays.toString(student.getMarks()) + 
                    ", Total Mark: " + totalMarks);
            }
        }
    }

    //Functional Requirement 5: Creating Menus
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> studentList = null;
        //This function creates Menu
        while(true){
        System.out.println("Menu:");
        System.out.println("1. Read Student Data from a file");
        System.out.println("2. Calculate total marks and Display Student Data");
        System.out.println("3. Display Students with total marks below a threshold");
        System.out.println("4. Exit");
        
        System.out.print("Enter Your Choice: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); //Concume the newline character
        
        switch(choice){//Created Switch to choose/create menu functions
            case 1:
                System.out.print("Enter the file name(Use this file Name: prog5001_students_grade_2022.csv): ");
                String filename = scanner.nextLine();
                studentList = readStudentData(filename);
                break;
            case 2:
                while (true) {
                    if (studentList != null) {
                        calculateTotalMarksAndDisplay(studentList);
                        break;  // Exit the loop if the operation is successful
                    } else {
                        System.out.println("Please read student data first (option 1).");
                        System.out.print("Press Enter to return to the main menu...");
                        scanner.nextLine();  // Consume the newline character
                        scanner.nextLine();  // Wait for user input
                    break;  // Exit the loop
                    }
                }
                break;  // Break added to exit the switch statement
            case 3:
                if(studentList != null){
                    System.out.print("Please Enter the Threshold: ");
                    double threshold = scanner.nextDouble();
                    printStudentsBelowThreshold(studentList, threshold);
                }else{
                    System.out.println("Please read student data first (option 1).");
                }
            case 4:
                scanner.close();
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Try again.");
                break;
            }//end of switch
            
        }
    }
}

