package com.alfredTech.studentDetails.controllers;
import com.alfredTech.studentDetails.data.model.Student;
import com.alfredTech.studentDetails.dtos.request.AddStudentRequest;
import com.alfredTech.studentDetails.dtos.request.UpdateStudentRequest;
import com.alfredTech.studentDetails.exceptions.StudentExistException;
import com.alfredTech.studentDetails.exceptions.StudentNotFoundException;
import com.alfredTech.studentDetails.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Controller
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/view_all")
    public ResponseEntity<List<Student>>getStudents() {
        return new ResponseEntity<>(studentService.getStudents(), HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<String> registerStudent(@RequestBody AddStudentRequest student) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(studentService.addStudent(student).toString());
        }
        catch (StudentExistException exception) {
            System.out.println(exception.getMessage());
        }
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User details already exist");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@RequestBody UpdateStudentRequest  student, @PathVariable Long id) {
        try {
            return  ResponseEntity.status(HttpStatus.OK).body(studentService.updateStudent(student,id));
        }
        catch (StudentNotFoundException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
           studentService.deleteStudent(id);
           return ResponseEntity.status(HttpStatus.OK).body("The student with id " + id + " was deleted.");
    }

    @GetMapping("/one/student/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(studentService.getStudentById(id));
        }catch (StudentNotFoundException exception){
            System.out.println(exception.getMessage());
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
