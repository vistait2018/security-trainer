package com.perspective.securitytrainer.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//Using Role Based Permission
@RestController()
@RequestMapping("management/api/v1/")
public class StudentManagemnetController {
    private static  List<Student> students =  new ArrayList<>();
    static{
        students.add(new Student(1,"Jame Bond", "Male"));
        students.add( new Student(2,"Indiana Jones", "Male"));
        students.add( new Student(3,"Angelina Jolie", "Female"));
        students.add( new Student(4,"Eddie Murphie", "Male"));
    }

    private static int studentCount = students.size();
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    @GetMapping("students")
    public List<Student> students(){
        return students.stream().collect(Collectors.toList());
    }

    @GetMapping("students/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    public Student student(@PathVariable int id){
        return students.stream()
                .filter(e->e.getId() == id)
                .findFirst()
                .orElseThrow(()->new IllegalStateException("Student with id not found"));
    }

    @PostMapping("students")
    @PreAuthorize("hasAuthority('student:write')")
    public Student addStudent(@RequestBody Student student){
        int studentCounter = this.studentCount;
        if( student.getId() == null){
            student.setId(++studentCounter);
        }
        students.add(student);
        return student;
    }

    @DeleteMapping("students/{id}")
    @PreAuthorize("hasAuthority('student:write')")
    public void deleteStudent(@PathVariable Integer id){
        Student studentToDelete = students.stream()
                .filter(e->e.getId() == id)
                .findFirst()
                .orElseThrow(()->new IllegalStateException("Student not found"));

        students.remove(studentToDelete);
    }

    @PostMapping("students/{id}")
    @PreAuthorize("hasAuthority('student:write')")
    public void updateStudent(@PathVariable Integer id, @RequestBody Student student){
        Student studentToDelete = students.stream()
                .filter(e->e.getId() == id)
                .findFirst()
                .orElseThrow(()->new IllegalStateException("Student not found"));

        studentToDelete.setId(student.getId());
        studentToDelete.setName(student.getName());
        studentToDelete.setSex(student.getSex());


    }
}
