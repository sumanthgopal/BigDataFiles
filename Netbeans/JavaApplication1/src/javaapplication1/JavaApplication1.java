/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.Arrays;
import org.bson.Document;

/**
 *
 * @author sgopalakrishna
 */
public class JavaApplication1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Step 1. Get mongdb client
        MongoClient mongoClient = new MongoClient();
        //2. select db
        MongoDatabase db = mongoClient.getDatabase("mydb");
        //select collection
        MongoCollection<Document> collection = db.getCollection("mydb");
        //4.create doc
        Document doc = new Document("name", "MongoDB")
                .append("type", "database")
                .append("count", 1)
                .append("versions", Arrays.asList("v3.2", "v3.0", "v2.6"))
                .append("info", new Document("x", 203).append("y", 102));
        //5. Save doc
        collection.insertOne(doc);
        
        System.out.println(doc);
    }
    
}
