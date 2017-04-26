package com.bp.dao;

import com.bp.entity.Student;

import java.util.List;

/**
 * @author current_bp
 * @createTime 20170426
 */
public interface CustomerRepository {
    public Student findById(String id);
    public List<Student> findByName(String name);
}
