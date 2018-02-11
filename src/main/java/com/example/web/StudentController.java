package com.example.web;

import com.example.mapper.pojo.Student;
import com.example.mapper.pojo.StudentExample;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentServiceImpl;

    @RequestMapping(value = "/demo",method = RequestMethod.GET)
    @ResponseBody
    public Object getStudent(){
        try{
            List<Student> students = studentServiceImpl.getAll();
            return students;
        }catch (Exception ex){
        }
        return null;
    }

}
