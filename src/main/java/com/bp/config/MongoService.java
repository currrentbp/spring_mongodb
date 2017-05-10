package com.bp.config;

import com.alibaba.fastjson.JSON;
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
public class MongoService {

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

    public boolean insertDocument(String document) {
//        Document doc = new Document("_id", username).append("password", passwordHash);

        try {
            mongoCollection.insertOne(document);
        } catch (Exception e) {
            System.out.println("===>insertDocument: error!! msg:" + e.getMessage());
            return false;
        }
        return true;
    }

    public String findFirstDocument() {
        Document myDoc = (Document) mongoCollection.find().first();
        System.out.println("===>first:"+JSON.toJSONString(myDoc));

        mongoCollection.find();

        FindIterable findIterable = mongoCollection.find();
        if (null == findIterable) {
            return null;
        }

        if (null == findIterable.first()) {
            return null;
        }
        return findIterable.first().toString();
    }
}
