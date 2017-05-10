package com.bp.dao;

import com.bp.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by issuser on 2017/5/10.
 */
public interface StudentDao extends MongoRepository<Student, String> {
}
