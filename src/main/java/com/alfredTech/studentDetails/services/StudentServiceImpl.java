package com.alfredTech.studentDetails.services;

import com.alfredTech.studentDetails.data.model.Student;
import com.alfredTech.studentDetails.data.repository.StudentRepository;
import com.alfredTech.studentDetails.dtos.request.AddStudentRequest;
import com.alfredTech.studentDetails.dtos.request.UpdateStudentRequest;
import com.alfredTech.studentDetails.exceptions.StudentExistException;
import com.alfredTech.studentDetails.exceptions.StudentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;


    @Override
    public Student addStudent(AddStudentRequest addStudentRequest) {
        Student student = new Student();
        student.setFirstName(addStudentRequest.getFirstName());
        student.setLastName(addStudentRequest.getLastName());
        student.setEmail(addStudentRequest.getEmail());
        student.setCohort(addStudentRequest.getCohort());
        if (studentExist(addStudentRequest.getEmail())) {
            throw new StudentExistException("Student with the email " + addStudentRequest.getEmail() + " already exist");
        }
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(UpdateStudentRequest updateStudentRequest, Long id) {
        return studentRepository.findById(id).map(student -> {
            student.setFirstName(updateStudentRequest.getFirstName());
            student.setLastName(updateStudentRequest.getLastName());
            student.setEmail(updateStudentRequest.getEmail());
            student.setCohort(updateStudentRequest.getCohort());
            return studentRepository.save(student);
        }).orElseThrow(() -> new StudentNotFoundException("Student with id " + updateStudentRequest.getEmail() + " not found"));
    }


    @Override
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException("Student with id " + id + " not found");
        }
        studentRepository.deleteById(id);

    }

    @Override
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException
                        ("Student with id " + id + " not found"));

    }

    public boolean studentExist(String email) {
        return studentRepository.findStudentByEmail(email).isPresent();
    }

}
