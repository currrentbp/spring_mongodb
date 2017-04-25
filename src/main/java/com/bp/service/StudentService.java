package com.bp.service;

import com.bp.common.StudentCondition;
import com.bp.entity.Student;

import java.util.List;

/**
 * Created by issuser on 2017/3/23.
 */
public interface StudentService {

    /**
     * 插入一个学生
     * @param student 学生
     * @return 影响行数
     */
    int insertStudent(Student student);

    /**
     * 修改一个学生
     * @param student 学生
     * @return 影响行数
     */
    int updateStudent(Student student);

    /**
     * 根据条件获取学生列表
     * @param studentCondition 条件
     * @return 学生列表
     */
    List<Student> getStudentByCondition(StudentCondition studentCondition);
}
