package com.example.service.impl;


import com.example.mapper.StudentMapper;
import com.example.mapper.StudentMapperImpl;
import com.example.mapper.pojo.Student;
import com.example.mapper.pojo.StudentExample;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentMapper studentMapperImpl;

    @Override
    @Transactional
    public List<Student> getAll() {
        StudentExample studentExample = new StudentExample();
        return studentMapperImpl.selectByExample(studentExample);
    }
}
