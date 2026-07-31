package org.maxing.learning.string;

public class StringDemo {
    public static void show(String[] strs) {
        for (int i = 0; i < strs.length; ++i) {
            System.out.println("---------------");

            if(isValid(strs[i])){
                System.out.println("valid string");
            }
            else{
                System.out.println("invalid string");
            }

            if (strs[i] == null) {
                System.out.println("null string");
                continue;
            }
            else{
                System.out.println(strs[i]);
            }

            if(strs[i]!=null && strs[i].isBlank()){
                System.out.println("blank string");
            }
            else{
                System.out.println("not blank string");
            }

            if(strs[i]!=null && strs[i].isEmpty()){
                System.out.println("empty string");
            }
            else{
                System.out.println("not empty string");
            }

            System.out.println("length: "+strs[i].length());
        }
    }

    public static boolean isValid(String name){
        if(name==null || name.isEmpty()){
            return false;
        }
        return true;
    }

}
