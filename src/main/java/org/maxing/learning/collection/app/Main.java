package org.maxing.learning.collection.app;

import java.util.*;

public class Main {
    public static void main(String[]args){
        Map<String,String> map=new HashMap<>();
        map.put("Michael","Love");
        map.put("Jerry","Love");
        map.put("Jerry","Love4");
        map.put("Jerry","Love2");
        map.put("Jerry","Love3");

        for(Map.Entry<String,String>entry:map.entrySet()){
            System.out.println(entry.getKey()+" "+ entry.getValue());
        }
    }
}
