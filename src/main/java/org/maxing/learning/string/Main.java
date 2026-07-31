package org.maxing.learning.string;

public class Main {
    public static void main(String[] args){
        String[] strs={null,""," ","Java",new String("Michael")};
        StringDemo.show(strs);

        String str="java";
        str.concat(" se");
        System.out.println(str);

        str=str.concat(" se");
        System.out.println(str);
    }
}
