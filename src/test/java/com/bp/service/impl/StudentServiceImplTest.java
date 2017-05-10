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
        student.setId("1");
        student.setName("name_1");
        student.setAddress("address_1");
        List<String> stringList = new ArrayList();
        stringList.add("s1");
        stringList.add("s2");
        student.setHobbies(stringList);

        studentService.insertStudent(student);


    }


}