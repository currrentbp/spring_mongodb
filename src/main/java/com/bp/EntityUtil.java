package com.bp;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author current_bp
 * @createTime 20170425
 */
public class EntityUtil {

    public static Document getDocByEntity(Object object) {
        if (null == object) {
            return null;
        }

        Document document = new Document();
        Class clazz = object.getClass();
        System.out.println("===>class:" + clazz.getName());
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                String name = field.getName();
                String value = field.get(object).toString();
                System.out.println("name:"+name+" value:"+value);
                document.append(field.getName(), field.get(object));
            } catch (IllegalAccessException e) {
                System.out.println("===>error: msg:" + e.getMessage());
            }
        }

        return document;
    }

    public static <T> T getBeanByDoc(Document document ,Class<T> clazz){
        T bean = null;
        try {
            bean = clazz.newInstance();
            Field[] fields = clazz.getDeclaredFields();// 获取所有属性
            Method[] methods = clazz.getMethods();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

}
