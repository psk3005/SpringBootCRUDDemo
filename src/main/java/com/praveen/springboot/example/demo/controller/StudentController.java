package com.praveen.springboot.example.demo.controller;

import com.praveen.springboot.example.demo.data.Student;
import com.praveen.springboot.example.demo.exception.StudentNotFoundException;
import com.praveen.springboot.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;


    @RequestMapping(
            value = "/students",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Student> retrieveAllStudents(){
        return studentRepository.findAll();
    }

    @RequestMapping(
            value = "/students/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Student retrieveStudent(@PathVariable long id) {
        Optional<Student> student = studentRepository.findById(id);
        if(!student.isPresent()) {
            throw new StudentNotFoundException("id : "+id);
        }
        return student.get();
    }

    @RequestMapping(
            value = "/students/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void deleteStudent(@PathVariable long id) {
        studentRepository.deleteById(id);
    }

    @RequestMapping(
            value = "/students",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @RequestMapping(
            value = "/students/{id}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Student updateStudent(Student student, @PathVariable long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if(!studentOptional.isPresent()) {
            throw new StudentNotFoundException("id : "+id);
        }
        student.setId(id);
        studentRepository.save(student);
        return student;

    }
}
