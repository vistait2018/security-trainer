package com.perspective.securitytrainer.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("api/v1/")
public class StudentController {
    private static final List<Student> students =  Arrays.asList(
            new Student(1,"Jame Bond", "Male"),
            new Student(2,"Indiana Jones", "Male"),
            new Student(3,"Angelina Jolie", "Female"),
            new Student(4,"Eddie Murphie", "Male")
    );

    @GetMapping("students")
    public List<Student> students(){
        return students.stream().collect(Collectors.toList());
    }

    @GetMapping("students/{id}")
    public Student student(@PathVariable int id){
        return students.stream()
                .filter(e->e.getId() == id)
                .findFirst()
                .orElseThrow(()->new IllegalStateException("Student with id not found"));
    }

}
