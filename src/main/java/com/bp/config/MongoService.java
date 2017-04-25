package com.bp.config;

import com.alibaba.fastjson.JSON;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

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
        Document doc = new Document("name", "MongoDB")
                .append("type", "database")
                .append("count", 1)
                .append("versions", Arrays.asList("v3.2", "v3.0", "v2.6"))
                .append("info", new Document("x", 203).append("y", 102));
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
