package com.bp;

import com.alibaba.fastjson.JSON;
import com.bp.entity.Student;
import org.bson.Document;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by issuser on 2017/4/26.
 */
public class EntityUtilTest {


    @Test
    public void getDocByEntity() throws Exception {
        Student student = new Student();
        student.setId("1");
        student.setName("baopan");
        student.setAddress("ADDRESS");
        Document document = EntityUtil.getDocByEntity(student);
        System.out.println("===>document:" + JSON.toJSONString(document));

    }

}