package com.bp.service.impl;

import com.alibaba.fastjson.JSON;
import com.bp.common.StudentCondition;
import com.bp.config.MongoService;
import com.bp.entity.Student;
import com.bp.service.StudentService;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.client.MongoCollection;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * @author current_bp
 * @createTime 20170323
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath*:applicationContext.xml",
        "classpath*:mongodb.xml"})
public class StudentServiceImplTest {

    @Autowired
    private StudentService studentService;

    @Test
    public void insertStudent() throws Exception {
        Student student = new Student();
        student.setId("5");
        student.setName("name_5");
        student.setAddress("address_5");
        List<String> stringList = new ArrayList();
        stringList.add("s11345");
        stringList.add("s22345");
        student.setHobbies(stringList);

        studentService.insertStudent(student);
    }

    @Test
    public void findFirst() throws Exception{
        System.out.println(JSON.toJSONString(studentService.getStudentById("5")));
    }

    @Test
    public void getStudentByCondition() {
        StudentCondition studentCondition = new StudentCondition();
        studentCondition.setName("name_2");
//        studentCondition.setAddress("address");
        System.out.println(JSON.toJSONString(studentService.getStudentByCondition(studentCondition)));
    }


}