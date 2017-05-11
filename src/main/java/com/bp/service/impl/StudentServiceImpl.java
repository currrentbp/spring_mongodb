package com.bp.service.impl;

import com.alibaba.fastjson.JSON;
import com.bp.common.StudentCondition;
import com.bp.config.MongoService;
import com.bp.entity.Student;
import com.bp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author current_bp
 * @createTime 20170323
 */
@Service("studentService")
public class StudentServiceImpl implements StudentService{

    private MongoService mongoService;

    /**
     * 初始化mongodb的链接
     */
    @PostConstruct
    private void init(){
        mongoService = new MongoService().useDatabase("currentbp").getCollection("student");
    }


    /**
     * 插入一个学生
     *
     * @param student 学生
     * @return 影响行数
     */
    public int insertStudent(Student student) {
        try {
            mongoService.insertDocument(student);
        }catch (Exception e){
            System.out.println("===>insertStudent: error!! msg:"+e.getMessage());
            return 0;
        }
        return 1;
    }

    /**
     * 修改一个学生
     *
     * @param student 学生
     * @return 影响行数
     */
    public int updateStudent(Student student) {
        return 0;
    }

    /**
     * 根据条件获取学生列表
     *
     * @param studentCondition 条件
     * @return 学生列表
     */
    public List<Student> getStudentByCondition(StudentCondition studentCondition) {
        return null;
    }

    /**
     * 根据ID获取学生
     *
     * @param id 学生id
     * @return 学生
     */
    public Student getStudentById(String id) {
        return (Student) mongoService.findDocumentById(id,Student.class);
    }
}
