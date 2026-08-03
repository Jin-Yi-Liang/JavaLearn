package org.maxing.learning.collection.service;

import java.util.ArrayList;
import java.util.Collection;

public class CollectionMethod {
    public static void test() {
        Collection<String> col=new ArrayList<String>();
        col.add("Michael");
        col.add("David");
        col.add("JavaSE");
        col.add("Spring boot");
        for(String cur:col){
            System.out.println(cur);
        }

        col.remove("JavaSE");
        System.out.println(col.contains("JavaSE"));
        System.out.println(col.size());

        String[]strArray=col.toArray(new String[0]);
        for(int i=0;i<strArray.length;i++){
            System.out.println(strArray[i]);
        }

        col.removeAll(col);
        System.out.println(col.isEmpty());
    }
}
