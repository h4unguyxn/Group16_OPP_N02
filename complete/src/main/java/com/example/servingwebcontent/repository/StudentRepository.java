package com.example.servingwebcontent.repository;

import com.example.servingwebcontent.model.Student;

public class StudentRepository extends GenericManager<Student> {
    public StudentRepository() {
        super("students.dat");
    }
}
