package com.example.service;



import com.example.mapper.pojo.Student;
import com.example.mapper.pojo.StudentExample;

import java.util.List;

public interface StudentService {
    List<Student> getAll();
}
