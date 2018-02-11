package com.example.web;

import com.example.service.UserinfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/userinfo")
public class UserinfoController {

    @Resource
    UserinfoService userinfoServiceImpl;

    @RequestMapping(value = "/demo",method = RequestMethod.GET)
    @ResponseBody
    public Object getAll(){
        return userinfoServiceImpl.getAll();
    }

}
