package com.example.service.impl;

import com.example.mapper.UserinfoMapper;
import com.example.mapper.pojo.Userinfo;
import com.example.mapper.pojo.UserinfoExample;
import com.example.service.UserinfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class UserinfoServiceImpl implements UserinfoService {
    @Resource
    UserinfoMapper userinfoMapper;
    @Override
    public List<Userinfo> getAll() {
        UserinfoExample userinfoExample = new UserinfoExample();
        userinfoExample.setDistinct(true);
        return userinfoMapper.selectByExample(userinfoExample);
    }
}
