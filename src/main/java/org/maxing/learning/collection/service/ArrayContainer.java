package org.maxing.learning.collection.service;

import java.util.Objects;

import static java.lang.Math.max;

public class ArrayContainer {
    private Object[] array;
    private int size;

    public ArrayContainer(){
        array=new Object[4];
        size=0;
    }

    public ArrayContainer(Object[]obj){
        if(obj==null){
            throw new IllegalArgumentException("initialize array should not be null");
        }
        array=new Object[obj.length];
        size=obj.length;
        for(int i=0;i<size;++i){
            array[i]=obj[i];
        }
    }

    public ArrayContainer(int len){
        if(len<0){
            throw new IllegalArgumentException("initialize num should be positive");
        }
        array=new Object[len];
    }

    public int size(){
        return size;
    }

    public int capacity(){
        return array.length;
    }

    public boolean isEmpty() {
        return size ==0;
    }

    public void add(Object value){
        if(size==array.length){
            int newCapacity=max(array.length+1,array.length+(array.length>>1));
            Object[]newObj=new Object[newCapacity];
            for(int i=0;i<size;++i){
                newObj[i]=array[i];
            }
            array=newObj;
        }
        array[size++]=value;
    }

    public Object get(int index){
        if(index<0 || index>=size){
            throw new IndexOutOfBoundsException("index out of bounds");
        }
        return array[index];
    }

    public void set(int index,Object value){
        if(index<0 || index>=size){
            throw new IndexOutOfBoundsException("index out of bounds");
        }
        array[index]=value;
    }

    public boolean remove(Object value){
        boolean found=false;
        int index=-1;
        for(int i=0;i<size;++i){
            if(Objects.equals(array[i],value)){
                array[i]=null;
                index=i;
                found=true;
                break;
            }
        }
        if(index==-1){
            return false;
        }
        for(int i=index;i<size-1;++i){
            array[i]=array[i+1];
        }
        array[--size]=null;
        return found && index!=-1;
    }

    public Object remove(int index){
        if(index<0 || index>=size) {
            throw new IndexOutOfBoundsException("index out of bounds");
        }
        Object value=array[index];
        for(int i=index;i<size-1;++i){
            array[i]=array[i+1];
        }
        array[--size]=null;
        return value;
    }
}
