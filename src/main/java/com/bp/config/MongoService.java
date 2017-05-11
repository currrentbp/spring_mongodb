package com.bp.config;

import com.alibaba.fastjson.JSON;
import com.bp.EntityUtil;
import com.bp.entity.Student;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Arrays;

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
//            mongoCollection.insertOne(EntityUtil.getDocByEntity(document));//自己写的一个反射机制
            Document document1 = Document.parse(JSON.toJSONString(document));
            String id = JSON.parseObject(JSON.toJSONString(document)).getString("id");
            document1.append("_id",id);
            mongoCollection.insertOne(document1);
        } catch (Exception e) {
            System.out.println("===>insertDocument: error!! msg:" + e.getMessage());
            return false;
        }
        return true;
    }

    public <T> T findDocumentById(String id,Class<T> clazz) {
        Document document = (Document) mongoCollection.find(new Document("id",id)).first();
        return JSON.parseObject(document.toJson(),clazz);
    }
}
