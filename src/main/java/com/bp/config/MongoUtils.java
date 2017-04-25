package com.bp.config;

import com.mongodb.MongoClient;

/**
 * mongo的 集合
 *
 * @author current_bp
 * @createTime 20170425
 */
public class MongoUtils {
    private static String host = "10.75.224.96";
    private static Integer port = 27017;

    private static MongoClient mongoClient;

    public static MongoClient getOneMongoClient(){
        if(null == mongoClient){
            synchronized (MongoUtils.class){
                if(null == mongoClient){
                    mongoClient = new MongoClient(host, port);
                }
            }
        }
        return mongoClient;
    }
}
