package com.example.mapper;

import com.example.mapper.pojo.Student;
import com.example.mapper.pojo.StudentExample;
import com.example.util.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public class StudentMapperImpl extends BaseDao<StudentMapper,StudentMapper,Student> implements StudentMapper{


    @Override
    public int countByExample(StudentExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(StudentExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer sid) {
        return 0;
    }

    @Override
    public int insert(Student record) {
        return 0;
    }

    @Override
    public int insertSelective(Student record) {
        return 0;
    }

    @Override
    public List<Student> selectByExample(StudentExample example) {
        return this.getMapper().selectByExample(example);
    }

    @Override
    public Student selectByPrimaryKey(Integer sid) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Student record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Student record) {
        return 0;
    }

    @Override
    protected void setMapperClass() {

    }

    @Override
    protected void setEntityClass() {

    }
}
