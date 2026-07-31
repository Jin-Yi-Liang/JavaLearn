package org.maxing.learning.string;

public class StringAnalyzer {
    public static void analyze(String text){
        if(text==null){
            throw new IllegalArgumentException("string should not be null");
        }

        int length=text.length();
        System.out.println(length);

        if(!text.isEmpty()) {
            System.out.println(text.charAt(0));
            System.out.println(text.charAt(length - 1));
        }
        else{
            System.out.println("empty string");
        }

        for(int i=0;i<length;++i){
            System.out.print(text.charAt(i));
        }
        System.out.println();

        for(int i=length-1;i>=0;--i){
            System.out.print(text.charAt(i));
        }
        System.out.println();
    }

    public static int countCharacter(String text,char target){
        if(!StringDemo.isValid(text)){
            throw new IllegalArgumentException("string is not valid");
        }
        int length=text.length(),count=0;
        for(int i=0;i<length;++i){
            if(text.charAt(i)==target) ++count;
        }
        return count;
    }

    public static void printAllIndex(String text,char target){
        if(!StringDemo.isValid(text)){
            throw new IllegalArgumentException("string is not valid");
        }
        int length=text.length();
        int[]index=new int[length];
        int size=0;
        for(int i=0;i<length;++i){
            if(text.charAt(i)==target) index[size++]=i;
        }

        for(int i=0;i<size;++i){
            int ind=index[i];
            System.out.print(ind+" ");
        }
        System.out.println();
    }

    public static void printFileInfo(String fileName){
        int fileNameIndex=fileName.lastIndexOf('.');
        if(fileNameIndex==-1){
            throw new IllegalArgumentException("file name not found");
        }

        System.out.println("full file name: "+fileName);

        System.out.print("file name: ");
        String pureFIleName=fileName.substring(0,fileNameIndex);
        System.out.println(pureFIleName);

        System.out.print("extension: ");
        String extention=fileName.substring(fileNameIndex+1);
        System.out.println(extention);
    }

    public static void compareTwoString(String text1,String text2){
        if(!StringDemo.isValid(text1)||!StringDemo.isValid(text2)){
            throw new IllegalArgumentException("string is not valid");
        }

        int ans=text1.compareTo(text2);
        if(ans==0){
            System.out.println(text1+" is equal to "+text2);
        }
        else if(ans<0){
            System.out.println(text1+" is before "+text2);
        }
        else if(ans>0){
            System.out.println(text1+" is after "+text2);
        }
    }

    public static String findMinimun(String[]strs){
        if(strs==null||strs.length==0){
            throw new IllegalArgumentException("string is empty");
        }

        String ans=strs[0];
        for(String str:strs){
            if(!StringDemo.isValid(str)){
                throw new IllegalArgumentException("string is not valid");
            }
            if(str.compareTo(ans)<0){
                ans=str;
            }
        }
        return ans;
    }
}
