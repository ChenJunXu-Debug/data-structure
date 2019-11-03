/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: SeparateChainingHashtable
 * Author:   ASUS
 * Date:     2019/10/12 10:20
 * Description: 分离链接哈希表
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cjx.container;

import java.util.LinkedList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈分离链接哈希表〉
 *
 * @author ASUS
 * @create 2019/10/12
 * @since 1.0.0
 */
public class SeparateChainingHashtable<T> {
    private static final int DEFAULT_TABLE_SIZE = 16;//默认桶容量
    private List<T> [] list;//容器 每个数组元素（或者说是桶）都是一个集合
    private int currentsize;//当前容器中的实例（T类型）的数量
    //构造法
    public SeparateChainingHashtable() {
        this(DEFAULT_TABLE_SIZE);
    }
    //指定初始桶数，把每个桶初始化为一个链表
    public SeparateChainingHashtable(int tableSize) {
        list = new LinkedList[tableSize];
        for(int i=0; i<tableSize; i++){
            list[i] = new LinkedList<>();
        }
    }
    public int getSize(){
        return currentsize;
    }
    public boolean isEmpty(){
        return currentsize == 0;
    }
    public void makeEmpty(){
        for(int i=0; i<list.length; i++){
            list[i].clear();
        }
    }
    //容器里是否有这个实例
    public boolean contain(T x){
        int listIndex = myhash(x);
        return list[listIndex].contains(x);
    }
    //插入一个实例
    public void insert(T x){
        //找到桶下标
        int listIndex = myhash(x);
        //找到对应的桶，查看是否桶里包含这个实例
        if(!list[listIndex].contains(x)){
            list[listIndex].add(x);
            currentsize++;
            //桶中实例个数大于桶的个数时 扩容
            if(currentsize > list.length){
                rehash();
            }
        }
    }
    //移除一个实例
    public void remove(T x){
        //找到桶下标
        int listIndex = myhash(x);
        //找到对应的桶，查看是否桶里包含这个实例
        if(list[listIndex].contains(x)){
            list[listIndex].remove(x);
            currentsize--;
        }
    }
    //定义一个新的容器（原容器的两倍长度），
    // 把原来的实例再通过myhash()分配到新的容器中
    private void rehash(){
        List<T> [] oldList = list;
        list = new LinkedList[list.length*2];
        for(int i=0; i<list.length; i++){
            list[i] = new LinkedList<>();
        }
        for(int i=0; i<oldList.length; i++){
            for(T x:oldList[i]){
                insert(x);
            }
        }
    }
    //通过取余的算法把实例分散到对应的桶里
    private int myhash(T x){
        int hashVal = x.hashCode();
        hashVal %= list.length;
        if(hashVal < 0){
            hashVal += list.length;
        }
        return hashVal;
    }
    public void println(){
        for(int i=0; i<list.length; i++){
            for(T x:list[i]){
                System.out.println(x);
            }
        }
    }
}
