package com.bp.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoOptions;

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
                    mongoClient = new MongoClient(host, port);//一个服务器时
                    MongoClientOptions options = MongoClientOptions.builder().sslEnabled(true).build();
                }
            }
        }
        return mongoClient;
    }

    /*
     String user; // the user name
 String database; // the name of the database in which the user is defined
 char[] password; // the password as a character array
 // ...

 MongoCredential credential = MongoCredential.createCredential(user, database, password);

 MongoClientOptions options = MongoClientOptions.builder().sslEnabled(true).build();

 MongoClient mongoClient = new MongoClient(new ServerAddress("host1", 27017),
                                           Arrays.asList(credential),
                                           options);
     */
}
