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
        return new ResponseEntity<>(studentService.getStudents(), HttpStatus.FOUND);
    }
    @PostMapping("/register")
    public ResponseEntity<Student> registerStudent(@RequestBody AddStudentRequest student) {
        try {
            return new ResponseEntity<>(studentService.addStudent(student), HttpStatus.CREATED);
        }
        catch (StudentExistException exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@RequestBody UpdateStudentRequest  student, @PathVariable Long id) {
        try {
            return  new ResponseEntity<>(studentService.updateStudent(student,id), HttpStatus.OK);
        }
        catch (StudentNotFoundException exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete/{id}")
    public void deleteStudent(@PathVariable Long id) {
           studentService.deleteStudent(id);
    }

    @GetMapping("/one/student/{id}")
    public Student getStudentById(@PathVariable Long id){
        return studentService.getStudentById(id);
    }

}
