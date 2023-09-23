
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> studentList = null;
        //This function creates Menu
        while(true){
        System.out.println("Menu:");
        System.out.println("1. Read Student Data from a file");
        System.out.println("2. Exit");
        
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
                scanner.close();
                System.exit(0);
                break;
            }
        }
    }
}

