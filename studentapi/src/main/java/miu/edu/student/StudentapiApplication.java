package miu.edu.student;

import java.util.List;
import miu.edu.student.Model.Address;
import miu.edu.student.Model.Student;
import miu.edu.student.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class StudentapiApplication implements CommandLineRunner {

  @Autowired
  private StudentRepository studentRepository;

  public static void main(String[] args) {
    SpringApplication.run(StudentapiApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    // create student
    Student student = new Student(
      101,
      "Shahjahan Rasel",
      "6418192211",
      "srasel@miu.edu"
    );
    Address address = new Address(
      "1000 N. 4th St.",
      "Fairfield",
      "Iowa",
      "52557"
    );
    student.setAddress(address);
    studentRepository.save(student);
    student = new Student(109, "Joynul", "1112221111", "joynul@miu.edu");
    address = new Address("10231 Forest Ln", "Dallas", "Texas", "12345");
    student.setAddress(address);
    studentRepository.save(student);
    student = new Student(66, "Polash", "2212213333", "jj123@acme.com");
    address = new Address("7213 Park Ln", "Chicago", "Illinois", "42134");
    student.setAddress(address);
    studentRepository.save(student);

    //get students by ID
    // System.out.println(studentRepository.findById(66).get());
    // System.out.println(studentRepository.findById(101).get());

    System.out.println("-----------All student ----------------");
    System.out.println(studentRepository.findAll());

    //get students by Name
    System.out.println(studentRepository.findByName("Shahjahan Rasel"));

    //update students
    student = studentRepository.findById(101).get();
    student.setEmail("waa.lesson@gmail.com");
    studentRepository.save(student);

    //Find Student by Phone number
    System.out.println("-----------find by phone ----------------");
    System.out.println(studentRepository.findByPhoneNumber("6418192211"));

    System.out.println("-----------find by email ----------------");
    System.out.println(studentRepository.findByhEmail("joynul@miu.edu"));

    System.out.println(
      "-----------find student from certain city ----------------"
    );
    List<Student> students = studentRepository.findStudentFromCity("Fairfield");
    for (Student stud : students) {
      System.out.println(stud);
    }
  }
}
