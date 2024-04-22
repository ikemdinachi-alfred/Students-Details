package com.alfredTech.studentDetails.services;

import com.alfredTech.studentDetails.data.model.Student;
import com.alfredTech.studentDetails.dtos.request.AddStudentRequest;
import com.alfredTech.studentDetails.dtos.request.UpdateStudentRequest;

import java.util.List;
public interface StudentService {
    Student addStudent(AddStudentRequest addStudentRequest);
    Student updateStudent(UpdateStudentRequest student,Long id);
    void deleteStudent(Long id);
    List<Student> getStudents();
    Student getStudentById(Long id);

}
