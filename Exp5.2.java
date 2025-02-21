import java.io.*;
class Student implements Serializable {
    private static final long serialVersionUID = 1L; 
    private int id;
    private String name;
    private double gpa;
    public Student(int id, String name, double gpa) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }
    @Override
    public String toString() {
        return "Student ID: " + id + ", Name: " + name + ", GPA: " + gpa;
    }
}
public class StudentSerialization {
    public static void serializeStudent(Student student) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("student.ser"))) {
            oos.writeObject(student);
            System.out.println("Student object has been serialized and saved to file.");
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        } catch (IOException e) {
            System.out.println("Error: IOException occurred during serialization.");
        }
    }
    public static Student deserializeStudent() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("student.ser"))) {
            Student student = (Student) ois.readObject();
            System.out.println("Student object has been deserialized.");
            return student;
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        } catch (IOException e) {
            System.out.println("Error: IOException occurred during deserialization.");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: Class not found.");
        }
        return null; 
    }
    public static void main(String[] args) {
        Student student1 = new Student(1, "John Doe", 3.75);
        serializeStudent(student1);
        Student deserializedStudent = deserializeStudent();
        if (deserializedStudent != null) {
            System.out.println("Deserialized Student Details:");
            System.out.println(deserializedStudent);
        }
        System.out.println("\nTest Case 2: Attempting to deserialize from a non-existent file.");
        new File("student.ser").delete();
        deserializeStudent();
        System.out.println("\nTest Case 3: Simulating ClassNotFoundException.");
        deserializeStudent();
    }
}
