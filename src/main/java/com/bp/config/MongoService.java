package com.bp.config;

import com.alibaba.fastjson.JSON;
import com.bp.common.StudentCondition;
import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/**
 * mongo 的相关操作
 *
 * @author current_bp
 * @createTime 20170425
 */
public class MongoService<T> {

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection mongoCollection;


    public MongoService useDatabase(String databaseName) {
        if (this.mongoClient == null) {
            synchronized (this) {
                if (null == this.mongoClient) {
                    mongoClient = MongoUtils.getOneMongoClient();
                }
            }
        }
        database = mongoClient.getDatabase(databaseName);
        return this;
    }

    public boolean insertCollection(String collection) {
        try {
            database.createCollection(collection);
        } catch (Exception e) {
            System.out.println("===>insertCollection: error!! msg:" + e.getMessage());
            return false;
        }
        return true;
    }

    public MongoService getCollection(String collection) {
        mongoCollection = database.getCollection(collection);
        System.out.println("===>collectionCount:" + mongoCollection.count());
        return this;
    }

    public boolean insertDocument(T document) {
        try {
            Document document1 = Document.parse(JSON.toJSONString(document));
            String id = JSON.parseObject(JSON.toJSONString(document)).getString("id");
            document1.append("_id", id);
            mongoCollection.insertOne(document1);
        } catch (Exception e) {
            System.out.println("===>insertDocument: error!! msg:" + e.getMessage());
            return false;
        }
        return true;
    }

    public <T> T findDocumentById(String id, Class<T> clazz) {
        Document document = (Document) mongoCollection.find(new Document("id", id)).first();
        return JSON.parseObject(document.toJson(), clazz);
    }

    public <T> List<T> findDocumentByCondition(StudentCondition studentCondition, Class<T> clazz) {
        List<T> result = new ArrayList<T>();
        BasicDBObject name = null;
        List<BasicDBObject> list = new ArrayList<BasicDBObject>();
        if (null != studentCondition.getName() && !"".equals(studentCondition.getName().trim())) {
            //模糊查询
            Pattern pattern = Pattern.compile("^.*" + studentCondition.getName() + ".*$", Pattern.CASE_INSENSITIVE);
            name = new BasicDBObject("name", pattern);
            list.add(name);
        }
        BasicDBObject address = null;
        if (null != studentCondition.getAddress() && !"".equals(studentCondition.getAddress())) {
            Pattern pattern = Pattern.compile("^.*" + studentCondition.getAddress() + ".*$", Pattern.CASE_INSENSITIVE);
            address = new BasicDBObject("address", pattern);
            list.add(address);
        }

        BasicDBObject andObj = new BasicDBObject("$and", list);//并列条件$and,或者：$or
        FindIterable<Document> fi = mongoCollection.find(andObj);
        mongoCollection.find();
        if (null == fi) {
            return null;
        }
        Iterator iter = fi.iterator();
        while (iter.hasNext()) {
            Document document = (Document) iter.next();
            System.out.println("===>each:" + document.toJson());
            result.add(JSON.parseObject(document.toJson(), clazz));
        }
        return result;
    }

    public boolean updateDocument(T document) {
        try {
            String allContent = JSON.toJSONString(document);
            BasicDBObject query = new BasicDBObject();
            query.put("id", JSON.parseObject(allContent).getString("id"));

            BasicDBObject content = new BasicDBObject();
            query.put("id", JSON.parseObject(allContent).getString("id"));

            Document document1 = Document.parse(JSON.toJSONString(document));
            String id = JSON.parseObject(JSON.toJSONString(document)).getString("id");
            mongoCollection.insertOne(document1);
        } catch (Exception e) {
            System.out.println("===>insertDocument: error!! msg:" + e.getMessage());
            return false;
        }
        return true;
    }
}
