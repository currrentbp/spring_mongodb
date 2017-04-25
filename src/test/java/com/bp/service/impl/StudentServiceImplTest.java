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

    @Test
    public void updateStudent() throws Exception {

    }

    @Test
    public void getStudentByCondition() throws Exception {
        StudentCondition studentCondition = new StudentCondition();

        List<Student> students = studentService.getStudentByCondition(studentCondition);
        System.out.println("students:"+JSON.toJSONString(students));
    }

    @Test
    public void testMongodb1() {
        try {
            // 连接到 mongodb 服务
            Mongo mongo = new Mongo("10.75.224.96", 27017);
            //根据mongodb数据库的名称获取mongodb对象 ,
            DB db = mongo.getDB("test");
            Set<String> collectionNames = db.getCollectionNames();
            // 打印出test中的集合
            for (String name : collectionNames) {
                System.out.println("collectionName===" + name);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMongodb2() {
        try {
            MongoClient mongoClient = new MongoClient("10.75.224.96", 27017);
            MongoDatabase database = mongoClient.getDatabase("currentbp");//数据库名称
//            database.createCollection("s1");//添加一个表or集合
//            MongoCollection<Document> collection = database.getCollection("col");//表名称
//            System.out.println("===>collection:"+JSON.toJSONString(collection));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void t1(){
        MongoService mongoService = new MongoService();
        String document = mongoService.useDatabase("currentbp").getCollection("s4").findFirstDocument();
        System.out.println("===>document:"+document);
    }

    @Test
    public void insertCollection(){
        MongoService mongoService = new MongoService();
        mongoService.useDatabase("currentbp").insertCollection("s3");
    }

    @Test
    public void insertDocument(){
        MongoService mongoService = new MongoService();
        mongoService.useDatabase("currentbp").getCollection("s4").insertDocument("{  \n" +
                " title: 'baopan222',  \n" +
                "description: '===========',   \n" +
                "by: 'Mongodb中文网',   \n" +
                "url: 'http://www.mongodb.org.cn',   \n" +
                "tags: ['mongodb', 'database', 'NoSQL'],  \n" +
                "likes: 100  \n" +
                "}");
    }

    @Test
    public void insertAndFind(){
        MongoClient mongoClient = new MongoClient( "10.75.224.96" , 27017 );
        MongoDatabase database = mongoClient.getDatabase("currentbp");
        database.createCollection("s5");
        Document doc = new Document("name", "baopan")
                .append("type", "+++++++++++++++++++++++++++++++")
                .append("count", 1)
                .append("versions", Arrays.asList("v3.2", "v3.0", "v2.6"))
                .append("info", new Document("x", 203).append("y", 102));
        MongoCollection<Document> collection = database.getCollection("s4");
        collection.insertOne(doc);
        System.out.println(collection.count());

        Document myDoc = collection.find().first();
        System.out.println(myDoc.toJson());
    }


    @Test
    public void createStudentTable(){
        try {
            MongoService mongoService = new MongoService();
            mongoService.useDatabase("currentbp").insertCollection("student");
            //DBObject dbObject = (DBObject)JSON.parse(json);
        }catch (Exception e){
            Assert.assertTrue(false);
        }
        Assert.assertTrue(true);
    }

    @Test
    public void insertOneStudent(){
        Student student = new Student();
        student.setId("1");
        student.setName("baopan");
        MongoService mongoService = new MongoService();
        mongoService.useDatabase("currentbp").getCollection("student").insertDocument(JSON.toJSONString(student));
    }

}