package com.example.new2.data.converter;

import com.example.new2.data.Map;
import org.bson.Document;

import java.util.ArrayList;

public class MapConverter {


    //MongoDB to User
    public static Map toMap(Document doc){
        Map map = new Map();
        map.setName((String)doc.get("name"));
        map.setPage((String)doc.get("page"));
        map.setAddress((String)doc.get("address"));
        map.setPhone((String)doc.get("phone"));
        map.setImg((String)doc.get("img"));
        map.setLatlong((ArrayList<Float>) doc.get("coordinate"));
        return map;
    }

}
