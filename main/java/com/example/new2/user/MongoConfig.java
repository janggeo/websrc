package com.example.new2.user;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.logging.Logger;

import static com.mongodb.client.model.Filters.eq;


public class MongoConfig {
    private static final String URI = "mongodb://localhost:27017";
    private static final String DATABASE = "VeganData";

    public static void main(String[] args) {

            Logger logger = Logger.getLogger("MongoConfig.class");


            MongoClient mongoClient = MongoClients.create(URI);

                MongoDatabase database = mongoClient.getDatabase(DATABASE);

                MongoCollection<Document> collection = database.getCollection("vegan");
               System.out.println(collection.getNamespace());

                Document doc = (Document) collection.find(eq("id",2)).first();
                System.out.println("1");
                System.out.print(doc.toJson());

            //return database;
    }
}
