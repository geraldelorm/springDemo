package com.gerald.springDemo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentByEmailOptional = studentRepository.findStudentByEmail(student.getEmail());

        if (studentByEmailOptional.isPresent()) {
            throw  new IllegalStateException("email taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if(!exists){
            throw  new IllegalStateException("student with " + studentId + " does not exist");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException("student with " + studentId + " does not exist") );
        if(name != null && name.length() > 0 && (student.getName() != name)) {
            student.setName(name);
        }
        if(email != null && email.length() > 0 && (student.getEmail() != email)) {
            Optional<Student> studentWithEmailOptional = studentRepository.findStudentByEmail(email);
            if(studentWithEmailOptional.isPresent()){
                throw  new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }
    }
}
