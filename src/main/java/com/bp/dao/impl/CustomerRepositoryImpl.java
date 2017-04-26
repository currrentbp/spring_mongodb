package com.bp.dao.impl;

import com.bp.config.MongoService;
import com.bp.dao.CustomerRepository;
import com.bp.entity.Student;

import java.util.List;

/**
 * Created by issuser on 2017/4/26.
 */
public class CustomerRepositoryImpl implements CustomerRepository{
    private MongoService mongoService = new MongoService();


    public Student findById(String id) {
        mongoService.useDatabase("currentbp").getCollection("s4").findFirstDocument();
        return null;
    }

    public List<Student> findByName(String name) {
        return null;
    }
}
