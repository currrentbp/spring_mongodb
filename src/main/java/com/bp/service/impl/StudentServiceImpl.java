package com.bp.service.impl;

import com.bp.common.StudentCondition;
import com.bp.entity.Student;
import com.bp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author current_bp
 * @createTime 20170323
 */
@Service("studentService")
public class StudentServiceImpl implements StudentService{

    @Autowired(required = false)
    private MongoTemplate mongoTemplate;


    /**
     * 插入一个学生
     *
     * @param student 学生
     * @return 影响行数
     */
    public int insertStudent(Student student) {
        try {
            mongoTemplate.insert(student);
        }catch (Exception e){
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
        return mongoTemplate.find(new Query(),Student.class);
    }
}
