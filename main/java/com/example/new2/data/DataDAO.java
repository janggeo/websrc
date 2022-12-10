package com.example.new2.data;

import com.example.new2.data.converter.DataConverter;
import com.mongodb.*;
import com.mongodb.client.*;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static com.mongodb.client.model.Filters.eq;

public class DataDAO  {
    public MongoCollection<Document> coll;
    public MongoCollection<Document> coll2;
    int i=0;
    Logger Log = Logger.getGlobal();
    public DataDAO(MongoClient mongoClient){
//        for (String name : mongoClient.getDatabase("VeganData").listCollectionNames()) {
//            System.out.println(name);
//        }

        this.coll = mongoClient.getDatabase("VeganData").getCollection("vegan");
        this.coll2 = mongoClient.getDatabase("VeganData").getCollection("restaurant");
        Log.info("info Log");
    }
    public DataDAO(MongoClient mongoClient, String region){
//       try{
//           if(region.equals("")){
//               region = "seoul";
//           }
//       }catch(NullPointerException e){
//           region = "seoul";
//       }
        this.coll2 = mongoClient.getDatabase("VeganData").getCollection(region);

        Log.info("info Log");
    }
//    public User create(Data data){
//        Document doc = DataConverter.toDocument(data);
//        this.coll.insertOne(doc);
//        ObjectId id = (ObjectId) doc.get("_id");
//        p.setId(id.toString());
//        return p;
//    }
//    public void update(Data data){
//        this.coll.updateOne(Filters.eq("_id",new ObjectId(data.getId())),new Document("$set",DataConverter.toDocument(p)));
//    }
//    public void delete(String id){
//        this.coll.deleteOne(Filters.eq("_id",new ObjectId(id)));
//    }
//    public boolean exists(String id){
//        FindIterable<Document> doc = this.coll.find(Filters.eq("_id",id)).limit(1);
//        return doc !=null;
//    }

    public List<Data> getList(String[] values){

        FindIterable<Document> iterable = coll.find();
        MongoCursor<Document> cursor = null;
        List<Data> list = new ArrayList<Data>();

        try{
        cursor = iterable.iterator();}
        catch(MongoTimeoutException e){
            System.out.println("ok");
        }

        try{
            while(cursor.hasNext()) {
                Document doc = cursor.next();
                Data data = DataConverter.toData(doc);
                for (int i=0; i<values.length; i++){
                    if(data.getCategory().equals(values[i])){
                        list.add(data);
                    }
                }
            }
            cursor.close();
        }catch(NullPointerException e){
            System.out.println("nullpointerexception");
        }
        return list;
    }

    public List<Data> getList(int id){

        FindIterable<Document> iterable = coll.find();
        MongoCursor<Document> cursor = null;
        List<Data> list = new ArrayList<Data>();

        try{
            cursor = iterable.iterator();}
        catch(MongoTimeoutException e){
            System.out.println("ok");
        }

        try{
            while(cursor.hasNext()) {
                Document doc = cursor.next();
                Data data = DataConverter.toData(doc);

                    if(data.getId()==(id)){
                        list.add(data);
                }
            }
            cursor.close();
        }catch(NullPointerException e){
            System.out.println("nullpointerexception");
        }
        return list;
    }

    public List<Map> getMap(){
        FindIterable<Document> iterable = coll2.find();
        MongoCursor<Document> cursor = null;
        List<Map> list = new ArrayList<Map>();

        try{
            cursor = iterable.iterator();}
        catch(MongoTimeoutException e){
            System.out.println("ok");
        }

        try{
            while(cursor.hasNext()) {
                Document doc = cursor.next();
                Map map = DataConverter.toMap(doc);
                list.add(map);
            }
            cursor.close();
        }catch(NullPointerException e){
            System.out.println("nullpointerexception");
        }
        return list;
    }

    public Data getData(String id){
        Document doc = this.coll.find(eq("_id",new ObjectId(id))).first();
        return DataConverter.toData(doc);
    }
}
