package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
        return this.studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> optionalStudent = studentRepository.findStudentByEmail(student.getEmail());
        if(optionalStudent.isPresent()) {
            throw new IllegalStateException("email already exists!");
        }

        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        if(! studentRepository.existsById(studentId)) {
            throw new IllegalStateException("student with id " + studentId + " does not exist!");
        }

        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, Student updatedStudent) {
        if(!studentRepository.existsById(studentId)) {
            throw new IllegalStateException("student with id " + studentId + " does not exist!");
        } else {
            Optional<Student> optionalStudent = studentRepository.findById(studentId);
            Student student = optionalStudent.get();
            student.setName(updatedStudent.getName());
            student.setEmail(updatedStudent.getEmail());
        }
    }
}
