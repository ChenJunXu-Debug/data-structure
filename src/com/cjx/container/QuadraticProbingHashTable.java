/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: QuadraticProbingHashTable
 * Author:   ASUS
 * Date:     2019/10/12 11:58
 * Description: 平方探测哈希表
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cjx.container;

/**
 * 〈一句话功能简述〉<br> 
 * 〈平方探测哈希表〉
 *
 * @author ASUS
 * @create 2019/10/12
 * @since 1.0.0
 */
public class QuadraticProbingHashTable<T> {

    private static final int DEFAULT_TABLE_SIZE = 11;
    private HashEntry<T> []array;//容器（本质是个数组）
    private int currentSize;//容器中元素总数
    public QuadraticProbingHashTable(){
        this(DEFAULT_TABLE_SIZE);
    }
    public QuadraticProbingHashTable(int size){
        array = new HashEntry[size];
        makeEmpty();
    }
    //清空容器
    public void makeEmpty() {
        currentSize = 0;
        for(int i=0; i<array.length; i++){
            array[i] = null;
        }
    }
    public int getSize(){
        return currentSize;
    }
    public boolean contains(T x){
        int currentPos = findPos(x);
        return isActive(currentPos);
    }
    //如果不存在这个元素就通过算法找个位置插入
    public void insert(T x){
        int currentPos = findPos(x);
        if(!isActive(currentPos)){
           array[currentPos] = new HashEntry<>(x, true);
           currentSize++;
           //扩容
           if(currentSize>array.length/2){
               reHash();
           }
       }
    }
    //如果存在这个元素就逻辑删除
    public void remove(T x){
        int currentPos = findPos(x);
        if(isActive(currentPos)){
            array[currentPos].isActive = false;
            currentSize--;
        }
    }
    //当前位置有元素且标记为isActive:true才表示为可用元素
    private boolean isActive(int currentPos) {
        return array[currentPos]!=null && array[currentPos].isActive;
    }
    //给元素分配一个位置（数组下标）
    private int findPos(T x) {
        int count = 1;//步长
        int currentPos = myHash(x);
        while(array[currentPos]!=null && !array[currentPos].element.equals(x)){
            currentPos += count;//+1=>4=>9
            count +=2;
            if(currentPos >= array.length){
                currentPos -=array.length;
            }
        }
        return currentPos;
    }
    //散列算法
    private int myHash(T x){
        int hashVal = x.hashCode();
        hashVal %= array.length;
        if(hashVal < 0){
            hashVal += array.length;
        }
        return hashVal;
    }
    //把旧容器的元素重新散列放到新的扩容后的容器里
    private void reHash(){
        HashEntry<T>[]oldArray = array;
        array = new HashEntry[array.length*2 + 1];
        for(HashEntry entry: oldArray){
            if(entry!=null && entry.isActive){
                insert((T)entry.element);
            }
        }
    }
    public void println(){
        for(HashEntry entry:array){
            if(entry!=null && entry.isActive){
                System.out.println(entry.element);
            }
        }
    }
    private static class HashEntry<T>{
        public T element;//元素的值
        public boolean isActive;//false表示被逻辑删除

        public HashEntry(T e) {
            this(e, true);
        }

        public HashEntry(T element, boolean isActive) {
            this.element = element;
            this.isActive = isActive;
        }
    }
}
