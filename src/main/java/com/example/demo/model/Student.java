package com.example.demo.model;

import com.example.demo.common.Identifiable;
import java.io.Serializable;

public class Student implements Serializable, Identifiable {
    private String id;
    private String name;
    private String email;

    public Student(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() { return id; }

    public String getName() { return name; }
    public String getEmail() { return email; }
}
