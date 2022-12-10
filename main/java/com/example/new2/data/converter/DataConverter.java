package com.example.new2.data.converter;

import com.example.new2.data.Data;
import com.example.new2.data.Map;
import org.bson.Document;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DataConverter {

//    public static Document toDocument(Data data){
//        Document doc = new Document("id", data.getId())
//                .append("category", data.getCategory())
//                .append("name",data.getName())
//                .append("",p.getUserEmail());
//        if(p.getId() !=null){
//            doc.append("_id",new ObjectId(p.getId()));
//        }
//        return doc;
//    }

    //MongoDB to User
    public static Data toData(Document doc){
        Data data = new Data();
        data.setId((int)doc.get("id"));
        data.setCategory((String)doc.get("category"));
        data.setName((String)doc.get("name"));
        data.setImg((ArrayList<String>) doc.get("img"));
        data.setProduct((ArrayList<Array>) doc.get("product"));
        return data;
    }

    public static Map toMap(Document doc){
        Map map = new Map();
        map.setAddress((String)doc.get("address"));
        map.setName((String)doc.get("name"));
        map.setPage((String)doc.get("page"));
        map.setPhone((String)doc.get("phone"));
        map.setLatlong((ArrayList<Float>)doc.get("coordinate"));
        map.setImg((String)doc.get("img"));
        return map;
    }

}
